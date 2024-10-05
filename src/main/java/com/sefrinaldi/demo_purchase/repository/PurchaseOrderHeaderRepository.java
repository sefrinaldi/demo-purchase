package com.sefrinaldi.demo_purchase.repository;

import com.sefrinaldi.demo_purchase.entity.PoH;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Created : 04/10/24 - 22.20
 * @Author : caniago
 */

@Repository
public interface PurchaseOrderHeaderRepository extends JpaRepository<PoH, Long> {
}
