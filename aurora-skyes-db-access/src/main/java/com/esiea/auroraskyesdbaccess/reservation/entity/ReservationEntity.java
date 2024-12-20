package com.esiea.auroraskyesdbaccess.reservation.entity;

import com.esiea.auroraskyesdbaccess.reservation.model.Classe;
import com.esiea.auroraskyesdbaccess.utilisateur.entity.UtilisateurEntity;
import com.esiea.auroraskyesdbaccess.vol.entity.VolEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "reservation")
public class ReservationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservation_id_generator")
	@SequenceGenerator(name = "reservation_id_generator", sequenceName = "seq_reservation", allocationSize = 1)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "vol_id", nullable = false)
	private VolEntity vol;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private UtilisateurEntity user;

	@Column(nullable = false)
	private String siege;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Classe classe;

	@Column(nullable = false)
	private double prix;

}
