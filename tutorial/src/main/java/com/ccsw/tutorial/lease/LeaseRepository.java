package com.ccsw.tutorial.lease;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ccsw.tutorial.lease.model.Lease;

/**
 * 
 * @author ccamposa
 *
 */
public interface LeaseRepository extends CrudRepository<Lease, Long> {
    Page<Lease> findAll(Pageable pageable);

    @Query("select l from Lease l where (:game is null or l.game.id = :game) and (:client is null or l.client.id = :client) and (:date is null or (l.startDate <= :date and l.endDate >= :date))")
    Page<Lease> find(@Param("game") Long game, @Param("client") Long client, @Param("date") LocalDate date,
            Pageable pageable);
}