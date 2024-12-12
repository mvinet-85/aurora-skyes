package com.esiea.auroraskyesdbaccess.reservation.entity;

import com.esiea.auroraskyesdbaccess.reservation.model.Classe;
import com.esiea.auroraskyesdbaccess.vol.entity.VolEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "reservationExternal")
public class ReservationExternalEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservationExternal_id_generator")
	@SequenceGenerator(name = "reservationExternal_id_generator", sequenceName = "seq_reservationExternal", allocationSize = 1)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "vol_id", nullable = false)
	private VolEntity vol;

	@Column(nullable = false)
	private Long userId;

	@Column(nullable = false)
	private String siege;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Classe classe;

	@Column(nullable = false)
	private double prix;

}
