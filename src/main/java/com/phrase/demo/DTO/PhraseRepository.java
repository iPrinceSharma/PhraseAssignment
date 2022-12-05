package com.phrase.demo.DTO;


import com.phrase.demo.DTO.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhraseRepository extends CrudRepository<Configuration, Long> { }
