package com.shadow.misc;

public class CronExpressions {

    public static final String EVERY_5_MINUTES = "0 0/5 * * * ?";
    public static final String EVERY_10_MINUTES = "0 0/10 * * * ?";
    public static final String EVERY_15_MINUTES = "0 0/15 * * * ?";
    public static final String EVERY_30_MINUTES = "0 0/30 * * * ?";
    public static final String EVERY_HOUR = "0 0 * * * ?";
    public static final String EVERY_6_HOURS = "0 0 0/6 * * ?";
    public static final String EVERY_12_HOURS = "0 0 0/12 * * ?";
    public static final String DAILY = "0 0 0 * * ?";
    public static final String WEEKLY = "0 0 0 ? * MON"; // Every Monday
    public static final String MONTHLY = "0 0 0 1 * ?"; // 1st of every month
}
