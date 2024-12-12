package com.esiea.auroraskyesdbaccess.aeroport.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "aeroport")
public class AeroportEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aeroport_id_generator")
	@SequenceGenerator(name = "aeroport_id_generator", sequenceName = "seq_aeroport", allocationSize = 1)
	private Long id;

	@Column(nullable = false)
	private String nom;

	@Column(nullable = false)
	private String ville;

}