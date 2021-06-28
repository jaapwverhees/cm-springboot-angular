package com.verhees.cm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public abstract class Competition {
    @Id
    private String name;
}
