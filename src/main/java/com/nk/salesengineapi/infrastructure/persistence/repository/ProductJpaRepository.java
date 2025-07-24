package com.nk.salesengineapi.infrastructure.persistence.repository;

import com.nk.salesengineapi.infrastructure.persistence.entity.ProductEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductJpaRepository extends IGenericRepository<ProductEntity, Long> {
}
