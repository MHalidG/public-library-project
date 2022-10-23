package libdirector.service;

import libdirector.exception.message.ErrorMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import libdirector.domain.entities.Author;
import libdirector.domain.requestdto.AuthorSaveDTO;
import libdirector.domain.mapper.AuthorMapper;
import libdirector.repository.AuthorRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthorService {

	private AuthorMapper authorMapper;
	
    private AuthorRepository authorRepository;

    public Author saveAuthor(AuthorSaveDTO authorSaveDTO){
        Author author= authorMapper.authorSaveDTOToAuthor(authorSaveDTO);
        authorRepository.save(author);
        return author;
    }

    public Author deleteAuthor(Long id){
        Author author=authorRepository.findById(id).orElseThrow(()-> new RuntimeException(String.format(ErrorMessage.AUTHOR_NOT_FOUND_MESSAGE)));
        if (!author.getAuthorBooks().isEmpty()) {
            throw new RuntimeException(String.format(ErrorMessage.AUTHOR_HAS_RELATION));
        }
        authorRepository.deleteById(author.getId());

     return author;
    }
    
    
    
    public AuthorSaveDTO getAuthorById(Long id, AuthorSaveDTO authorSaveDTO) {

         Author author =authorRepository.findById(id).orElseThrow(()-> new RuntimeException(String.format(ErrorMessage.AUTHOR_NOT_FOUND_MESSAGE)));
         authorSaveDTO.setName(author.getName());
         authorSaveDTO.setId(author.getId());
         return authorSaveDTO;
    }




    public Author updateAuthor(AuthorSaveDTO authorSaveDTO,Long id) {
        Author updatingAuthor=authorRepository.findById(id).orElseThrow(()-> new
                RuntimeException(String.format(ErrorMessage.AUTHOR_NOT_FOUND_MESSAGE)));
        updatingAuthor.setName(authorSaveDTO.getName());
        authorRepository.save(updatingAuthor);
        return updatingAuthor;
    }


    public Page<AuthorSaveDTO> getAuthorsPage(Pageable pageable) {
            Page<AuthorSaveDTO> authors = authorRepository.findAllAuthorsWithPage(pageable);

            return authors;
    }
}