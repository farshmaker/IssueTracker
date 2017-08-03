package by.epamlab.bugtracker.dao.intefaces;

import by.epamlab.bugtracker.model.bean.Assigment;
import by.epamlab.bugtracker.model.bean.Member;
import by.epamlab.bugtracker.model.bean.Task;

import java.util.List;

public interface AssigmentDAO {

    public void addAssigment(Assigment assignment);

    public Assigment getAssigment(int taskId);

    public List<Assigment> listAssigment();

    public List<Assigment> listAssigment(Member member);

    public void updateAssigment(Assigment assigment);


}
