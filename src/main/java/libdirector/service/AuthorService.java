package libdirector.service;

import org.springframework.stereotype.Service;

import libdirector.domain.entities.Author;
import libdirector.domain.requestdto.AuthorDTO;
import libdirector.dto.mapper.AuthorMapper;
import libdirector.repository.AuthorRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthorService {

	private AuthorMapper authorMapper;
	
    private AuthorRepository authorRepository;

    
    //@Transactional(readOnly=true)
    public Author saveAuthor(AuthorDTO authorDTO){
        Author author= authorMapper.authorDTOToAuthor(authorDTO);
        authorRepository.save(author);
        return author;

    }

    public AuthorDTO deleteAuthor(AuthorDTO authorDTO){

        authorRepository.deleteById(authorDTO.getId());

     return authorDTO;
    }
    
    
    
    public Author getAuthorById(Long id) {

        return authorRepository.findById(id).orElseThrow(()-> new RuntimeException("No Found"));

    }
}