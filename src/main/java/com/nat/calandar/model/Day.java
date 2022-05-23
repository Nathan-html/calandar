package com.nat.calandar.model;

import java.time.LocalDate;
import java.util.Random;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "day_")
public class Day {

	private static Random random = new Random();
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private LocalDate date;
	
	@Min(value=20, message="Le nombre de points doit être supérieur ou égale à 20")
	@Max(value=50, message="Le nombre de points doit être inférieur ou égale à 50")
	private int nbPoints;

	@OneToOne
	private Gif gif;
	
	public Day(LocalDate date) {
		this(date, 20 + random.nextInt(31));
	}

	public Day(LocalDate date, int nbPoints) {
		this.date = date;
		this.nbPoints = nbPoints;
	}

	public String toString() {
		return date.getDayOfMonth() + "/" +  date.getMonthValue();
	}
	
}