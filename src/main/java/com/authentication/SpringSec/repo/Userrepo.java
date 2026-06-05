package com.authentication.SpringSec.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.authentication.SpringSec.Model.Users;
@Repository
public interface Userrepo extends JpaRepository<Users,Integer>{
     Users findByUsername(String username);
}
