package com.sherwin.demo2.domain.repository;

import com.sherwin.demo2.domain.entity.MaterialEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRespository extends CrudRepository<MaterialEntity, Integer> {
}
