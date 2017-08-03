package by.epamlab.bugtracker.dao.intefaces;

import by.epamlab.bugtracker.model.bean.Employee;
import by.epamlab.bugtracker.model.bean.Member;

import java.util.List;

public interface MemberDAO {

    public void addMember(Member member);

    public Member getMember(int id);

    public Member getMember(int projectId, int employeeId);

    public List<Member> listMember();

    public List<Member> listMember(Employee employee);

    public List<Member> listMemberGroupByProject(Employee employee);

    public List<Member> listMember(int projectId);

}
