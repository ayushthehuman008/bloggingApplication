 package com.in.blog.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in.blog.DTO.CategoryDto;
import com.in.blog.Exception.ResourceNotFoundException;
import com.in.blog.entity.Category;
import com.in.blog.repository.CategoryRepo;
import com.in.blog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) 
	{
		
		//CategoryDtoToCategory
		Category category = this.modelMapper.map(categoryDto, Category.class);
		
		//System.out.println(category);
		
		Category savedCategory = this.categoryRepo.save(category);
		
		
		//CategoryToCategoryDto
		return this.modelMapper.map(savedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "CategoryId", categoryId));
		
		//CategoryDtoToCategory
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		
		Category updatedCategory = this.categoryRepo.save(category);
		
		
		//CategoryToCategoryDto
		CategoryDto categorydto = this.modelMapper.map(updatedCategory, CategoryDto.class);
		
		return categorydto;
	}

	@Override
	public CategoryDto getCateogoryById(Integer categoryId) {
		
		Optional<Category> category = this.categoryRepo.findById(categoryId);
		Category cat = category.orElseThrow(()-> new ResourceNotFoundException("Category", "CategoryId", categoryId));
		/*
		CategoryDto categorydto = this.modelMapper.map(cat, CategoryDto.class);
		return categorydto;
		*/
		return this.modelMapper.map(cat, CategoryDto.class);
		
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		
		//CategoryDtoToCategory
		List<Category> category1 = this.categoryRepo.findAll();
		
		//CategoryToCategoryDto
		List<CategoryDto> categorydto = category1.stream().map(category->this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
		
		return categorydto;
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		
		Optional<Category> category = this.categoryRepo.findById(categoryId);
		Category cat = category.orElseThrow(()-> new ResourceNotFoundException("Category", "CategoryId", categoryId));
		this.categoryRepo.delete(cat);

	}

}
