package com.esiea.auroraskyesback.aeroport.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class AeroportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String ville;

}