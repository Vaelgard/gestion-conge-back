package com.project.gestionpersonnelback.services;

import com.project.gestionpersonnelback.dtos.ReqRes;
import com.project.gestionpersonnelback.entities.OurUsers;
import com.project.gestionpersonnelback.repositories.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UsersManagementService {

    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public ReqRes register(ReqRes registrationRequest){
        ReqRes resp = new ReqRes();

        try {
            OurUsers ourUser = new OurUsers();
            ourUser.setEmail(registrationRequest.getEmail());
            ourUser.setRole(registrationRequest.getRole());
            ourUser.setName(registrationRequest.getName());
            ourUser.setPhone(registrationRequest.getPhone());
            ourUser.setRole(registrationRequest.getRole());
            ourUser.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            var jwt = jwtUtils.generateToken(ourUser);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), ourUser);
            OurUsers ourUsersResult = usersRepo.save(ourUser);
            if (ourUsersResult.getId() != null) {
                resp.setStatusCode(200);
                resp.setToken(jwt);
                resp.setRefreshToken(refreshToken);
                resp.setExpirationTime("24Hrs");
                resp.setOurUsers(ourUsersResult);
                resp.setMessage("User Saved Successfully");
            }
        } catch (Exception e) {
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }



    public ReqRes login(ReqRes loginRequest){
        ReqRes response = new ReqRes();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                    loginRequest.getPassword()));
            Optional<OurUsers> userOptional = usersRepo.findByEmail(loginRequest.getEmail());
            if (userOptional.isPresent()) {
                OurUsers user = userOptional.get();
                var jwt = jwtUtils.generateToken(user);
                var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
                response.setStatusCode(200);
                response.setToken(jwt);
                response.setName(user.getName());
                response.setRole(user.getRole());
                response.setRefreshToken(refreshToken);
                response.setExpirationTime("24Hrs");
                response.setMessage("Successfully Logged In");
            } else {
                response.setStatusCode(404);
                response.setMessage("User not found");
            }
        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }


    public ReqRes refreshToken(ReqRes refreshTokenRequest){
        ReqRes response = new ReqRes();
        try{
            String userEmail = jwtUtils.extractUsername(refreshTokenRequest.getRefreshToken());
            Optional<OurUsers> userOptional = usersRepo.findByEmail(userEmail);
            if (userOptional.isPresent()) {
                OurUsers user = userOptional.get();
                if (jwtUtils.isTokenValid(refreshTokenRequest.getRefreshToken(), user)) {
                    var jwt = jwtUtils.generateToken(user);
                    response.setStatusCode(200);
                    response.setToken(jwt);
                    response.setRefreshToken(refreshTokenRequest.getRefreshToken());
                    response.setExpirationTime("24Hrs");
                    response.setMessage("Successfully Refreshed Token");
                } else {
                    response.setStatusCode(401); // Unauthorized
                    response.setMessage("Invalid refresh token");
                }
            } else {
                response.setStatusCode(404); // Not Found
                response.setMessage("User not found");
            }
        } catch (Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }
    public ReqRes getAllUsers() {
        ReqRes reqRes = new ReqRes();

        try {
            List<OurUsers> result = usersRepo.findAll();
            if (!result.isEmpty()) {
                reqRes.setOurUsersList(result);
                reqRes.setStatusCode(200);
                reqRes.setMessage("Successful");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("No users found");
            }
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred: " + e.getMessage());
        }
        return reqRes;
    }


    public ReqRes getUserByEmail(String email) {
        ReqRes reqRes = new ReqRes();
        try {
            Optional<OurUsers> userOptional = usersRepo.findByEmail(email);
            if (userOptional.isPresent()) {
                OurUsers user = userOptional.get();
                reqRes.setOurUsers(user);
                reqRes.setStatusCode(200);
                reqRes.setMessage("User with id '" + email + "' found successfully");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found with id: " + email);
            }
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred: " + e.getMessage());
        }
        return reqRes;
    }


    public ReqRes deleteUser(Integer userId) {
        ReqRes reqRes = new ReqRes();
        try {
            if (usersRepo.existsById(userId)) {
                usersRepo.deleteById(userId);
                reqRes.setStatusCode(200);
                reqRes.setMessage("User deleted successfully");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found for deletion");
            }
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred while deleting user: " + e.getMessage());
        }
        return reqRes;
    }

    public ReqRes updateUser(Integer userId, OurUsers updatedUser) {
        ReqRes reqRes = new ReqRes();
        try {
            Optional<OurUsers> userOptional = usersRepo.findById(userId);
            if (userOptional.isPresent()) {
                OurUsers existingUser = userOptional.get();
                existingUser.setEmail(updatedUser.getEmail());
                existingUser.setName(updatedUser.getName());
                existingUser.setRole(existingUser.getRole());
                existingUser.setPhone(updatedUser.getPhone());

                // Check if password is present in the request
                if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                    // Encode the password and update it
                    existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
                }

                OurUsers savedUser = usersRepo.save(existingUser);
                reqRes.setOurUsers(savedUser);
                reqRes.setStatusCode(200);
                reqRes.setMessage("User updated successfully");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found for update");
            }
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred while updating user: " + e.getMessage());
        }
        return reqRes;
    }
}
