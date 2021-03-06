package com.jake.demo.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jake.demo.MockDataProvider.AuthorSeq;
import com.jake.demo.MockDataProvider.BookSeq;
import com.jake.demo.model.Author;
import com.jake.demo.model.Book;
import com.jake.demo.repository.AuthorRepository;
import com.jake.demo.repository.BookRepository;

import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class Mutation {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @GraphQLMutation(name = "addAuthor")
    public Author addAuthor(String name) {
        return authorRepository.save(Author.builder()
                .id(AuthorSeq.nextval())
                .name(name)
                .build());
    }

    @GraphQLMutation(name = "addBook")
    public Book addBook(String name, int authorId) {
        return bookRepository.save(Book.builder()
                .id(BookSeq.nextval())
                .name(name)
                .author(authorRepository.findById(authorId))
                .build());
    }

    @GraphQLMutation(name = "deleteAuthor")
    public boolean deleteAuthor(int id) {
        return authorRepository.delete(id);
    }

    @GraphQLMutation(name = "deleteBook")
    public boolean deleteBook(int id) {
        return bookRepository.delete(id);
    }
}
