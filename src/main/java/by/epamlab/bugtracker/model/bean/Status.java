package by.epamlab.bugtracker.model.bean;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "status")
public class Status {

    @Id
    @GeneratedValue
    @Column(name = "status_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    public Status(Integer id, String name){
        this.id = id;
        this.name = name;
    }

    public Status() {
    }

    @XmlTransient
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
        return "Status{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
