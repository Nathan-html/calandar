package com.nat.calandar.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Emotion {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message="Merci de donner un nom à l''émotion")
	private String nom;
	
	private String code;

	public Emotion
			(String nom, String code) {
		this.nom = nom;
		this.code = code;
	}

}