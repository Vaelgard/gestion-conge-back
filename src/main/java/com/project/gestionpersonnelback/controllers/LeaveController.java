package com.project.gestionpersonnelback.controllers;

import com.project.gestionpersonnelback.dtos.LeaveReqDto;
import com.project.gestionpersonnelback.services.LeaveService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/leave")

public class LeaveController {
    private LeaveService leaveService;
    @GetMapping("/getAll")
    public ResponseEntity<List<LeaveReqDto>> getAllUsers(){
        return ResponseEntity.ok(leaveService.requestLeaveList());
    }
    @PostMapping("/create")
    public ResponseEntity<LeaveReqDto> createLeave(@RequestBody LeaveReqDto leaveReqDto){
        return ResponseEntity.ok(leaveService.requestLeave(leaveReqDto));
    }
    @PostMapping("/approved")
    public ResponseEntity<LeaveReqDto> approveLeave(@RequestBody LeaveReqDto leaveReqDto
    ){
        return ResponseEntity.ok(leaveService.approveLeave(leaveReqDto));
    }
    @PostMapping("/rejected")
    public ResponseEntity<LeaveReqDto> rejectedLeave(@RequestBody LeaveReqDto leaveReqDto){
        return ResponseEntity.ok(leaveService.rejectedLeave(leaveReqDto));
    }
    @GetMapping("/getAllByUserId/{userId}")
    public ResponseEntity<List<LeaveReqDto>> getAllUsersByUserId(@PathVariable Integer userId){
        return ResponseEntity.ok(leaveService.requestLeaveListByUserId(userId));
    }
}
