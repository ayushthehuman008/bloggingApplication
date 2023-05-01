package com.in.blog.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CategoryDto {
	
	
	private Integer categoryId;
	
	@NotBlank
	@Size(min = 4, message = "Min size of the tilte name should be 4")
	private String categoryTitle;
	

	@NotBlank
	@Size(min = 10, message = "Min size category description should be 10")
	private String CategoryDescription;


	public CategoryDto(Integer categoryId, String categoryTitle, String categoryDescription) {
		super();
		this.categoryId = categoryId;
		this.categoryTitle = categoryTitle;
		CategoryDescription = categoryDescription;
	}
	public CategoryDto() {
		super();
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryTitle() {
		return categoryTitle;
	}
	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}
	public String getCategoryDescription() {
		return CategoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		CategoryDescription = categoryDescription;
	}
	
	@Override
	public String toString() {
		return "CategoryDto [categoryId=" + categoryId + ", categoryTitle=" + categoryTitle + ", CategoryDescription="
				+ CategoryDescription + "]";
	}
}
