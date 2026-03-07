package com.polarbookshop.catalog_service;

import com.polarbookshop.catalog_service.Configuration.GeralConfigurations.DataConfig;
import com.polarbookshop.catalog_service.Entitys.Book;
import com.polarbookshop.catalog_service.Repository.BookRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
@Import(DataConfig.class)
@ActiveProfiles("integration")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryJdbcTests {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private JdbcAggregateTemplate jdbcAggregateTemplate;

    @Test
    public void findBookByIsbnWhenExisting(){
        var bookIsbn = "12345678";
        var book = Book.of(bookIsbn,"Title","Author",12.90);
        jdbcAggregateTemplate.insert(book);
        Optional<Book> actuatorBook = bookRepository.findByIsbn(bookIsbn);

        assertThat(actuatorBook).isPresent();
        assertThat(actuatorBook.get().isbn()).isEqualTo(book.isbn());
    }
}
