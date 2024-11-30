package com.shadow.service;

import com.shadow.entity.Schedule;
import com.shadow.repository.ScheduleRepository;
import jakarta.inject.Singleton;

import java.util.Optional;

@Singleton
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public Schedule createSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public Optional<Schedule> getScheduleByClient(Long id) {
        return Optional.ofNullable(scheduleRepository.findByclientId(id));
    }

    public Iterable<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }
}
