package org.learning.blogapplication.services.implementation;

import org.learning.blogapplication.entities.models.Category;
import org.learning.blogapplication.entities.dto.CategoryDto;
import org.learning.blogapplication.exceptions.ResourceNotFound;
import org.learning.blogapplication.repositries.CategoryRepository;
import org.learning.blogapplication.services.interfaces.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImple implements CategoryService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.categoryDtoToCategory(categoryDto);
        Category savedCategory = this.categoryRepository.save(category);
        return this.categoryToCategoryDto(savedCategory);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer CategoryId) {
        Category category = categoryRepository.findById(CategoryId).orElseThrow(() -> new ResourceNotFound("Category", "Id", CategoryId));
        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        Category updatedCategory = categoryRepository.save(category);
        return this.categoryToCategoryDto(updatedCategory);

    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFound("Category", "Id", categoryId));
        categoryRepository.delete(category);
    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFound("Category", "Id", categoryId));
        return this.categoryToCategoryDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtos = categories.stream().map(cate -> this.categoryToCategoryDto(cate)).collect(Collectors.toList());
        return categoryDtos;
    }


    public CategoryDto categoryToCategoryDto(Category category) {
        return this.modelMapper.map(category, CategoryDto.class);
    }

    public Category categoryDtoToCategory(CategoryDto categoryDto) {
        return this.modelMapper.map(categoryDto, Category.class);
    }


}
