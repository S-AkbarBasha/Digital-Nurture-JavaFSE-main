package com.library.service;

import com.library.repository.BookRepositoryDI;

public class BookServiceDI {

    private BookRepositoryDI bookRepository;

    public void setBookRepository(BookRepositoryDI bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void displayService() {
        System.out.println("Book Service Created");
        bookRepository.displayRepository();
    }
}