package com.nat.calandar.DAO;

import com.nat.calandar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {

}
