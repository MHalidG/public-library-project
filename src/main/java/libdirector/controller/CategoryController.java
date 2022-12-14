package libdirector.controller;

import libdirector.domain.entities.Category;
import libdirector.domain.requestdto.CategorySaveDTO;
import libdirector.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {

	private CategoryService categoryService;

	//42
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Category> updateCategory(@PathVariable Long id, @Valid @RequestBody CategorySaveDTO categorySaveDTO){


		return new ResponseEntity<>(categoryService.updateCategory(categorySaveDTO,id),HttpStatus.CREATED);
	}

	//41
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Category> saveCategory(@Valid @RequestBody CategorySaveDTO categorySaveDTO) {

		return new ResponseEntity<>(categoryService.saveCategory(categorySaveDTO),HttpStatus.CREATED);

	}

	//40
	@GetMapping("/{id}")
	public ResponseEntity<CategorySaveDTO> getCategoryById(@PathVariable Long id,CategorySaveDTO categorySaveDTO){
		categoryService.getCategoryById(id,categorySaveDTO);


		return new ResponseEntity<>(categoryService.getCategoryById(id,categorySaveDTO),HttpStatus.OK);

	}

	//43
	@DeleteMapping("/{id}")
	public ResponseEntity<Category> deleteAuthor(@PathVariable Long id){

		return new ResponseEntity<>(categoryService.deleteCategory(id),HttpStatus.OK);
	}

	//39
	@GetMapping
	public ResponseEntity<Page<CategorySaveDTO>> getAllCategoriesByPage(@RequestParam(required = false, value = "page", defaultValue = "0") int page,
																   @RequestParam(required = false,value = "size", defaultValue = "20") int size,
																   @RequestParam(required = false,value = "sort", defaultValue = "name") String prop,
																   @RequestParam(required = false,value = "type", defaultValue = "ASC") Sort.Direction direction
	){
		Pageable pageable = PageRequest.of(page,size,Sort.by(direction,prop));
		Page<CategorySaveDTO> categoryDTOPage = categoryService.getCategoriesPage(pageable);
		return ResponseEntity.ok(categoryDTOPage);
	}




}