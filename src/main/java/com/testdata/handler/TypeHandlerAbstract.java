package com.testdata.handler;

/**
 * @ClassName TypeHandlerAbstract
 * @Description
 * @Author manem
 * @Date 2023/7/4 下午5:35
 * @Version V1.0
 */
public abstract class TypeHandlerAbstract implements ITypeHandler{
    /* *
     * @description: 根据类型的全类名提取出简短的名称
     * 如 java.lang.String => String
     * @author: manem
     * @date: 2023/7/6 下午2:37
     * @param: fullType
     * @return: java.lang.String
     **/
    public static String withdrawType(String fullType) {
        return fullType.substring(fullType.lastIndexOf(".") + 1);
    }
}
