package com.jogo.deepify.repositories;

import com.jogo.deepify.entities.urlEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepo extends CrudRepository<urlEntity, Long> {
    urlEntity findByDestination(String destination);
    urlEntity findByUrl(String url);
}
