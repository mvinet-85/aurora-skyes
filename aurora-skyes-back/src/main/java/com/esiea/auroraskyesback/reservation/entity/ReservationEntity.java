package com.esiea.auroraskyesback.reservation.entity;

import com.esiea.auroraskyesback.utilisateur.entity.UtilisateurEntity;
import com.esiea.auroraskyesback.vol.entity.VolEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vol_id", nullable = false)
    private VolEntity vol;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UtilisateurEntity user;

    @Column(nullable = false)
    private String siege;

    @Column(nullable = false)
    private String classe;

}
