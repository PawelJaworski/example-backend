package pl.pjaworski.examplebackend.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public record PolicyHolder(String name, String surname) {
}
