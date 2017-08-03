package by.epamlab.bugtracker.model.bean;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Table(name = "activity")
public class Activity {

    @Id
    @Column(name = "activity_id")
    @GeneratedValue
    private Integer id;

    @Column(name = "_date")
    private Date date;

    @Column(name = "duration")
    @NotNull
    private Integer duration;

    @NotEmpty
    @Column(name = "comment")
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigment_id")
    private Assigment assigment;

    public Activity(Integer id, Date date, Integer duration, String comment, Member member, Assigment assigment) {
        this.id = id;
        this.date = date;
        this.duration = duration;
        this.comment = comment;
        this.member = member;
        this.assigment = assigment;
    }

    public Activity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Assigment getAssigment() {
        return assigment;
    }

    public void setAssigment(Assigment assigment) {
        this.assigment = assigment;
    }
}
