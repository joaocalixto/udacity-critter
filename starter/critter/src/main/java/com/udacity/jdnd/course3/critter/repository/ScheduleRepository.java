package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.repository.entities.pet.Pet;
import com.udacity.jdnd.course3.critter.repository.entities.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findByPetsId(Long id);

    List<Schedule> findByEmployeesId(Long id);

    List<Schedule> findByPetsUserId(Long id);
}
