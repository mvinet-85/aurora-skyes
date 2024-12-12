package com.esiea.auroraskyesdbaccess.vol.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "volExternal")
public class VolExternalEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "volExternal_id_generator")
	@SequenceGenerator(name = "volExternal_id_generator", sequenceName = "seq_volExternal", allocationSize = 1)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date dateDepart;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date dateArrive;

	@Column(nullable = false)
	private String aeroportDepartCode;

	@Column(nullable = false)
	private String aeroportArriveCode;

	@Column(nullable = false)
	private int placeDisponible;

	@Column(nullable = false)
	private int prix;

}
