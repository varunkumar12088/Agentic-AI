package com.learning.repository;

import com.learning.domain.entity.Tier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TierRepository extends CrudRepository<Tier, Long> {
}
