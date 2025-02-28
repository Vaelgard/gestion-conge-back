package com.project.gestionpersonnelback.dtos;


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
