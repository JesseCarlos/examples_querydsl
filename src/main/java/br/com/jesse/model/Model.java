package br.com.jesse.model;

import javax.persistence.*;

@Entity
@Table
public class Model {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String brand;

    @Column
    private String description;

    public Model() {
    }

    public Model(String brand, String description) {
        this.brand = brand;
        this.description = description;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Model{");
        sb.append(", brand='").append(brand).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
