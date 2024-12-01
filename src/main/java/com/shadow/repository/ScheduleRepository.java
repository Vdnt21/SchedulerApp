package com.shadow.repository;

import com.shadow.entity.Schedule;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface ScheduleRepository extends CrudRepository<Schedule, Long> {

    List<Schedule> findByClientId(Long clientId);

    List<Schedule> findByStatus(String status);
}
