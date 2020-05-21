package com.thermomix.server.models;

import com.thermomix.server.models.enums.DishCategory;
import com.thermomix.server.models.enums.DishDificulty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "dish")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String photo;
    private String price;
    private String cookingTime;
    private String avgMark;
    private String ingredients;
    private String nvalues;
    private String preparing;

    @Enumerated(value = EnumType.STRING)
    private DishCategory category;

    @Enumerated(value = EnumType.STRING)
    private DishDificulty difficulty;

    @OneToMany(mappedBy = "dish")
    private List<Opinion> opinions;

    @ManyToMany(mappedBy = "userDishes")
    private List<User> dishUsers;
}
