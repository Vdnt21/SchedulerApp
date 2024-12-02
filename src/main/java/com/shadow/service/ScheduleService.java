package com.shadow.service;

import com.shadow.entity.Client;
import com.shadow.entity.Schedule;
import com.shadow.misc.ApiCallJob;
import com.shadow.repository.ClientRepository;
import com.shadow.repository.ScheduleRepository;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Singleton
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ClientRepository clientRepository;
    private final HttpClient httpClient;

    public Schedule saveSchedule(Schedule schedule) {
        schedule.setCreatedAt(LocalDateTime.now());
        schedule.setStatus("ACTIVE");
        Client client = clientRepository.findByclientName(schedule.getClientName());
        if(client != null) {
            schedule.setClientName(client.getClientName());
        }
        return scheduleRepository.save(schedule);
    }

    private JobDetail buildJobDetail(Schedule schedule) {
        return JobBuilder.newJob(ApiCallJob.class)
                .withIdentity("job_" + schedule.getId(), "api_calls")
                .usingJobData("scheduleId", schedule.getId())
                .build();
    }

    private Trigger buildTrigger(Schedule schedule) {
        return TriggerBuilder.newTrigger()
                .withIdentity("trigger_" + schedule.getId(), "api_calls")
                .withSchedule(CronScheduleBuilder.cronSchedule(schedule.getCronExpression()))
                .build();
    }

    public void executeApiCall(Schedule schedule) {
        try {
            HttpRequest<Object> request = HttpRequest.POST(schedule.getApiUrl(), schedule.getPayload());
            httpClient.toBlocking().exchange(request);
        } catch (Exception e) {
            throw new RuntimeException("Error calling API: " + e.getMessage(), e);
        }
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> getSchedulesByClient(String name) {
        Client client = clientRepository.findByclientName(name);
        List<Schedule> schedules = null;
        if(client != null) {
            schedules = scheduleRepository.findByclientName(client.getClientName());
        }
        return schedules;
    }

    public List<Schedule> getSchedulesByStatus() {
        return scheduleRepository.findByStatus("active");
    }

    public Optional<Schedule> getScheduleById(Long scheduleId) {
        return scheduleRepository.findById(scheduleId);
    }
}