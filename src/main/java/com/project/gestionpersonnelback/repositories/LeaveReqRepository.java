package com.project.gestionpersonnelback.repositories;

import com.project.gestionpersonnelback.entities.LeaveRequest;
import com.project.gestionpersonnelback.entities.OurUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveReqRepository extends JpaRepository<LeaveRequest,Integer> {
    List<LeaveRequest> findLeaveRequestByUser(OurUsers user);
}
