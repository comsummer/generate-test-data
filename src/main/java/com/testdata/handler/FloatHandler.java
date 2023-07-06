package com.testdata.handler;

import java.util.Random;

/**
 * @ClassName FloatHandler
 * @Description 生成浮点类型数据
 * @Author manem
 * @Date 2023/7/4 下午5:42
 * @Version V1.0
 */
public class FloatHandler extends TypeHandlerAbstract{
    private Integer dotCount; // 小数点的位数，默认是2位

    private Integer min; // 整数部分最小值
    private Integer max; // 整数部分最大值

    public FloatHandler() {
        this.min = 10;
        this.max = 20;
        this.dotCount = 2;
    }

    public FloatHandler(Integer dotCount, Integer min, Integer max) {
        this.dotCount = dotCount;
        this.min = min;
        this.max = max;
    }

    @Override
    public Float generate() {
        float defaultVal = 0.0F;
        Random random = new Random();
        if (getMin() >= getMax()) {
            return defaultVal;
        }
        int diff = getMax() - getMin();
        int integer = random.nextInt(diff) + getMin();

        // 生成小数部分
        int dotRate = (int) Math.pow(10, getDotCount());
        float dot = random.nextInt(dotRate);

        // 数值生成小数时以浮点数比上倍数即可
        // 由于浮点数运算之后的精度无法保证，所以通过格式化的方式只保留设置的位数
        return Float.valueOf(String.format("%" + getDotCount() + "f", integer + dot / dotRate));
    }

    public Integer getDotCount() {
        return dotCount;
    }

    public void setDotCount(Integer dotCount) {
        this.dotCount = dotCount;
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
}
