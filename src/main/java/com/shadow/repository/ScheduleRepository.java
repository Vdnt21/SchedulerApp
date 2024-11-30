package com.shadow.repository;

import com.shadow.entity.Schedule;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface ScheduleRepository extends CrudRepository<Schedule, Long> {

    public Schedule findByclientId(Long id);
}
