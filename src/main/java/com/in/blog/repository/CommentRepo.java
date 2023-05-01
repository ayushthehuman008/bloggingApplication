package com.in.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in.blog.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
