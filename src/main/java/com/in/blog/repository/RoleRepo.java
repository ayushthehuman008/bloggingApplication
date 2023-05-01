package com.in.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in.blog.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>
{

}
