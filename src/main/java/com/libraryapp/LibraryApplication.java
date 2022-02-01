package com.libraryapp;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.libraryapp.entities.Book;
import com.libraryapp.entities.User;
import com.libraryapp.services.BookService;
import com.libraryapp.services.NotificationService;
import com.libraryapp.services.UserService;
import com.libraryapp.utils.MidnightApplicationRefresh;

@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }

    @Autowired
    BookService bookService;

    @Autowired
    UserService usService;

    @Autowired
    NotificationService notifService;

    @Autowired
    BCryptPasswordEncoder pwEncoder;

    @Autowired
    MidnightApplicationRefresh midAppRef;

    @Bean
    CommandLineRunner runner() {
        return args -> {

            User user1 = new User("ndriqimbehrami", pwEncoder.encode("admin"), "ndriqimib@gmail.com", "Ndriqim", "Behrami", "Kongresi I Manastirit", "044111111", "Vushtrri");
            user1.setRole("ROLE_ADMIN");

            User user2 = new User("leartaistrefaj", pwEncoder.encode("employee"), "leartaistrefaj@gamail.com", "Learta", "Istrefaj", "Peje", "044222222", "Peje");
            user2.setRole("ROLE_EMPLOYEE");

            User user3 = new User("ahmettahiraj", pwEncoder.encode("user"), "ahmettahiraj@gmail.com", "Ahmet", "Tahiraj", "Deqan", "044333333", "Deqan");
            user3.setRole("ROLE_USER");


            usService.save(user1);
            usService.save(user2);
            usService.save(user3);


            Book book1 = new Book("Inteligjenca Emocionale", "David Thomas, Andrew Hunt", 2006, 1);
            Book book2 = new Book("Shprehi Atomike", "James Clear", 2017, 2);
            Book book3 = new Book("Mendesia", "Carol S. Dweck", 2018, 1);
            Book book4 = new Book("Ikigai", "Hector Garcia", 2017, 4);
            Book book5 = new Book("Everything is f*cked", "Mark Manson", 2019, 5);
            Book book6 = new Book("Fuqia E Mendimit Poizitiv", "Ibrahim El-fekij", 2019, 1);
            Book book7 = new Book("12 Rules For Life", "Jordan B. P", 2021, 3);


            bookService.save(book1);
            bookService.save(book2);
            bookService.save(book3);
            bookService.save(book4);
            bookService.save(book5);
            bookService.save(book6);
            bookService.save(book7);


            midAppRef.midnightApplicationRefresher();
        };
    }
}
