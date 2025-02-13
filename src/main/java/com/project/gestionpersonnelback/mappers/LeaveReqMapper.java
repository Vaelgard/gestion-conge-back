package com.project.gestionpersonnelback.mappers;

import com.project.gestionpersonnelback.dtos.LeaveDto;
import com.project.gestionpersonnelback.dtos.LeaveReqDto;
import com.project.gestionpersonnelback.dtos.UserDto;
import com.project.gestionpersonnelback.entities.Leave;
import com.project.gestionpersonnelback.entities.LeaveRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LeaveReqMapper {
    LeaveReqMapper INSTANCE = Mappers.getMapper(LeaveReqMapper.class);
    @Mapping(source = "user.id",target = "userId")
    @Mapping(source = "leave.id" ,target = "leaveId")
    LeaveReqDto LeaveReqToLeaveReqDto(LeaveRequest leaveRequest);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "leaveId",target = "leave.id" )
    LeaveRequest LeaveDtoToLeaveRequest(LeaveDto leaveDto);

}
