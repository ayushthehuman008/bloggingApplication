package com.in.blog.service;

import java.util.List;

import com.in.blog.DTO.CategoryDto;

public interface CategoryService {

	//create
	 CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	 CategoryDto 
	 updateCategory(CategoryDto categoryDto, Integer categoryId);
	
	//get
	 CategoryDto getCateogoryById(Integer categoryId);
	
	//getAll
	 List<CategoryDto> getAllCategory();
	
	//delete
	void deleteCategory(Integer categoryId);

}
