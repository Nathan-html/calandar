package com.nat.calandar.DAO;

import com.nat.calandar.model.Emotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmotionDao extends JpaRepository<Emotion, Long> {

}
