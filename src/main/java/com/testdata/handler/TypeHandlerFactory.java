package com.testdata.handler;

/**
 * @ClassName TypeHandlerFactory
 * @Description
 * @Author manem
 * @Date 2023/7/5 下午2:31
 * @Version V1.0
 */
public class TypeHandlerFactory {

    public static ITypeHandler builder(String fullType) {
        String shortType = TypeHandlerAbstract.withdrawType(fullType);

        switch (shortType.toLowerCase()) {
            case "localdatetime":
                TimeHandler timeHandler = new TimeHandler();
                timeHandler.setExpectType("datetime");
                return timeHandler;
            case "byte":
            case "short":
            case "integer":
            case "long":
                return new NumberHandler();
            case "float":
            case "double":
            case "bigdecimal":
                return new FloatHandler();
            case "string":
            default:
                return new StringHandler();
        }

    }
}
