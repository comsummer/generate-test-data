package com.testdata.handler;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * @ClassName TimeHandler
 * @Description 生成时间类型的数据，支持时间戳、或日期字符串
 * @Author manem
 * @Date 2023/7/5 下午5:48
 * @Version V1.0
 */
public class TimeHandler extends TypeHandlerAbstract {
    private final String datetimeFormatter = "yyyy-MM-dd HH:mm:ss";
    private Long start; // 最小的日期值
    private Long end; // 最大的日期值
    private String expectType = "sec";

    public TimeHandler() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDateTime start = LocalDateTime.now();
        String curr_date = dateFormatter.format(start);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(datetimeFormatter);
        LocalDateTime curr_datetime = LocalDateTime.parse(curr_date + " 00:00:00", dateTimeFormatter);
        long startSec = curr_datetime.toInstant(ZoneOffset.of("+8")).getEpochSecond();
        long endSec = startSec + 86400;

        this.start = startSec;
        this.end = endSec;
    }

    public TimeHandler(String start, String end, String expectType) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(datetimeFormatter);
        this.start = LocalDateTime.parse(start, dateTimeFormatter).toInstant(ZoneOffset.of("+8")).getEpochSecond();
        this.end = LocalDateTime.parse(end, dateTimeFormatter).toInstant(ZoneOffset.of("+8")).getEpochSecond();
        this.expectType = expectType;
    }

    @Override
    public Object generate() {
        if (getStart() > getEnd()) {
            throw new RuntimeException("最小日期值不能大于最大日期值");
        }
        // 根据start和end的时间戳生成时间
        long diff = getEnd() - getStart();

        Random random = new Random();

        long val = random.nextInt((int) diff) + getStart();

        switch (getExpectType().toLowerCase()) {
            case "datetime":
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(datetimeFormatter);
                return dateTimeFormatter.format(LocalDateTime.ofEpochSecond(val, 0, ZoneOffset.of("+8")));
            case "sec":
            default:
                return val;
        }
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }

    public String getExpectType() {
        return expectType;
    }

    public void setExpectType(String expectType) {
        this.expectType = expectType;
    }
}
