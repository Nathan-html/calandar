package com.nat.calandar.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Reaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime dateTime;

    @ManyToOne
    private Emotion emotion;

    @ManyToOne
    private User customer;

    @ManyToOne
    private Gif gif;
}
