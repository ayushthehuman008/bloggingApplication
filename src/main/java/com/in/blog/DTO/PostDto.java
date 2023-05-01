package com.in.blog.DTO;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class PostDto {

	private Integer postId;
	
	private String postTitle;
	
	private String content;
	
	private String imageName;
	
	private Date addedDate;

	private CategoryDto category;
	
	private UserDto user;
	
	private Set<CommentDto> comments = new HashSet<>();
	
	
	public PostDto() {
		super();
	}

	public PostDto(Integer postId, String postTitle, String content, String imageName, Date addedDate,
			CategoryDto category, UserDto user, Set<CommentDto> comments) {
		super();
		this.postId = postId;
		this.postTitle = postTitle;
		this.content = content;
		this.imageName = imageName;
		this.addedDate = addedDate;
		this.category = category;
		this.user = user;
		this.comments = comments;
	}

	public Set<CommentDto> getComments() {
		return comments;
	}

	public void setComments(Set<CommentDto> comments) {
		this.comments = comments;
	}
	
	public String getImageName() {
		return imageName;
	}
	
	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public CategoryDto getCategory() {
		return category;
	}

	public void setCategory(CategoryDto category) {
		this.category = category;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "PostDto [postId=" + postId + ", postTitle=" + postTitle + ", content=" + content + ", imageName="
				+ imageName + ", addedDate=" + addedDate + ", category=" + category + ", user=" + user + "]";
	}



	
	//private String imageName = "default.png";
	
	
}
