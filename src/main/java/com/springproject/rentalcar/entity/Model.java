package com.springproject.rentalcar.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="model")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "modelId")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long modelId;

    @Column(nullable = false)
    private String modelName;

    private String modelYear;

    @Column(nullable = false)
    private String modelMake;

    @OneToMany(mappedBy = "model", cascade = CascadeType.ALL)
//    @JsonManagedReference
    private List<Car> cars;


}
