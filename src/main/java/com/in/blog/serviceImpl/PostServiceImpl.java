package com.in.blog.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.in.blog.DTO.PostDto;
import com.in.blog.DTO.PostResponse;
import com.in.blog.Exception.ResourceNotFoundException;
import com.in.blog.entity.Category;
import com.in.blog.entity.Post;
import com.in.blog.entity.User;
import com.in.blog.repository.CategoryRepo;
import com.in.blog.repository.PostRepo;
import com.in.blog.repository.UserRepo;
import com.in.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService 
{
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto,Integer userId, Integer categoryId) 
	{
		
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "user id", userId));
		
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "category id", categoryId));
		
		//PostDtoToPost
		Post post = this.modelMapper.map(postDto, Post.class);
		
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post save = this.postRepo.save(post);
		
		//PostToPostDto
		return this.modelMapper.map(save, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) 
	{
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId));
		post.setPostTitle(postDto.getPostTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		Post updatedPost = this.postRepo.save(post);
		
		//PostToPostDto
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	//H.W
	@Override
	public PostDto getPostById(Integer postId) 
	{
		Optional<Post> optional = this.postRepo.findById(postId);
		Post post = optional.orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	//H.W
	@Override
	public PostResponse getAllByPost(Integer pageNumber, Integer pageSize, String sortBy, String SortDir) //--> SortDir is working as a variable
	{
		
//		int pageSize = 5;
//		int pageNumber = 1;
		
		//Method - 2 for sorting using ternary operator
		
		//Sort sort1 = (SortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		//Pageable p = PageRequest.of(pageNumber, pageSize, sort1);
		
		//Method - 1 for sorting using if-else;
		
		Sort sort = null;
		if(SortDir.equalsIgnoreCase("asc"))
		{
			 sort = Sort.by(sortBy).ascending();
		}
		else
		{
			 sort = Sort.by(sortBy).descending();
		}
		
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);  //Sort.by(sortBy).descending()
		
		Page<Post> pagePost = this.postRepo.findAll(p);
		
		//PageDtoToPage
		List<Post> posts = pagePost.getContent();
		
		//PageToPageDto
		List<PostDto> postdtos = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		
		PostResponse postResponse = new PostResponse();
		
		postResponse.setContent(postdtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		return postResponse;
		
	}
	@Override
	public List<PostDto> searchPost(String keyword) {
		
		//PostDtoTOPost
		List<Post> posts = this.postRepo.searchByTitle("%" + keyword + "%");
		
		//PostToPostDto
		List<PostDto> allPosts = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return allPosts;
	}

	@Override
	public void deletePost(Integer postId) 
	{
		
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId));
		this.postRepo.delete(post);
		
	}
	
	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User user1 = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "user id", userId));
		
		//PostDtoToPost
		List<Post> posts = this.postRepo.findByUser(user1);
		
		//PostToPostDto
		List<PostDto> postdtos = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postdtos;
	}
	
	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "category id", categoryId));
		
		
		//PostDtoToPost
		List<Post> posts = this.postRepo.findByCategory(cat);
		
		//PostDtoToPost
		List<PostDto> postdtos = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postdtos;
	}

}
