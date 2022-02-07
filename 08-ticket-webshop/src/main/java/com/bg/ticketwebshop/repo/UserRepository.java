package com.bg.ticketwebshop.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bg.ticketwebshop.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

}
