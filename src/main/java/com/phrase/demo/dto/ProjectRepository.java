package com.phrase.demo.dto;


import com.phrase.demo.dto.request.TMSConfiguration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<TMSConfiguration, Long> { }
