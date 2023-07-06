import com.testdata.domain.pojos.TxUsrPojo;
import com.testdata.tools.SqlTool;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @ClassName HandlerTest
 * @Description
 * @Author manem
 * @Date 2023/7/4 上午11:14
 * @Version V1.0
 */
public class HandlerTest {

    @Test
    public void numberTest() throws IOException {
//        String filename = SqlTool.generateInsertSql(PeoplePojo.class, "people", 500, "/tmp");
        String filename = SqlTool.generateInsertSql(TxUsrPojo.class, "tx_usr", 500, "/tmp");
        System.out.println("已保存在文件：" + filename);
    }

}
