package by.epamlab.bugtracker.model.bean;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "assigment")
public class Assigment {

    @Id
    @Column(name = "assigment_id")
    @GeneratedValue
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @NotNull
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    @NotNull
    private Task task;

    @Column(name = "description")
    private String description;

    public Assigment(Integer id, Member member, Task task, String description) {
        this.id = id;
        this.member = member;
        this.task = task;
        this.description = description;
    }

    public Assigment() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
