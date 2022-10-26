package libdirector.service;

import libdirector.domain.entities.Category;
import libdirector.domain.mapper.CategoryMapper;
import libdirector.domain.requestdto.CategorySaveDTO;
import libdirector.exception.message.ErrorMessage;
import libdirector.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryService {

	private CategoryMapper categoryMapper;

	private CategoryRepository categoryRepository;


	public Category saveCategory(CategorySaveDTO categorySaveDTO){
		Category category= categoryMapper.categorySaveDTOToCategory(categorySaveDTO);
		category.setSequence(category.getSequence());
		categoryRepository.save(category);
		return category;
	}

	public Category deleteCategory(Long id){
		Category category=categoryRepository.findById(id).orElseThrow(()-> new RuntimeException(String.format(ErrorMessage.CATEGORY_NOT_FOUND_MESSAGE)));
		if (!category.getCategoryBooks().isEmpty()) {
			throw new RuntimeException(String.format(ErrorMessage.CATEGORY_HAS_RELATION));
		}
		categoryRepository.deleteById(category.getId());

		return category;
	}

	public CategorySaveDTO getCategoryById(Long id, CategorySaveDTO categorySaveDTO) {

		Category category =categoryRepository.findById(id).orElseThrow(()-> new RuntimeException(String.format(ErrorMessage.CATEGORY_NOT_FOUND_MESSAGE)));
		categorySaveDTO.setName(category.getName());
		categorySaveDTO.setId(category.getId());
		categorySaveDTO.setSequence(category.getSequence());
		return categorySaveDTO;
	}


	public Category updateCategory(CategorySaveDTO categorySaveDTO,Long id) {
		Category updatingCategory=categoryRepository.findById(id).orElseThrow(()-> new
				RuntimeException(String.format(ErrorMessage.CATEGORY_NOT_FOUND_MESSAGE)));
		updatingCategory.setName(categorySaveDTO.getName());
		categoryRepository.save(updatingCategory);
		return updatingCategory;
	}


	public Page<CategorySaveDTO> getCategoriesPage(Pageable pageable) {
		Page<CategorySaveDTO> category = categoryRepository.findAllCategoriesWithPage(pageable);

		return category;
	}

}
