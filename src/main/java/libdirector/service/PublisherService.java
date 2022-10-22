package libdirector.service;

import libdirector.domain.requestdto.PublisherSaveDTO;
import libdirector.exception.message.ErrorMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import libdirector.domain.entities.Publisher;
import libdirector.domain.mapper.PublisherMapper;
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

	public Publisher deletePublisher(Long id){
		Publisher publisher=publisherRepository.findById(id).orElseThrow(()-> new RuntimeException(String.format(ErrorMessage.AUTHOR_NOT_FOUND_MESSAGE)));
		if (!publisher.getPublisherBooks().isEmpty()) {
			throw new RuntimeException(String.format(ErrorMessage.AUTHOR_HAS_RELATION));
		}
		publisherRepository.deleteById(publisher.getId());

		return publisher;
	}



	public PublisherSaveDTO getPublisherById(Long id, PublisherSaveDTO publisherSaveDTO) {

		Publisher publisher =publisherRepository.findById(id).orElseThrow(()-> new RuntimeException(String.format(ErrorMessage.AUTHOR_NOT_FOUND_MESSAGE)));
		publisherSaveDTO.setName(publisher.getName());
		publisherSaveDTO.setId(publisher.getId());
		return publisherSaveDTO;
	}




	public Publisher updatePublisher(PublisherSaveDTO publisherSaveDTO,Long id) {
		Publisher updatingPublisher=publisherRepository.findById(id).orElseThrow(()-> new
				RuntimeException(String.format(ErrorMessage.AUTHOR_NOT_FOUND_MESSAGE)));
		updatingPublisher.setName(publisherSaveDTO.getName());
		publisherRepository.save(updatingPublisher);
		return updatingPublisher;
	}


	public Page<PublisherSaveDTO> getPublishersPage(Pageable pageable) {
		Page<PublisherSaveDTO> publisher = publisherRepository.findAllPublishersWithPage(pageable);

		return publisher;
	}
}
