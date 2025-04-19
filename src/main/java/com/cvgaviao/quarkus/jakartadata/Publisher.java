package com.cvgaviao.quarkus.jakartadata;

import java.util.Set;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Publisher {
	@Id
    public long id;

    @Basic(optional = false)
    public String name;

    @OneToMany(mappedBy = Book_.PUBLISHER)
    Set<Book> books;
}
