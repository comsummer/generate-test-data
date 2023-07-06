package com.testdata.domain.pojos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @ClassName PeoplePojo
 * @Description
 * @Author manem
 * @Date 2023/7/4 上午11:16
 * @Version V1.0
 */
public class PeoplePojo {
    private Integer age;
    private String name;
    private LocalDateTime born;
    private BigDecimal salary;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getBorn() {
        return born;
    }

    public void setBorn(LocalDateTime born) {
        this.born = born;
    }
}
