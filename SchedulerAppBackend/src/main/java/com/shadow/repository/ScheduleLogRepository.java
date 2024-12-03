package com.shadow.repository;

import com.shadow.entity.ScheduleLog;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface ScheduleLogRepository extends CrudRepository<ScheduleLog, Long> {
}
