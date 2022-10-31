package libdirector.controller;

import libdirector.domain.entities.Publisher;
import libdirector.domain.requestdto.PublisherSaveDTO;
import libdirector.service.PublisherService;
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
@RequestMapping("/publishers")
@AllArgsConstructor
public class PublisherController {

	private PublisherService publisherService;

	//29
	@PostMapping()
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Publisher> savePublisher(@Valid @RequestBody PublisherSaveDTO publisherDTO) {

		return new ResponseEntity<>(publisherService.savePublisher(publisherDTO), HttpStatus.CREATED);

	}

	//30
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Publisher> updatePublisher(@PathVariable Long id, @Valid @RequestBody PublisherSaveDTO publisherSaveDTO){


		return new ResponseEntity<>(publisherService.updatePublisher(publisherSaveDTO,id),HttpStatus.CREATED);
	}

	//28
	@GetMapping("/{id}")
	public ResponseEntity<PublisherSaveDTO> getPublisherById(@PathVariable Long id,PublisherSaveDTO publisherSaveDTO){
		publisherService.getPublisherById(id,publisherSaveDTO);


		return new ResponseEntity<>(publisherService.getPublisherById(id,publisherSaveDTO),HttpStatus.OK);

	}

	//31
	@DeleteMapping("/{id}")
	public ResponseEntity<Publisher> deletePublisher(@PathVariable Long id){

		return new ResponseEntity<>(publisherService.deletePublisher(id),HttpStatus.OK);
	}

	//27
	@GetMapping
	public ResponseEntity<Page<PublisherSaveDTO>> getAllPublishersByPage(@RequestParam(required = false, value = "page", defaultValue = "0") int page,
																   @RequestParam(required = false,value = "size", defaultValue = "20") int size,
																   @RequestParam(required = false,value = "sort", defaultValue = "name") String prop,
																   @RequestParam(required = false,value = "type", defaultValue = "ASC") Sort.Direction direction
	){
		Pageable pageable = PageRequest.of(page,size,Sort.by(direction,prop));
		Page<PublisherSaveDTO> authorDTOPage = publisherService.getPublishersPage(pageable);
		return ResponseEntity.ok(authorDTOPage);
	}





}