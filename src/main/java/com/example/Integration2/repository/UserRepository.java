package com.example.Integration2.repository;

import com.example.Integration2.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {
	Users findByUsername( String username); // Define this method

}