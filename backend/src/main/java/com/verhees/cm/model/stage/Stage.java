package com.verhees.cm.model.stage;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class Stage {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    private Date date;
}
