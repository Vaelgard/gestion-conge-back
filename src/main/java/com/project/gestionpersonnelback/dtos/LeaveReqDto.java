package com.project.gestionpersonnelback.dtos;

import com.project.gestionpersonnelback.entities.Leave;
import com.project.gestionpersonnelback.entities.User;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeaveReqDto {
    private Integer id;
    private String reason;
    private Integer userId;
    private Integer leaveId;
}
