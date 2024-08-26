package com.rochman.book_rent.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name ="book_code")
    private String bookCode;

    @Column(name = "title")
    private String title;

    @Column(name = "stock")
    private Integer stock;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Book> books;

    @ManyToMany
    @JoinTable(
            name = "book_category",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();
}
