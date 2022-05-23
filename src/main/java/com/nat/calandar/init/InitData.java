package com.nat.calandar.init;

 import com.github.javafaker.Faker;
 import com.github.javafaker.service.FakeValuesService;
 import com.github.javafaker.service.RandomService;
 import com.nat.calandar.DAO.DayDao;
 import com.nat.calandar.DAO.EmotionDao;
 import com.nat.calandar.DAO.ThemeDao;
 import com.nat.calandar.DAO.UserDao;
 import com.nat.calandar.model.Day;
 import com.nat.calandar.model.Emotion;
 import com.nat.calandar.model.Theme;
 import com.nat.calandar.model.User;
 import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

 import java.time.LocalDate;
 import java.time.ZoneId;
 import java.util.*;


@Component
@AllArgsConstructor
public class InitData implements CommandLineRunner {

	private final EmotionDao emotionDao;
	private final ThemeDao themeDao;
	private final UserDao utilisateurDao;
	private final DayDao dayDao;

	private static Random random = new Random();
	private static FakeValuesService fakeValuesService = new FakeValuesService(new Locale("fr-FR"), new RandomService());
	private static Faker faker = new Faker(new Locale("fr-FR"));

	@Override
	public void run(String... args) throws Exception {
		Date dateHeureDebut = new Date();
		addEmotions();
		addThemes();
		addDay();
		addUser();
		Date dateHeureFin = new Date();
		System.out.println("Données initiales ajoutées en " + String.valueOf(dateHeureFin.getTime()-dateHeureDebut.getTime()) + " ms");
	}

	private void addEmotions() {
		if (emotionDao.count() == 0) {
			emotionDao.save(new Emotion("Souriant", "&#x1F600;"));
			emotionDao.save(new Emotion("Monocle", "&#x1F9D0;"));
			emotionDao.save(new Emotion("Bisous", "&#x1F618;"));
			emotionDao.save(new Emotion("Coeur", "&#x1F60D;"));
			emotionDao.save(new Emotion("PTDR", "&#x1F923;"));
		}
	}

	private void addThemes( ) {
		if (themeDao.count()==0) {
			themeDao.save(new Theme("Bachata"));
			themeDao.save(new Theme("Dark"));
		}
	}

	private void addDay() {
		if (dayDao.count()==0) {
			int anneeEnCours = LocalDate.now().getYear();
			int moisEnCours = LocalDate.now().getMonthValue();
			LocalDate date = LocalDate.of(anneeEnCours, moisEnCours, 1);
			int nbJoursDuMoisEnCours = date.lengthOfMonth();
			Day day = new Day();
			day.setDate(date);
			for (int i = 1; i <= nbJoursDuMoisEnCours; i++) {
				dayDao.save(new Day(date));
				date = date.plusDays(1);
			}
		}
	}

	public void addUser() {
		if (utilisateurDao.count()==0) {
			// Partie déclarative
			List<Theme> themes = themeDao.findAll();
			Map<String, User> map = new HashMap<>();
			Calendar calendar = Calendar.getInstance();
			int compteur = 0;

			// Partie traitement
			// On boucle tant que la taille de la map n'est pas égale à 10000
			while (map.size() != 10000) {
				compteur++;
				// On déclare un objet de type Utilisateur
				// que l'on instancie dans la foulée
				User utilisateur = new User();
				// On fait appel au faker pour définir le nom de l'utilisateur
				utilisateur.setNom(faker.name().lastName());
				utilisateur.setPrenom(faker.name().firstName());
				utilisateur.setEmail(fakeValuesService.letterify("?????@hb.com"));

				//utilisateur.setMotDePasse(fakeValuesService.letterify("?????"));
				utilisateur.setMotDePasse(faker.internet().password(3, 8));

				// Grâce à l'objet calendar et le faker on obtient une date comprise
				// entre le 1 janvier 2021 et aujourd'hui (inclus)
				calendar.set(2021, 1, 1);
				Date dateDebut = calendar.getTime();
				calendar = Calendar.getInstance();
				Date dateFin = calendar.getTime();
				Date dateAleatoire = faker.date().between(dateDebut, dateFin);
				calendar.setTime(dateAleatoire);
				// La date choisie par le faker est utilisée pour définir la date et heure
				// d'inscription du nouvel utilisateur
				utilisateur.setDateHeureInscription(
						dateAleatoire.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
				// On choisit un thème aléatoirement parmi la liste des thèmes
				utilisateur.setTheme(themes.get(random.nextInt(themes.size())));
				//System.out.println(utilisateur);
				// On ajoute l'objet utilisateur dans la map
				map.put(utilisateur.getEmail(), utilisateur);
			}
			// J'invoque la méthode saveAll sur la dao utilisateurDao
			// pour demander à Spring Data de sauvegarder tous les utilisateurs présents dans la map
			utilisateurDao.saveAll(map.values());

			System.out.println("Nombre d'itérations=" + compteur);
		}
	}
}