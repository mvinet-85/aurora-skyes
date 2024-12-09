package com.esiea.auroraskyesback.monnaie.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "monnaie")
public class MonnaieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private double taux;

    public MonnaieEntity(String nom, double taux) {
        this.nom = nom;
        this.taux = taux;
    }

    public MonnaieEntity() {}
}