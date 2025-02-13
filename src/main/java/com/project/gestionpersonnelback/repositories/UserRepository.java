package com.project.gestionpersonnelback.repositories;

import com.project.gestionpersonnelback.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
