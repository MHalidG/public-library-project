package libdirector.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import libdirector.dto.CategoryDTO;
import libdirector.service.CategoryService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {

	private CategoryService categoryService;

	@PostMapping("/add")
	// TO DO: PreAuthorize()admin eklenecek
	public ResponseEntity<HttpStatus> saveCategory(@Valid @RequestBody CategoryDTO categoryDTO) {

		categoryService.saveCategory(categoryDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);

	}
}