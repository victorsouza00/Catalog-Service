package com.polarbookshop.catalog_service.Exceptions;

public class BookNotFoundExeption extends RuntimeException {
    public BookNotFoundExeption(String isbn) {
        super("A book with ISBN " + isbn + " already exist");
    }
}
