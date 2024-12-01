package com.shadow.service;

import jakarta.inject.Singleton;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

@Singleton
public class ApiCallJob implements Job {

    private final ScheduleService scheduleService;

    public ApiCallJob(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @Override
    public void execute(JobExecutionContext context) {
        Long scheduleId = context.getJobDetail().getJobDataMap().getLong("scheduleId");
        scheduleService.getScheduleById(scheduleId).ifPresent(scheduleService::executeApiCall);
    }
}
