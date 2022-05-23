package com.nat.calandar.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "customer")
public class User {

	private static final int NB_POINTS_INITIAL = 500;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String nom;
	
	@NotBlank(message="Veuillez préciser votre prénom")
	private String prenom;
	
	@Email(message="Merci de préciser une adresse email au bon format")
	@NotBlank(message="Merci de préciser une adresse email")
	@Column(unique=true)
	@Pattern(regexp="^([A-Za-z0-9-])+(.[A-Za-z0-9-]+)*@hb.com$", message="Votre adresse doit faire partie du nom de domaine hb.com")
	private String email;
	
	@Size(min=3, message="Le mot de passe doit comporter au moins trois caractères")
	private String motDePasse;
	
	@ManyToOne
	@NotNull(message="Merci de choisir un thème")
	private Theme theme;
	
	private int nbPoints;

	private LocalDateTime dateHeureInscription;

	@OneToMany(mappedBy = "customer")
	private List<Gif> gifs;

	public User() {
		nbPoints = NB_POINTS_INITIAL;
		dateHeureInscription = LocalDateTime.now();
		System.out.println(dateHeureInscription);
	}

	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", motDePasse="
				+ motDePasse + ", theme=" + theme + ", nbPoints=" + nbPoints + "]";
	}
	
}