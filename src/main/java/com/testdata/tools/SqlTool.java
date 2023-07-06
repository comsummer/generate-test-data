package com.testdata.tools;

import com.testdata.handler.ITypeHandler;
import com.testdata.handler.TypeHandlerAbstract;
import com.testdata.handler.TypeHandlerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SqlTool
 * @Description
 * @Author manem
 * @Date 2023/7/5 下午2:26
 * @Version V1.0
 */
public class SqlTool {
    final static Map<String, ITypeHandler> handlerMap = new HashMap<>();

    /* *
     * @description: 生成测试数据的插入语句
     * @author: manem
     * @date: 2023/7/6 下午2:43
     * @param: cls 实体类的类型
     * @param: tbl 要插入的数据表名
     * @param: count 要生成的数据条数
     * @param: sqlFilePath 生成的sql文件保存地址
     * @return: java.lang.String
     **/
    public static String generateInsertSql(Class<?> cls, String tbl, Integer count, String sqlFilePath) throws IOException {
        if (count <= 0) {
            throw new RuntimeException("测试数据的数量必须大于0");
        }
        String sqlFilename = sqlFilePath + File.separator + tbl + ".sql";
        chkFileLocation(sqlFilename);

        String format = "INSERT INTO %s(%s) VALUES %s";

        StringBuilder fieldString = new StringBuilder();
        List<String> types = new ArrayList<>();
        for (Field field : cls.getDeclaredFields()) {
            fieldString.append(String.format("`%s`, ", field.getName()));
            types.add(field.getType().toString());
        }
        // 移除
        String field = fieldString.toString().substring(0, fieldString.length() - 2);

        // 生成SQL数据 每条sql最多使用200条values
        int sqlMaxItem = 200;

        List<String> sqls = new ArrayList<>();
        StringBuilder valString = new StringBuilder();
        for (int i = 1; i <= count; i++) {
            valString.append("(");
            for (String type : types) {
                ITypeHandler handler = TypeHandlerFactory.builder(type);
                Object testVal = handler.generate();
                switch (TypeHandlerAbstract.withdrawType(handler.getClass().toString().toLowerCase())) {
                    case "stringhandler":
                    case "timehandler":
                        valString.append(String.format("'%s', ", testVal));
                        break;
                    case "numberhandler":
                    case "floathandler":
                    default:
                        valString.append(String.format("%s, ", testVal));
                }
            }
            // 去除值的最后一个分隔符
            valString.deleteCharAt(valString.lastIndexOf(", "));
            valString.append("), ");

            // 判断是否达到单条SQL最大val数
            if (i % sqlMaxItem == 0) {
                // 保存此条sql
                String val = valString.toString();
                val = val.substring(0, val.length() - 2);
                sqls.add(String.format(format, tbl, field, val));
                valString = new StringBuilder();
            }
        }
        if (valString.toString().length() > 0) {
            // 接收剩余的数据
            // 保存此条sql
            String val = valString.toString();
            val = val.substring(0, val.length() - 2);
            sqls.add(String.format(format, tbl, field, val));
        }

        writeToFile(sqlFilename, sqls);
        return sqlFilename;
    }

    private static void chkFileLocation(String filename) {
        File file = new File(filename);
        if (!file.canWrite()) {
            throw new RuntimeException("文件路径不可写，请检查文件|文件夹权限");
        }
    }

    private static void writeToFile(String filename, List<String> sqlList) throws IOException {
        // 保存到.sql文件
        FileOutputStream os = new FileOutputStream(new File(filename));

        for (String sql : sqlList) {
            os.write(sql.getBytes());
            // 写入换行和结束标记
            os.write(";\r\n".getBytes());
        }
    }
}
