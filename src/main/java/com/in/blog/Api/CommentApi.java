package com.in.blog.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in.blog.DTO.ApiResponse;
import com.in.blog.DTO.CommentDto;
import com.in.blog.service.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentApi {

	@Autowired
	private CommentService commentService;
	
	
	//create comment
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId)
	{
		CommentDto comment = this.commentService.createComment(commentDto, postId);
		return new ResponseEntity<CommentDto>(comment, HttpStatus.CREATED);
	}
	
	
	//delete comment
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId)
	{
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted Successfully", false), HttpStatus.OK);
	}
	
}
