package com.sefrinaldi.demo_purchase.repository;

import com.sefrinaldi.demo_purchase.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Created : 02/10/24 - 16.43
 * @Author : caniago
 */

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
}
