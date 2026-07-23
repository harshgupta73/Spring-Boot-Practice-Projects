package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.CRUDApplication.CrudApplication;
import model.Book;
import repo.BookRepo;

@RestController
public class BookController {

    
	
	@Autowired
	private BookRepo bookRepo;

    
	
	@GetMapping("/getAllBooks")
	public ResponseEntity<List<Book>> getAllBooks() {
		// TODO Auto-generated method stub
		try {
			List<Book> bookList = new ArrayList<>();
			bookRepo.findAll().forEach(bookList::add);
			
			if(bookList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(bookList,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/getBookById/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable Long id) {
		// TODO Auto-generated method stub
		Optional<Book> bookData= bookRepo.findById(id);
		
		if(bookData.isPresent()) {
			return new ResponseEntity<>(bookData.get(),HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/addBook")
	public ResponseEntity<Book> addBook(@RequestBody Book book) {
		// TODO Auto-generated method stub
		Book bookObj=bookRepo.save(book);
		
		return new ResponseEntity<>(bookObj, HttpStatus.OK);
	}
	
	@PostMapping("/updateBookById{id}")
	public void updateBook(@PathVariable long id, @RequestBody Book newBookData) {
		// TODO Auto-generated method stub
		Optional<Book> oldBookData = bookRepo.findById(id);
		
		if(oldBookData.isPresent()) {
			Book updatedBookData=oldBookData.get();
			updatedBookData.setTitle(newBookData.getTitle());
			updatedBookData.setAuthor(newBookData.getAuthor())
		}
	}
	
	@DeleteMapping
	public void deleteBookById() {
		// TODO Auto-generated method stub

	}
}
