package com.in.blog.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in.blog.DTO.CommentDto;
import com.in.blog.Exception.ResourceNotFoundException;
import com.in.blog.entity.Comment;
import com.in.blog.entity.Post;
import com.in.blog.repository.CommentRepo;
import com.in.blog.repository.PostRepo;
import com.in.blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private  PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId));
		
		//CommentDtoToComment
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		
		comment.setPost(post);
		
		Comment savedComment = this.commentRepo.save(comment);
		
		
		//CommentToCommentDto
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		
		Comment comment = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "comment id", commentId));
		this.commentRepo.delete(comment);
	}

}
