package com.gta.spring.springboot.junix_opp.servise;

import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

/**
 * Общий интерфейс для сервисов.
 * @param <T> тип сущности
 * @param <RD> тип DTO read
 * @param <ED> тип DTO edit
 * @param <ID> тип идентификатора
 */
public interface BaseCRUDService <T, RD, ED, ID> {

    List<RD> findAll();

    Optional<RD> findById(ID id_);

    T findEntityById(ID id_) ;

    @Transactional
    void create(ED editDTO, Principal principal);

    @Transactional
    void update(ID id_, ED editDTO, Principal principal);

    @Transactional
    void delete(ID id_);

}
