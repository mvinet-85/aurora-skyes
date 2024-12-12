package com.esiea.auroraskyesdbaccess.utilisateur.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "utilisateur")
public class UtilisateurEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "utilisateur_id_generator")
	@SequenceGenerator(name = "utilisateur_id_generator", sequenceName = "seq_utilisateur", allocationSize = 1)
	private Long id;

	@Column(nullable = false)
	private String nom;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String motDePasse;

}
