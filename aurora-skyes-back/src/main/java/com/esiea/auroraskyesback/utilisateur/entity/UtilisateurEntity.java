package com.esiea.auroraskyesback.utilisateur.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UtilisateurEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String nom;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String motDePasse;

}