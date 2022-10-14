package libdirector.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import libdirector.dto.PublisherDTO;
import libdirector.service.PublisherService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/publishers")
@AllArgsConstructor
public class PublisherController {

    private PublisherService publisherService;



    @PostMapping("/add")
    //TO DO: PreAuthorize()admin eklenecek
    public ResponseEntity<PublisherDTO> savePublisher(@Valid @RequestBody PublisherDTO publisherDTO) {
    	
    	publisherService.savePublisher(publisherDTO);
    	
        return new ResponseEntity<>(HttpStatus.CREATED);

    }



}