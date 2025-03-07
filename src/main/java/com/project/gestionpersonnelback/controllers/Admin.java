package com.project.gestionpersonnelback.controllers;

import com.project.gestionpersonnelback.dtos.ReqRes;
import com.project.gestionpersonnelback.entities.OurUsers;
import com.project.gestionpersonnelback.services.UsersManagementService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class Admin {
    private UsersManagementService usersManagementService;

    @PostMapping("/auth/register")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ReqRes> regeister(@RequestBody ReqRes req){
        return ResponseEntity.ok(usersManagementService.register(req));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<ReqRes> login(@RequestBody ReqRes req){
        return ResponseEntity.ok(usersManagementService.login(req));
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes req){
        return ResponseEntity.ok(usersManagementService.refreshToken(req));
    }

    @GetMapping("/admin/get-all-users")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ReqRes> getAllUsers(){
        return ResponseEntity.ok(usersManagementService.getAllUsers());

    }

    @GetMapping("/admin/get-users/{email}")
    public ResponseEntity<ReqRes> getUserByEmail(@PathVariable String email){
        return ResponseEntity.ok(usersManagementService.getUserByEmail(email));

    }

    @PutMapping("/admin/update/{userId}")
    public ResponseEntity<ReqRes> updateUser(@PathVariable Integer userId, @RequestBody OurUsers reqres){
        return ResponseEntity.ok(usersManagementService.updateUser(userId, reqres));
    }

    @DeleteMapping("/admin/delete/{userId}")
    public ResponseEntity<ReqRes> deleteUSer(@PathVariable Integer userId){
        return ResponseEntity.ok(usersManagementService.deleteUser(userId));
    }


}
