package libdirector.controller;

import libdirector.domain.entities.Book;
import libdirector.domain.entities.Loan;
import libdirector.domain.requestdto.LoanSaveDTO;
import libdirector.domain.responsedto.LoanResponseDTO;
import libdirector.service.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@RestController
@RequestMapping("/loans")
@AllArgsConstructor
public class LoanController {

	private LoanService loanService;

	//24
	@PostMapping
	@PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
	public ResponseEntity<Loan> makeLoan(@Valid @RequestBody LoanSaveDTO loanSaveDTO) {

		Loan loan=loanService.createLoan(loanSaveDTO);
		return ResponseEntity.ok(loan);
	}

	//25
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
	public ResponseEntity<Loan> updateLoan(@Valid @RequestBody LoanSaveDTO loanFinishDTO, @PathVariable Long id){

		Loan loan=loanService.updateLoan(loanFinishDTO,id);

		return ResponseEntity.ok(loan);

	}
	//20
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('STAFF') or hasRole('MEMBER')")
	public ResponseEntity<LoanResponseDTO> userGetLoanById(HttpServletRequest request, @RequestParam(value = "id") Long loanId){
	Long userId=(Long)request.getAttribute("id");
	LoanResponseDTO loanDTO=loanService.getUserLoanById(userId,loanId);
	return ResponseEntity.ok(loanDTO);
	}

	//23
	@GetMapping("/auth/{loanId}")
	@PreAuthorize("hasRole('ADMIN')or hasRole('STAFF')")
	public ResponseEntity<Loan> getLoanById(@RequestParam("loanId") Long loanId){
		Loan loan=loanService.getLoanById(loanId);

		return ResponseEntity.ok(loan);
	}
	//19
	@GetMapping
	@PreAuthorize("hasRole('ADMIN') or hasRole('STAFF') or hasRole('MEMBER')")
	public ResponseEntity<Page<LoanResponseDTO>> getUserLoansByPage(HttpServletRequest request,
																   @RequestParam(required = false, value = "page", defaultValue = "0") int page,
																   @RequestParam(required = false,value = "size", defaultValue = "20") int size,
																   @RequestParam(required = false,value = "sort", defaultValue = "loanDate") String prop,
																   @RequestParam(required = false,value = "type", defaultValue = "DESC") Sort.Direction direction
	){
		Long userId= (Long) request.getAttribute("id");
		Pageable pageable = PageRequest.of(page,size,Sort.by(direction,prop));
		Page<LoanResponseDTO> loanResponseDTO =loanService.getUserLoans(pageable,userId);
		return ResponseEntity.ok(loanResponseDTO);
	}


	//21
	@GetMapping("/user/{userId}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
	public ResponseEntity<Page<Loan>> adminGetUserLoans(@PathVariable Long userId,
														@RequestParam(required = false, value = "page", defaultValue = "0") int page,
														@RequestParam(required = false,value = "size", defaultValue = "20") int size,
														@RequestParam(required = false,value = "sort", defaultValue = "loanDate") String prop,
														@RequestParam(required = false,value = "type", defaultValue = "DESC") Sort.Direction direction
	){
	Pageable pageable=PageRequest.of(page,size,Sort.by(direction,prop));

		Page<Loan> userLoans= loanService.getUserLoansByAdmin(pageable,userId);
		return ResponseEntity.ok(userLoans);
	}
	//22
	@GetMapping("/user/{bookId}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
	public ResponseEntity<Page<Book>> adminGetBookLoans(@PathVariable Long bookId,
														@RequestParam(required = false, value = "page", defaultValue = "0") int page,
														@RequestParam(required = false,value = "size", defaultValue = "20") int size,
														@RequestParam(required = false,value = "sort", defaultValue = "loanDate") String prop,
														@RequestParam(required = false,value = "type", defaultValue = "DESC") Sort.Direction direction
	){
		Pageable pageable=PageRequest.of(page,size,Sort.by(direction,prop));

		Page<Book> userLoans= loanService.getBookLoansByAdmin(pageable,bookId);
		return ResponseEntity.ok(userLoans);
	}


}
