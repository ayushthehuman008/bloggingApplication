package com.in.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.in.blog.entity.Category;
import com.in.blog.entity.Post;
import com.in.blog.entity.User;

public interface PostRepo extends JpaRepository<Post, Integer>
{
	
	//These all are manually created methods
	
	List<Post> findByUser(User user);  
	
	List<Post> findByCategory(Category category);
	
	@Query("select p from Post p where p.postTitle like :key")
	List<Post> searchByTitle(@Param("key")String postTitle);
}
