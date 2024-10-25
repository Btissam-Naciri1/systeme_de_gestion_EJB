package com.example.ejbexam.entity;

import jakarta.persistence.*;

// Specify the entity and its corresponding table name if necessary
@Entity
public class CD {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String artist;

    @Column(name = "is_available") // Optional: Specify the column name in the database
    private boolean available; // Indicates if the CD is available for borrowing

    // Default constructor (required by JPA)
    public CD() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
