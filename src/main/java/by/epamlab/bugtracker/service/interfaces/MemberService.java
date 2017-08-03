package by.epamlab.bugtracker.service.interfaces;

import by.epamlab.bugtracker.model.bean.Employee;
import by.epamlab.bugtracker.model.bean.Member;

import java.util.List;

public interface MemberService {

    public void addMember(Member member);

    public Member getMember(int id);

    public Member getMember(int projectId, int employeeId);

    public List<Member> listMember();

    public List<Member> listMember(Employee employee);

    public List<Member> listMember(String login);

    public List<Member> listMember(int projectId);

    public List<Member> listMemberGroupByProject(Employee employee);

    public String getJsonString(List<Member> members);

}
