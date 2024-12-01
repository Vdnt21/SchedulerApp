package com.shadow.controller;

import com.shadow.entity.Schedule;
import com.shadow.misc.CronExpressions;
import com.shadow.service.ScheduleService;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

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

        return scheduleService.createSchedule(schedule);
    }

//    @Get("/client/{clientId}")
//    public List<Schedule> getSchedulesByClient(@PathVariable Long clientId) {
//        return scheduleService.getSchedulesByClientId(clientId);
//    }

    @Get("/{id}")
    public Schedule getScheduleById(@PathVariable Long id) {
        return scheduleService.getScheduleById(id).orElseThrow(() -> new RuntimeException("Schedule not found"));
    }

    @Delete("/{id}")
    public HttpStatus deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return HttpStatus.NO_CONTENT;
    }
}

