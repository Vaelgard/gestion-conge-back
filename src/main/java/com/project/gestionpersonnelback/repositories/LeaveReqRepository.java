package com.project.gestionpersonnelback.repositories;

import com.project.gestionpersonnelback.entities.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveReqRepository extends JpaRepository<LeaveRequest,Integer> {
}
