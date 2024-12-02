package com.shadow.controller;

import com.shadow.entity.Schedule;
import com.shadow.misc.CronExpressions;
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
            case "every_hour":
                schedule.setCronExpression(CronExpressions.EVERY_HOUR);
                break;
            case "daily":
                schedule.setCronExpression(CronExpressions.DAILY);
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