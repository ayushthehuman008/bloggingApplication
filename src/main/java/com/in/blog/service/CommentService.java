package com.in.blog.service;

import com.in.blog.DTO.CommentDto;

public interface CommentService {
	
	// create comment
	CommentDto createComment(CommentDto commentDto, Integer postId);
	
	void deleteComment(Integer commentId);
	

}
