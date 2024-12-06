package com.esiea.auroraskyesback.vol.entity;

import com.esiea.auroraskyesback.aeroport.entity.AeroportEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class VolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date dateDepart;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date dateArrive;

    @ManyToOne
    @JoinColumn(name = "aeroport_depart_id", nullable = false)
    private AeroportEntity aeroportDepart;

    @ManyToOne
    @JoinColumn(name = "aeroport_arrive_id", nullable = false)
    private AeroportEntity aeroportArrivee;

    @Column(nullable = false)
    private int placeDisponible;

    @Column(nullable = false)
    private int prix;

}