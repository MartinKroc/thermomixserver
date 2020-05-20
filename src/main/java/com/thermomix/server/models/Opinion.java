package com.thermomix.server.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "opinion")
public class Opinion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private String content;
    private String mark;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @ManyToOne()
    @JoinColumn(name = "dish_id")
    private Dish dishId;
}
