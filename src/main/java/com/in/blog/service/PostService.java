package com.in.blog.service;

import java.util.List;
import com.in.blog.DTO.PostDto;
import com.in.blog.DTO.PostResponse;


public interface PostService {
	
	// create
	
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	//update
	
	PostDto updatePost(PostDto postDto, Integer postId);
	
	//getPostById
	
	PostDto getPostById(Integer postId);
	
	//getAllPost
	
	PostResponse getAllByPost(Integer pageNumber, Integer pageSize, String sortBy, String SortDir);
	
	//delete
	
	void deletePost(Integer postId);
	
	//get all posts by user
	
	List<PostDto> getPostByUser(Integer userId);
	
	//get all post by category
	
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	//search posts
	
	List<PostDto> searchPost(String keyword);
	
}
