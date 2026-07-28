package com.library;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.library.service.BookServiceDI;

public class LibraryManagementApplication {

    public static void main(String[] args) {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContextDI.xml");

        BookServiceDI service =
                context.getBean("bookService", BookServiceDI.class);

        service.displayService();

        ((ClassPathXmlApplicationContext) context).close();
    }
}