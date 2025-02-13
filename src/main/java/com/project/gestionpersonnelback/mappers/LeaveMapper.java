package com.project.gestionpersonnelback.mappers;

import com.project.gestionpersonnelback.dtos.LeaveDto;
import com.project.gestionpersonnelback.entities.Leave;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LeaveMapper {
    LeaveMapper INSTANCE = Mappers.getMapper(LeaveMapper.class);
    LeaveDto leaveToLeaveDto(Leave leave);
    Leave leaveDtoToLeave(LeaveDto leaveDto);
}
