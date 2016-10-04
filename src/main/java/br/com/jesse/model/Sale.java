package br.com.jesse.model;

import javax.persistence.*;

@Entity
@Table
public class Sale {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String code;

    @ManyToOne
    @JoinColumn(name = "modelo_id")
    private Model model;

    @Column(name = "manufacture_year")
    private int manufactureYear;

    public Sale() {
    }

    public Sale(String code, Model model, int manufactureYear) {
        this.code = code;
        this.model = model;
        this.manufactureYear = manufactureYear;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Sale{");
        sb.append("code='").append(code).append('\'');
        sb.append(", model=").append(model);
        sb.append(", manufactureYear=").append(manufactureYear);
        sb.append('}');
        return sb.toString();
    }
}
