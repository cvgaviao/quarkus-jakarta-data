package com.cvgaviao.quarkus.jakartadata;

import org.hibernate.reactive.mutiny.Mutiny;

public interface ReactiveDataRepository<T, K> {

	Mutiny.StatelessSession session();
}
