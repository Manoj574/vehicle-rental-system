package com.practice.lld.project_design_practice.models;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "stores")
@Getter
@Setter
public class Store {

    @Id
    @Generated
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Basic
    private String name;

    @Basic
    private String city;

    @Basic
    private String address;

    @OneToMany(mappedBy = "store", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Vehicle> vehicles;
}
