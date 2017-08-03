package by.epamlab.bugtracker.model.bean;

import by.epamlab.bugtracker.web.converter.DateAdapter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.sql.Date;

@Entity
@Table(name = "task")
@XmlRootElement(name = "task")
public class Task {

    @Id
    @GeneratedValue
    @Column(name = "task_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    @NotNull
    private Project project;

    @Column(name = "description")
    private String description;

    @Column(name = "psd")
    @NotNull(message = "Correct date format must be YYYY-mm-dd.")
    private Date planStartDate;

    @Column(name = "ped")
    @NotNull(message = "Correct date format must be YYYY-mm-dd.")
    private Date planEndDate;

    @Column(name = "asd")
    private Date actualStartDate;

    @Column(name = "aed")
    private Date actualEndDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", insertable = false, nullable = false, columnDefinition = "int default 1")
    private Status status;

    public Task(Integer id, Project project, String description, Date planStartDate,
                Date planEndDate, Date actualStartDate, Date actualEndDate, Status status) {
        this.id = id;
        this.project = project;
        this.description = description;
        this.planStartDate = planStartDate;
        this.planEndDate = planEndDate;
        this.actualStartDate = actualStartDate;
        this.actualEndDate = actualEndDate;
        this.status = status;
    }

    public Task() {
    }

    @XmlTransient
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlTransient
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getPlanStartDate() {
        return planStartDate;
    }

    public void setPlanStartDate(Date planStartDate) {
        this.planStartDate = planStartDate;
    }

    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getPlanEndDate() {
        return planEndDate;
    }

    public void setPlanEndDate(Date planEndDate) {
        this.planEndDate = planEndDate;
    }

    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getActualStartDate() {
        return actualStartDate;
    }

    public void setActualStartDate(Date actualStartDate) {
        this.actualStartDate = actualStartDate;
    }

    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getActualEndDate() {
        return actualEndDate;
    }

    public void setActualEndDate(Date actualEndDate) {
        this.actualEndDate = actualEndDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", project=" + project +
                ", description='" + description + '\'' +
                ", planStartDate=" + planStartDate +
                ", planEndDate=" + planEndDate +
                ", actualStartDate=" + actualStartDate +
                ", actualEndDate=" + actualEndDate +
                ", status=" + status +
                '}';
    }
}
