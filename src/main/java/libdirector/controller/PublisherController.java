package libdirector.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import libdirector.domain.Publisher;
import libdirector.domain.dto.PublisherDTO;
import libdirector.service.PublisherService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/publishers")
@AllArgsConstructor
public class PublisherController {

	private PublisherService publisherService;

	@PostMapping()
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Publisher> savePublisher(@Valid @RequestBody PublisherDTO publisherDTO) {

		return new ResponseEntity<>(publisherService.savePublisher(publisherDTO), HttpStatus.CREATED);

	}

}