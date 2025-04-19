package com.cvgaviao.quarkus.jakartadata;

import jakarta.persistence.Embeddable;

@Embeddable
public record Address(String street, String city, String postcode) {}
