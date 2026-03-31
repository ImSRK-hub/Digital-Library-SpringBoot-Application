package com.srk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srk.entity.UserRecord;

public interface UserRepository extends JpaRepository<UserRecord, Integer> {

}
