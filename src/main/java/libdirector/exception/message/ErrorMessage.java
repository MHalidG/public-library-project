package libdirector.exception.message;

public class ErrorMessage {

	public final static String RESOURCE_NOT_FOUND_MESSAGE = "Resource with id %d not found";
	
	public final static String USER_NOT_FOUND_MESSAGE = "User with id %d not found";
	
	public final static String BOOK_NOT_FOUND_MESSAGE = "Book with id %d not found";

	public final static String EMAIL_ALREADY_EXIST = "Email already exist:%s";

	public final static String AUTHOR_NOT_FOUND_MESSAGE = "Author with id %d not found";

	public final static String PUBLISHER_NOT_FOUND_MESSAGE = "Publisher with id %d not found";

	public final static String CATEGORY_NOT_FOUND_MESSAGE = "Category with id %d not found";

	public final static String NOT_PERMITTED_METHOD_MESSAGE = "You dont have any permission to change this value";

	public final static String PASSWORD_NOT_MATCHED = "Your password are not matched";

	public final static String IMAGE_NOT_FOUND_MESSAGE = "ImageFile with id %s not found";


	public final static String BOOK_NOT_RETURNED_IN_TIME = "Not returned books jetz 'expireDate-bookId' :  %s  ";

	public final static String LOAN_TIME_INCORRECT_MESSAGE = "Loan time not correct";

	public final static String BOOK_NOT_AVAILABLE_MESSAGE = "This Book is not available : %s ";

	public final static String BOOK_USED_BY_RESERVATION_MESSAGE = "Book couldn't be deleted.Book is used by a loan";

	public final static String USER_SCORE_NOT_ENOUGH_MESSAGE="User score is not enough. User Score is: %d";

	public final static String EXPIRE_DATE_CANT_CALCULATED_MESSAGE="Expire date cant calculated";

	public final static String ROLE_NOT_FOUND_MESSAGE = "Role with name %s not found";

	public final static String AUTHOR_HAS_RELATION = "Author couldn't be deleted.Author has books";
}
