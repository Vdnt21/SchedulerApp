package com.shadow.service;

import com.shadow.entity.Schedule;
import com.shadow.repository.ScheduleRepository;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import jakarta.inject.Singleton;
import org.quartz.*;

import java.util.List;
import java.util.Optional;

@Singleton
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final Scheduler quartzScheduler;
    private final HttpClient httpClient;

    public ScheduleService(ScheduleRepository scheduleRepository,
                           Scheduler quartzScheduler,
                           @Client HttpClient httpClient) {
        this.scheduleRepository = scheduleRepository;
        this.quartzScheduler = quartzScheduler;
        this.httpClient = httpClient;
    }

    public Schedule createSchedule(Schedule schedule) {
        Schedule savedSchedule = scheduleRepository.save(schedule);

        try {
            JobDetail jobDetail = buildJobDetail(savedSchedule);
            Trigger trigger = buildTrigger(savedSchedule);

            quartzScheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            throw new RuntimeException("Error scheduling job: " + e.getMessage(), e);
        }

        return savedSchedule;
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
            if (schedule.getHeaders() != null && !schedule.getHeaders().isEmpty()) {
                schedule.getHeaders();
            }

            httpClient.toBlocking().exchange(request);
        } catch (Exception e) {
            throw new RuntimeException("Error calling API: " + e.getMessage(), e);
        }
    }


    public List<Schedule> getSchedulesByClientId(Long clientId) {
        return scheduleRepository.findByClientId(clientId);
    }

    // Get all active schedules
    public List<Schedule> getActiveSchedules() {
        return scheduleRepository.findByStatus("active");
    }

    // Delete a schedule by ID
    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }

    // Get schedule by ID
    public Optional<Schedule> getScheduleById(Long id) {
        return scheduleRepository.findById(id);
    }
}
