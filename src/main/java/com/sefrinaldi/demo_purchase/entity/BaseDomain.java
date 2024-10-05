package com.sefrinaldi.demo_purchase.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

/**
 * @Created : 02/10/24 - 10.55
 * @Author : caniago
 */

@MappedSuperclass
@Data
public abstract class BaseDomain {

    @Column(name = "CREATED_BY")
    private String createdBy;
    private String updatedBy;
    @Column(name = "CREATED_DATETIME")
    @CreationTimestamp
    private Date createdDatetime;
    @UpdateTimestamp
    private Date updatedDatetime;

    @PrePersist
    private void onPrePersist() {
        this.setCreatedBy("SYSTEM");
    }

    @PreUpdate
    private void onPreUpdate() {
        this.setUpdatedBy("SYSTEM");
    }
}
