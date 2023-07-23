package com.example.SpringMVC_Project.repository;

import com.example.SpringMVC_Project.model.security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
}
