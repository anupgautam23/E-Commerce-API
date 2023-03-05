package com.masai.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.masai.Exception.CategoryNotFoundException;
import com.masai.Model.Category;

@Service
public interface CategoryService {
		
	public Category addCategory(Category category) throws CategoryNotFoundException ;
	public Category deleteCategory(Integer CatId) throws CategoryNotFoundException ;
	public List<Category > viewAllCategories() throws CategoryNotFoundException ;
	public Category findCateoryById(Integer Id)throws CategoryNotFoundException;
}
