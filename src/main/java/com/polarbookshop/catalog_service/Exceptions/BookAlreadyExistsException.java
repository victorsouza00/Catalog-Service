package com.polarbookshop.catalog_service.Exceptions;

public class BookAlreadyExistsException extends Throwable {
    public BookAlreadyExistsException(String isbn) {
        super("The book with ISBN "+ isbn + " Já existe ");
    }
}
