package com.ccsw.tutorial.lease.model;

import java.time.LocalDate;

import org.springframework.data.domain.Pageable;

public class LeaseSearchDto {
    private Pageable pageable;
    private Long idGame;
    private Long idClient;
    private LocalDate date;

    /**
     * @return pageable
     */
    public Pageable getPageable() {
        return this.pageable;
    }

    /**
     * @param pageable
     */
    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

    /**
     * @return the idGame
     */
    public Long getIdGame() {
        return idGame;
    }

    /**
     * @param idGame the idGame to set
     */
    public void setIdGame(Long idGame) {
        this.idGame = idGame;
    }

    /**
     * @return the idClient
     */
    public Long getIdClient() {
        return idClient;
    }

    /**
     * @param idClient the idClient to set
     */
    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    /**
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }
}