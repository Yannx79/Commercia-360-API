package com.nk.salesengineapi.infrastructure.persistence.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class SalesEntity {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "sales_time",
            joinColumns = @JoinColumn(name = "sales_id"),
            inverseJoinColumns = @JoinColumn(name = "time_id")
    )
    @JsonManagedReference
    private Set<TimeEntity> times = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "sales_store",
            joinColumns = @JoinColumn(name = "sales_id"),
            inverseJoinColumns = @JoinColumn(name = "store_id")
    )
    @JsonManagedReference
    private Set<StoreEntity> stores = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "sales_product",
            joinColumns = @JoinColumn(name = "sales_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @JsonManagedReference
    private Set<ProductEntity> products = new HashSet<>();

}
