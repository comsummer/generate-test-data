package com.testdata.handler;

import java.util.Random;

/**
 * @ClassName StringHandler
 * @Description 生成字符串类型数据
 * @Author manem
 * @Date 2023/7/4 下午4:34
 * @Version V1.0
 */
public class StringHandler extends TypeHandlerAbstract {
    private Integer len; // 设置生成的字符串长度，目前是定长的字符串

    // A-Z 65-90 a-z 97-122
    final static Integer A = 65;
    final static Integer Z = 90;
    final static Integer a = 97;
    final static Integer z = 122;

    public StringHandler() {
        this.len = 10;
    }

    public StringHandler(Integer len) {
        this.len = len;
    }

    @Override
    public String generate() {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        int t;
        for (int i = 0; i < len; i++) {

            int typeT = random.nextInt(2);
            if (typeT == 1) {
                t = random.nextInt(25) + A; // 生成[65-90]之间的随机数，nextInt(25)生成的是[0-24]
            } else {
                t = random.nextInt(25) + a;
            }
            // char和数字转换直接使用强转类型即可
            str.append((char)t);
        }
        return str.toString();
    }

    public Integer getLen() {
        return len;
    }

    public void setLen(Integer len) {
        this.len = len;
    }
}
