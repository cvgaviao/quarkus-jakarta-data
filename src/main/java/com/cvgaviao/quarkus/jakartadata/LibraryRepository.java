package com.cvgaviao.quarkus.jakartadata;

import jakarta.data.repository.Repository;

@Repository
public interface LibraryRepository extends ReactiveCrudRepository<Book, String> {

//	@Delete
//	void delete(String isbn);

//	@Insert
//	void add(Book book);
//
//	@Find
//	Optional<Book> byIsbn(String isbn);

//	@Find
//	Uni<List<Book>> byTitle(@Pattern String title);

//	@Delete
//	Uni<Void> deleteById(@By(ID) String id);

//    @Find
//    @OrderBy(_Book.TITLE)
//    Uni<List<Book>> allBooks();
}
