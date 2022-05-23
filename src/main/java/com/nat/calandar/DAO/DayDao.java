package com.nat.calandar.DAO;

import java.time.LocalDate;

import com.nat.calandar.model.Day;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DayDao extends JpaRepository<Day, LocalDate> {
	
}
