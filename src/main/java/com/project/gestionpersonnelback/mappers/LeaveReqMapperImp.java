package com.project.gestionpersonnelback.mappers;

import com.project.gestionpersonnelback.dtos.LeaveReqDto;
import com.project.gestionpersonnelback.entities.LeaveRequest;
import com.project.gestionpersonnelback.entities.OurUsers;

public class LeaveReqMapperImp implements LeaveReqM {

    public LeaveReqDto LeaveReqToLeaveReqDto(LeaveRequest leaveRequest) {
        if (leaveRequest == null) {
            return null;
        }

        LeaveReqDto dto = new LeaveReqDto();
        dto.setId(leaveRequest.getId());
        dto.setReason(leaveRequest.getReason());
        dto.setStatut(leaveRequest.getStatut());
        dto.setStartDate(leaveRequest.getStartDate());
        dto.setEndDate(leaveRequest.getEndDate());

        if (leaveRequest.getUser() != null) {
            dto.setUserId(leaveRequest.getUser().getId());
        }

        return dto;
    }

    public LeaveRequest LeaveDtoToLeaveRequest(LeaveReqDto leaveDto) {
        if (leaveDto == null) {
            return null;
        }

        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setId(leaveDto.getId());
        leaveRequest.setReason(leaveDto.getReason());
        leaveRequest.setStatut(leaveDto.getStatut());
        leaveRequest.setStartDate(leaveDto.getStartDate());
        leaveRequest.setEndDate(leaveDto.getEndDate());

        if (leaveDto.getUserId() != null) {
            OurUsers user = new OurUsers();
            user.setId(leaveDto.getUserId());
            leaveRequest.setUser(user);
        }

        return leaveRequest;
    }
}

