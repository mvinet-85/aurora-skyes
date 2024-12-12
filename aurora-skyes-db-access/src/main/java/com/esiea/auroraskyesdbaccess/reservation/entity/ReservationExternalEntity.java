package com.esiea.auroraskyesdbaccess.reservation.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "reservationExternal")
public class ReservationExternalEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservationExternal_id_generator")
	@SequenceGenerator(name = "reservationExternal_id_generator", sequenceName = "seq_reservationExternal", allocationSize = 1)
	private Long id;

	@Column(nullable = false)
	private Long flightId;

	@Column(nullable = false)
	private Long userId;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "reservation_id", nullable = false)
	private List<OptionEntity> options;

	@Column(nullable = false)
	private double price;

	@Column(nullable = false)
	private String currency;
}

