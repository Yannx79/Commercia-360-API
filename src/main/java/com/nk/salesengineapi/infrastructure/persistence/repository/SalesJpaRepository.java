package com.nk.salesengineapi.infrastructure.persistence.repository;

import com.nk.salesengineapi.infrastructure.persistence.entity.SalesEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesJpaRepository extends IGenericRepository<SalesEntity, Long> {
}
