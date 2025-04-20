package com.cvgaviao.quarkus.jakartadata;

import static jakarta.ws.rs.core.Response.Status.CREATED;
import static jakarta.ws.rs.core.Response.Status.NO_CONTENT;

import org.jboss.resteasy.reactive.RestResponse;

import io.quarkus.websockets.next.PathParam;
import io.smallrye.mutiny.Uni;
import jakarta.data.repository.Delete;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;

@Path("library")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class LibraryResource {

	@Inject
	LibraryService libraryService;

	@POST
	@Path("books")
	public Uni<RestResponse<Book>> create(Book book) {
		if (book == null || book.getIsbn().isBlank()) {
			throw new WebApplicationException("Id was invalidly set on request.", 422);
		}

		return libraryService.createBook(book).map(b -> RestResponse.ResponseBuilder.create(CREATED, b).build());
	}

	@Delete
	@Path("books/{isbn}")
	public Uni<RestResponse<Void>> delete(@PathParam String isbn) {
		if (isbn == null || isbn.isBlank()) {
			throw new WebApplicationException("Id was invalidly set on request.", 422);
		}

		return libraryService.deleteBookById(isbn).replaceWith(RestResponse.status(NO_CONTENT));
	}

}
