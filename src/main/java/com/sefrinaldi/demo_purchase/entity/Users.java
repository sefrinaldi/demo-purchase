package com.sefrinaldi.demo_purchase.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

/**
 * @Created : 02/10/24 - 10.52
 * @Author : caniago
 */

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Users extends BaseDomain{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;

}
