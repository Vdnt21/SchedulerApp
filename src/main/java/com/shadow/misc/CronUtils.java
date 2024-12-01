package com.shadow.misc;

import org.quartz.CronExpression;

public class CronUtils {

    public static boolean isValidCron(String cronExpression) {
        return CronExpression.isValidExpression(cronExpression);
    }
}
