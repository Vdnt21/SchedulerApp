package com.shadow.controller;

import com.shadow.entity.Schedule;
import com.shadow.service.ScheduleService;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;

@Controller("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Get("/health-check")
    public String healthCheck() {
        return "Ok";
    }
    @Post
    public Schedule createSchedule(@Body Schedule schedule) {
        return scheduleService.createSchedule(schedule);
    }

    @Get("/{id}")
    public Schedule getScheduleByClientId(@PathVariable Long id) {
        return scheduleService.getScheduleByClient(id).orElse(null);
    }

    @Get
    public Iterable<Schedule> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }
}

