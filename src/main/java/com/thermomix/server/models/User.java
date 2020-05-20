package com.thermomix.server.models;

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
@Table(name= "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private String username;
    private String password;

    @OneToMany(mappedBy = "userId")
    List<Opinion> opinions;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_dishes")
    private List<Dish> userDishes;
}
