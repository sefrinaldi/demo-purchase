package com.sefrinaldi.demo_purchase.repository;

import com.sefrinaldi.demo_purchase.entity.PoD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Created : 05/10/24 - 11.28
 * @Author : caniago
 */

@Repository
public interface PurchaseOrderDetailRepository extends JpaRepository<PoD, Long> {
}
