package libdirector.service;

import java.util.List;

import org.springframework.stereotype.Service;

import libdirector.domain.entities.Category;
import libdirector.domain.requestdto.CategorySaveDTO;
import libdirector.dto.mapper.CategoryMapper;
import libdirector.repository.CategoryRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryService {

	private CategoryMapper categoryMapper;

	private CategoryRepository categoryRepository;

	public Category saveCategory(CategorySaveDTO categoryDTO) {

		Category category = categoryMapper.CategoryDTOToCategory(categoryDTO);
		category.setSequence(setSequenceNumber());

		categoryRepository.save(category);
		return category;

	}

	private Integer setSequenceNumber() {
		List<Category> categories = categoryRepository.findAll();
		//categoryRepository.count();
		return categories.size() + 1;
		
	}

	public Category getCategoryById(Long id) {

		return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
	}
}
