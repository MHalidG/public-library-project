package libdirector.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import libdirector.domain.requestdto.LoanFinishDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import libdirector.domain.entities.Loan;
import libdirector.domain.requestdto.LoanSaveDTO;
import libdirector.service.LoanService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/loans")
@AllArgsConstructor
public class LoanController {

	private LoanService loanService;

	@PostMapping
	@PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
	public ResponseEntity<Loan> makeLoan(@Valid @RequestBody LoanSaveDTO loanSaveDTO) {

		Loan loan=loanService.createLoan(loanSaveDTO);
		return ResponseEntity.ok(loan);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
	public ResponseEntity<Loan> updateLoan(@Valid @RequestBody LoanFinishDTO loanFinishDTO, @PathVariable Long id){

		Loan loan=loanService.updateLoan(loanFinishDTO,id);

		return ResponseEntity.ok(loan);

	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('STAFF') or hasRole('MEMBER')")
	public ResponseEntity<LoanSaveDTO> userGetLoanById(HttpServletRequest request,@RequestParam(value = "id") Long loanId){
	Long userId=(Long)request.getAttribute("id");
	LoanSaveDTO loanSaveDTO=loanService.getUserLoanById(userId,loanId);
 	return ResponseEntity.ok(loanSaveDTO);
	}





}
