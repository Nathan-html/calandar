package com.nat.calandar.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public abstract class Gif {

    @Id
    protected Integer id;

    protected LocalDateTime addAt;

    protected String description;

    @ManyToOne
    private User customer;

    @OneToMany(mappedBy = "gif")
    private List<Reaction> reactions;

    @OneToOne
    protected Day day;

}
