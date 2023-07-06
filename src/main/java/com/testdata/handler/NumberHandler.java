package com.testdata.handler;

import java.util.Random;

/**
 * @ClassName NumberHandler
 * @Description 生成整数类型数据
 * @Author manem
 * @Date 2023/7/4 下午1:19
 * @Version V1.0
 */
public class NumberHandler extends TypeHandlerAbstract {
    private Integer min; // 最小值
    private Integer max; // 最大值
    private String fullType; // 整数类型的全类名，如java.lang.Integer

    public NumberHandler() {
        this.min = 10;
        this.max = 20;
    }

    public NumberHandler(Integer min, Integer max, String fullType) {
        this.min = min;
        this.max = max;
        this.fullType = fullType;
    }

    @Override
    public Long generate() {
        if (getMax() == null) {
            switch (withdrawType(getFullType()).toLowerCase()) {
                case "short":
                    max = (int) Short.MAX_VALUE;
                    break;
                case "integer":
                case "long":
                    max = Integer.MAX_VALUE;
                    break;
                case "byte":
                default:
                    max = (int) Byte.MAX_VALUE;
            }
        }
        if (getMin() != null) {
            // 生成范围内的值
            Random random = new Random();
            int diff = max - min;
            return (long) (random.nextInt(min) + diff);
        }
        return (long) (Math.random() * max);
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public String getFullType() {
        return fullType;
    }

    public void setFullType(String fullType) {
        this.fullType = fullType;
    }
}
