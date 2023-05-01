package com.in.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in.blog.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
