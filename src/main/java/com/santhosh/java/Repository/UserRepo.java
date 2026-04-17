package com.santhosh.java.Repository;

import com.santhosh.java.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserInfo,Integer>
{
    Optional<UserInfo> findByUsername(String username);
}
