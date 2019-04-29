package com.crackerStudents.projectApp.domain;

import org.hibernate.annotations.GenericGenerator;

import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "tag")
    private String tag;

    @ManyToMany(mappedBy = "tags")
    private Set<Pack> packs = new HashSet<>();
}