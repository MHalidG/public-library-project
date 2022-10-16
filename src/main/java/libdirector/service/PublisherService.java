package libdirector.service;

import org.springframework.stereotype.Service;

import libdirector.domain.Publisher;
import libdirector.domain.dto.PublisherDTO;
import libdirector.dto.mapper.PublisherMapper;
import libdirector.repository.PublisherRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PublisherService {

	private PublisherRepository publisherRepository;
	private PublisherMapper publisherMapper;

	public Publisher savePublisher(PublisherDTO publisherDTO) {
		Publisher publisher = publisherMapper.publisherDTOToPublisher(publisherDTO);
		publisherRepository.save(publisher);
		return publisher;
	}

	public Publisher getPublisherById(Long id) {
		return publisherRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
	}
}
