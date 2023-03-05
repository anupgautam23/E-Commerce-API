package com.masai.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.CategoryNotFoundException;
import com.masai.Model.Category;
import com.masai.Repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	
	@Autowired
	private CategoryRepository catRepo;
	

	@Override
	public Category addCategory(Category category) throws CategoryNotFoundException {
	Category existCat	=catRepo.findByName(category.getName());
	if(existCat!=null) throw new CategoryNotFoundException("Category is already added");
	Category cat =catRepo.save(category);
		return cat;
	}


	@Override
	public Category deleteCategory(Integer CatId) throws CategoryNotFoundException {
		Optional<Category> existCat=catRepo.findById(CatId);
		if(existCat.isEmpty()) throw new CategoryNotFoundException("Category not found ");
		catRepo.delete(existCat.get());
		return existCat.get();
	}


	@Override
	public List<Category> viewAllCategories() throws CategoryNotFoundException {
		List<Category> existCats =catRepo.findAll();
		if(existCats.size()==0) throw new CategoryNotFoundException("Category not found");
		
		return existCats;
	}


	@Override
	public Category findCateoryById(Integer Id) throws CategoryNotFoundException {
		Optional<Category> categoryOPT =catRepo.findById(Id);
		if(!categoryOPT.isPresent()) throw new CategoryNotFoundException("Category not found");
		
		return categoryOPT.get();
	}

	

}
