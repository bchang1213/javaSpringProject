package com.brianchang.web.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.brianchang.web.models.Category;
import com.brianchang.web.repositories.CategoryRepository;

@Service
public class CategoryService {

	private CategoryRepository categoryRepo;
	
	public CategoryService(CategoryRepository categoryRepo) {
		this.categoryRepo = categoryRepo;
	}
	
	public Category findOrCreateCategory(String name) {
		Category categoryByName = categoryRepo.findByName(name);
		if(categoryByName == null) {
			Category category = new Category();
			category.setName(name);
			return categoryRepo.save(category);
		}
		else {
			return categoryByName;
		}
	}
	
	public List<Category> getAllCategories(){
		return categoryRepo.findAll();
	}
	
	
	
	
	
}
