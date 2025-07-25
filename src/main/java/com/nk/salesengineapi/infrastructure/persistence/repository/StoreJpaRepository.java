package com.nk.salesengineapi.infrastructure.persistence.repository;

import com.nk.salesengineapi.infrastructure.persistence.entity.StoreEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreJpaRepository extends IGenericRepository<StoreEntity, Long> {
}
