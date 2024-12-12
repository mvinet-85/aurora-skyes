package com.esiea.auroraskyesdbaccess.reservation.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "option")
public class OptionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "option_id_generator")
	@SequenceGenerator(name = "option_id_generator", sequenceName = "seq_option", allocationSize = 1)
	private Long id;

	@Column(nullable = false)
	private String name;
}
