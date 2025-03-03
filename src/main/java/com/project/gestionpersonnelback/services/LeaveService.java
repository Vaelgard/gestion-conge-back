package com.project.gestionpersonnelback.services;

import com.project.gestionpersonnelback.dtos.LeaveReqDto;
import com.project.gestionpersonnelback.entities.LeaveRequest;

import com.project.gestionpersonnelback.entities.OurUsers;
import com.project.gestionpersonnelback.mappers.LeaveReqM;
import com.project.gestionpersonnelback.repositories.LeaveReqRepository;
import com.project.gestionpersonnelback.repositories.UsersRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class LeaveService {
    private  UsersRepo usersRepo;
    private  LeaveReqRepository leaveReqRepository;

    public LeaveReqDto requestLeave(LeaveReqDto leaveReqDto) {
        try {
            leaveReqDto.setStatut("Pending");
            leaveReqRepository.save(LeaveDtoToLeaveRequest(leaveReqDto));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return leaveReqDto;
    }
    public List<LeaveReqDto> requestLeaveList() {
        List<LeaveReqDto> leaveReqDtoList = new ArrayList<>();
        List<LeaveRequest> leaveRequestList = leaveReqRepository.findAll();
        for (int i = 0; i < leaveRequestList.size(); i++) {
            leaveReqDtoList.add(LeaveReqToLeaveReqDto(leaveRequestList.get(i)));
        }
        return leaveReqDtoList;
    }
    public LeaveReqDto approveLeave(LeaveReqDto leaveReqDto) {
        LeaveRequest leaveRequest = leaveReqRepository.findById(leaveReqDto.getId()).orElse(null);
        if (leaveRequest == null) {
            return null;
        }else {
            leaveRequest.setStatut("Approved");
            leaveReqRepository.save(leaveRequest);
        }
        return LeaveReqToLeaveReqDto(leaveRequest);
    }
    public LeaveReqDto rejectedLeave(LeaveReqDto leaveReqDto) {
        LeaveRequest leaveRequest = leaveReqRepository.findById(leaveReqDto.getId()).orElse(null);
        if (leaveRequest == null) {
            return null;
        }else {
            leaveRequest.setStatut("Rejected");
            leaveReqRepository.save(leaveRequest);
        }
        return LeaveReqToLeaveReqDto(leaveRequest);
    }
    public List<LeaveReqDto> requestLeaveListByUserId(Integer userId) {
        List<LeaveReqDto> leaveReqDtoList = new ArrayList<>();
        OurUsers user = usersRepo.findById(userId).orElse(null);
        List<LeaveRequest> leaveRequestList = leaveReqRepository.findLeaveRequestByUser(user);
        for (int i = 0; i < leaveRequestList.size(); i++) {
            leaveReqDtoList.add(LeaveReqToLeaveReqDto(leaveRequestList.get(i)));
        }
        return leaveReqDtoList;
    }
    public LeaveReqDto LeaveReqToLeaveReqDto(LeaveRequest leaveRequest) {
        if (leaveRequest == null) {
            return null;
        }

        LeaveReqDto dto = new LeaveReqDto();
        dto.setId(leaveRequest.getId());
        dto.setReason(leaveRequest.getReason());
        dto.setName(leaveRequest.getUser().getName());
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
