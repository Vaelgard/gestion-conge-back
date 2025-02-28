package com.project.gestionpersonnelback.repositories;

import com.project.gestionpersonnelback.entities.Leave;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LeaveRepository extends JpaRepository<Leave, Integer> {
}
