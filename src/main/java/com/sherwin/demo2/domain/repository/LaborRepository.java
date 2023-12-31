package com.sherwin.demo2.domain.repository;

import com.sherwin.demo2.domain.entity.LaborEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LaborRepository extends CrudRepository<LaborEntity, Integer>{
}
