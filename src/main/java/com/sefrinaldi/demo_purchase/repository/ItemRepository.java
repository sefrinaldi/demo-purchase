package com.sefrinaldi.demo_purchase.repository;

import com.sefrinaldi.demo_purchase.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Created : 03/10/24 - 15.16
 * @Author : caniago
 */

@Repository
public interface ItemRepository extends JpaRepository<Items, Long> {
}
