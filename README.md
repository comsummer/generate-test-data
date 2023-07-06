# 生成测试数据

## 背景

在做完一个项目功能有时需要造大批测试数据填充到数据库，设计测试数据的生成方式虽然不难，但是比较繁琐。
所以萌生出设计一个用于生成大批测试数据的工具

## 实现

根据实体类和数据表名生成插入的SQL语句

### 使用方式

```java
    @Test
    public void numberTest() throws IOException {
//        String filename = SqlTool.generateInsertSql(PeoplePojo.class, "people", 500, "/tmp");
        String filename = SqlTool.generateInsertSql(TxUsrPojo.class, "tx_usr", 500, "/tmp");
        System.out.println("已保存在文件：" + filename);
    }
```
