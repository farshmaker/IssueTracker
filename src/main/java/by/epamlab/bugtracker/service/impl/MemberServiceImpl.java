package by.epamlab.bugtracker.service.impl;

import by.epamlab.bugtracker.dao.intefaces.EmployeeDAO;
import by.epamlab.bugtracker.dao.intefaces.MemberDAO;
import by.epamlab.bugtracker.model.bean.Employee;
import by.epamlab.bugtracker.model.bean.Member;
import by.epamlab.bugtracker.service.interfaces.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDAO memberDAO;

    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    public Member getMember(int id) {
        return memberDAO.getMember(id);
    }

    @Override
    public void addMember(Member member) {
        memberDAO.addMember(member);
    }

    @Override
    public List<Member> listMember() {
        return memberDAO.listMember();
    }

    @Override
    public List<Member> listMember(Employee employee) {
        return memberDAO.listMember(employee);
    }

    @Override
    public List<Member> listMember(String login) {
        Employee employee = employeeDAO.getEmployee(login);
        return memberDAO.listMember(employee);
    }

    @Override
    public List<Member> listMember(int projectId) {
        return memberDAO.listMember(projectId);
    }

    @Override
    public List<Member> listMemberGroupByProject(Employee employee) {
        return memberDAO.listMemberGroupByProject(employee);
    }



    @Override
    public Member getMember(int projectId, int employeeId) {
        return memberDAO.getMember(projectId, employeeId);
    }

    @Override
    public String getJsonString(List<Member> members) {
        return null;
    }

}
