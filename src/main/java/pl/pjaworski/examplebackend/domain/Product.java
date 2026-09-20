package pl.pjaworski.examplebackend.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public record Product(String name, String description) {
}
