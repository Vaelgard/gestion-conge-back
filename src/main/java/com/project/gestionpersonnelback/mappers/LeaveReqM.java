package com.project.gestionpersonnelback.mappers;

import com.project.gestionpersonnelback.dtos.LeaveReqDto;
import com.project.gestionpersonnelback.entities.LeaveRequest;

public interface LeaveReqM {
    public LeaveReqDto LeaveReqToLeaveReqDto(LeaveRequest leaveRequest);
    public LeaveRequest LeaveDtoToLeaveRequest(LeaveReqDto leaveDto);
}
