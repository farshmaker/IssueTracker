package by.epamlab.bugtracker.model.bean;

import javax.persistence.*;

@Entity
@Table(name = "position")
public class Position {

    @Id
    @Column(name = "position_id")
    @GeneratedValue
    private Integer id;

    @Column(name = "name")
    private String name;

    public Position(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Position() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
