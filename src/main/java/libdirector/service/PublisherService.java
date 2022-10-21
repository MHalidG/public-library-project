package libdirector.service;

import libdirector.domain.requestdto.PublisherSaveDTO;
import org.springframework.stereotype.Service;

import libdirector.domain.entities.Publisher;
import libdirector.dto.mapper.PublisherMapper;
import libdirector.repository.PublisherRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PublisherService {

	private PublisherRepository publisherRepository;
	private PublisherMapper publisherMapper;

	public Publisher savePublisher(PublisherSaveDTO publisherDTO) {
		Publisher publisher = publisherMapper.publisherDTOToPublisher(publisherDTO);
		publisherRepository.save(publisher);
		return publisher;
	}

	public Publisher getPublisherById(Long id) {
		return publisherRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
	}
}
