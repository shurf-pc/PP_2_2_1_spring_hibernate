package hiber.model;

import javax.persistence.*;

@Entity
@Table(name = "cars", uniqueConstraints = {@UniqueConstraint(columnNames = {"model", "series"})})
public class Car {
    @Column
    private String model;
    @Column
    private int series;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User user;

    public Car() {
    }

    public Car(String model, int series) {
        this.model = model;
        this.series = series;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Car id: " + id + ",car model " + model + ", series " + series;
    }
}
