package com.thermomix.server.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "properties")
public class SoftProperties {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String version;
    private String receipeVer;
    private String lastUpdate;

}
