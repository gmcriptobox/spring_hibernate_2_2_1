package hiber.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;


@Entity
public class Car implements Serializable {


    public Car(){

    }
    public  Car(String model, int series){
        this.model = model;
        this.series = series;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "car_model", nullable = false)
    private String model;

    @Column(name = "series", nullable = false)
    private int series;

    //@OneToOne(mappedBy = "car", cascade = CascadeType.ALL)
    //private User user;

    public int getSeries() {
        return series;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setSeries(int series) {
        this.series = series;
    }
}
