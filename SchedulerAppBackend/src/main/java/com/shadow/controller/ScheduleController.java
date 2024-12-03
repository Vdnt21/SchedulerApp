package com.shadow.controller;

import com.shadow.entity.Schedule;
import com.shadow.misc.CronExpressions;
import com.shadow.repository.ClientRepository;
import com.shadow.service.ScheduleService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;

import java.util.List;

@Controller("/schedules")
public class ScheduleController {

    private ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @Post
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Schedule createSchedule(@Body Schedule schedule) {
        String interval = schedule.getCronExpression();
        switch (interval.toLowerCase()) {
            case "every_5_minutes":
                schedule.setCronExpression(CronExpressions.EVERY_5_MINUTES);
                break;
            case "every_10_minutes":
                schedule.setCronExpression(CronExpressions.EVERY_10_MINUTES);
                break;
            case "every_15_minutes":
                schedule.setCronExpression(CronExpressions.EVERY_15_MINUTES);
                break;
            case "every_30_minutes":
                schedule.setCronExpression(CronExpressions.EVERY_30_MINUTES);
                break;
            case "every_hour":
                schedule.setCronExpression(CronExpressions.EVERY_HOUR);
                break;
            case "every_6_hours":
                schedule.setCronExpression(CronExpressions.EVERY_6_HOURS);
                break;
            case "every_12_hours":
                schedule.setCronExpression(CronExpressions.EVERY_12_HOURS);
                break;
            case "daily":
                schedule.setCronExpression(CronExpressions.DAILY);
                break;
            case "weekly":
                schedule.setCronExpression(CronExpressions.WEEKLY);
                break;
            case "monthly":
                schedule.setCronExpression(CronExpressions.MONTHLY);
                break;
            default:
                throw new IllegalArgumentException("Invalid interval: " + interval);
        }

        return scheduleService.saveSchedule(schedule);
    }

    @Get
    public List<Schedule> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }

    @Get("/client/{name}")
    public List<Schedule> getSchedulesByClient(@PathVariable String name) {
        return scheduleService.getSchedulesByClient(name);
    }

    @Get("/status")
    public List<Schedule> getSchedulesByClient() {
        return scheduleService.getSchedulesByStatus();
    }
}