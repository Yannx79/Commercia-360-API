package com.nk.salesengineapi.infrastructure.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class TimeEntity {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private String description;
    private String monthCode;
    private String monthDescription;
    private String yearCode;
    private String weekCode;
    private String weekDescription;

    @ManyToMany(mappedBy = "times")
    @JsonBackReference
    private Set<SalesEntity> sales = new HashSet<>();

}
