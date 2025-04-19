package com.cvgaviao.quarkus.jakartadata;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LibraryService {

	final LibraryRepository libraryRepository;

	public LibraryService(LibraryRepository libraryRepository) {
		this.libraryRepository = libraryRepository;
	}

	public Uni<Book> createBook(Book book) {
		return libraryRepository.save(book);
	}

	public Uni<Void> deleteBookById(String isbn) {
		return libraryRepository.findById(isbn).chain(libraryRepository::delete);
	}
}
