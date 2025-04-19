package com.cvgaviao.quarkus.jakartadata;

import java.util.Set;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Author {
	@Id
    public String ssn;

    @Basic(optional = false)
    public String name;

    public Address address;

    @ManyToMany
    Set<Book> books;
}
