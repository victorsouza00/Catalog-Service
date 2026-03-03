package com.polarbookshop.catalog_service.demo;

import com.polarbookshop.catalog_service.Entitys.Book;
import com.polarbookshop.catalog_service.Repository.BookRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Profile("testdata")
public class BookDataLoader {
    //vamos acessar spring.profiles.active=testdata
    private BookRepository bookRepository;

    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadBookTest(){
        bookRepository.deleteAll();
        for (int i = 0; i < 20 ; i++) {
            bookRepository.save(Book.of("1234567"+i,"Tittle " + i , "Person " + i , (double) (i + 10)));
        }
    }
}
