package com.rochman.book_rent.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "categories", cascade = CascadeType.ALL)
    private Set<Book> books = new HashSet<>();
}
