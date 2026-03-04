package com.polarbookshop.catalog_service;


import com.polarbookshop.catalog_service.Entitys.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureWebTestClient
@ActiveProfiles("integration")
class CatalogServiceApplicationTests {
	@Autowired
	private WebTestClient webTestClient;

	@Test
	void whenPostRequestThenBookCreated(){
		var expectedBook =  Book.of("1231234563","title","Author",9.90);
		webTestClient
				.post()
				.uri("/books")
				.bodyValue(expectedBook)
				.exchange()
				.expectStatus().isCreated()
				.expectBody(Book.class).value(bookReleased ->{
					assertThat(bookReleased).isNotNull();
					assertThat(bookReleased.isbn())
							.isEqualTo(expectedBook.isbn());;
				});
	}
}
