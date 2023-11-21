package com.ziahh.enums;

public enum WeekDay {
    SUNDAY("星期日"),
    MONDAY("星期一"),
    TUESDAY("星期二"),
    WEDNESDAY("星期三"),
    THURSDAY("星期四"),
    FRIDAY("星期五"),
    SATURDAY("星期六");

    private final String name;

    WeekDay(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
