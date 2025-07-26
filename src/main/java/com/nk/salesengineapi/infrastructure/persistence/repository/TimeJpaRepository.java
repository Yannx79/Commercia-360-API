package com.nk.salesengineapi.infrastructure.persistence.repository;

import com.nk.salesengineapi.infrastructure.persistence.entity.TimeEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeJpaRepository extends IGenericRepository<TimeEntity, Long> {
}
