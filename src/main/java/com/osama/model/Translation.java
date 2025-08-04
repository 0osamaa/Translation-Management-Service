package com.osama.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Translation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String key;

    @Column(nullable = false)
    private String locale;

    @Column(nullable = false)
    private String content;

    @Column
    private String tags;
}
