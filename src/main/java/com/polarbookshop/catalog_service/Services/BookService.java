package com.polarbookshop.catalog_service.Services;

import com.polarbookshop.catalog_service.Entitys.Book;
import com.polarbookshop.catalog_service.Exceptions.BookAlreadyExistsException;
import com.polarbookshop.catalog_service.Exceptions.BookNotFoundExeption;
import com.polarbookshop.catalog_service.Repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Iterable<Book> viewBookList(){
        return bookRepository.findAll();
    }
    public Book viewBookDetails(String isbn){
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(()-> new BookNotFoundExeption(isbn));
    }
    public Book addBookToCatalog(Book book) throws BookAlreadyExistsException {
        if (bookRepository.existsByIsbn(book.isbn())){
            throw  new BookAlreadyExistsException(book.isbn());
        }
        return bookRepository.save(book);
    }
    @Transactional
    public void removeBookFromCatalog(String isbn){
        bookRepository.deleteByIsbn(isbn);
    }

    public Book editBookDetails(String isbn,Book book){
        return bookRepository.findByIsbn(isbn)
                .map(existingBook -> {
                    var bookUpdate = new Book(
                            existingBook.id(),
                            existingBook.isbn(),
                            book.title(),
                            book.author(),
                            book.price(),
                            book.publisher(),
                            existingBook.createdDate(),
                            existingBook.lastModifiedDate(),
                            existingBook.version()
                    );
                    return bookRepository.save(bookUpdate);
                })
                .orElseGet(
                        () -> {
                            try {
                                return addBookToCatalog(book);
                            } catch (BookAlreadyExistsException e) {
                                throw new RuntimeException(e);
                            }
                        }
                );
    }


}
