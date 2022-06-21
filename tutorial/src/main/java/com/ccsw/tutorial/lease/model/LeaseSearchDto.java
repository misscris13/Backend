package com.ccsw.tutorial.lease.model;

import org.springframework.data.domain.Pageable;

public class LeaseSearchDto {
    private Pageable pageable;

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
}