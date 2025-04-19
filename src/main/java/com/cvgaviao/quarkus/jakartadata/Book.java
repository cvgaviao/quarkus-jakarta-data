package com.cvgaviao.quarkus.jakartadata;

import static jakarta.persistence.EnumType.STRING;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Set;

import org.hibernate.annotations.NaturalId;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Book {
	@ManyToMany(mappedBy = Author_.BOOKS)
	Set<Author> authors;

	@Id
	public String isbn;

	@Basic(optional = false)
	public int pages;

	public BigDecimal price;

	@NaturalId(mutable = true)
	public LocalDate publicationDate;

	@ManyToOne
	public Publisher publisher;

	public BigInteger quantitySold;

	public String text;

	@NaturalId
	public String title;

	@Enumerated(STRING)
	@Basic(optional = false)
	public Type type = Type.Book;

	public Book() {
	}

	public Book(String isbn, String title, String text) {
		this.isbn = isbn;
		this.title = title;
		this.text = text;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Override
	public String toString() {
		return isbn + " : " + title + " [" + type + "]";
	}
}
