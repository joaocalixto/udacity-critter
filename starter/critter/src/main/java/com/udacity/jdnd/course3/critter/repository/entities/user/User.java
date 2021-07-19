package com.udacity.jdnd.course3.critter.repository.entities.user;

import lombok.Data;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Nationalized
    private String name;
}
