package com.esiea.auroraskyesdbaccess.aeroport.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "aeroportExternal")
public class AeroportExternalEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aeroportExternal_id_generator")
	@SequenceGenerator(name = "aeroportExternal_id_generator", sequenceName = "seq_aeroportExternal", allocationSize = 1)
	private Long id;

	@Column(nullable = false)
	private String airportCode;

}