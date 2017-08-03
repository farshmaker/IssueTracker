package by.epamlab.bugtracker.web.controller;

import by.epamlab.bugtracker.model.bean.Employee;
import by.epamlab.bugtracker.model.bean.Member;
import by.epamlab.bugtracker.model.bean.Position;
import by.epamlab.bugtracker.model.bean.Project;
import by.epamlab.bugtracker.service.interfaces.EmployeeService;
import by.epamlab.bugtracker.service.interfaces.MemberService;
import by.epamlab.bugtracker.service.interfaces.PositionService;
import by.epamlab.bugtracker.service.interfaces.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@Secured("ROLE_ADMIN")
public class AdminController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String getAdminPage() {

        return "admin";
    }

    @RequestMapping(value = "/add-employee", method = RequestMethod.POST)
    public String addEmployee(@ModelAttribute("employee") @Valid Employee employee,
                              BindingResult result, Model model) {

        if(result.hasErrors()){
            model.addAttribute("errorEmployee", true);

            return "admin";
        }

        employeeService.addEmployee(employee);

        return "redirect:/admin";
    }

    @RequestMapping(value = "/edit-employee-{id}", method = RequestMethod.GET)
    public String editEmployee(@PathVariable Integer id, ModelMap model) {

        Employee employee = employeeService.getEmployee(id);
        model.addAttribute("editEmployee", true);
        model.addAttribute("employee", employee);

        return "admin";
    }

    @RequestMapping(value = "/edit-employee", method = RequestMethod.POST)
    public String editEmployee(@ModelAttribute("employee") @Valid Employee employee,
                               BindingResult result, Model model) {
        if(result.hasErrors()){
            model.addAttribute("errorEmployee", true);
            model.addAttribute("editEmployee", true);
            return "admin";
        }

        employeeService.updateEmployee(employee);

        return "redirect:/admin";
    }

    @RequestMapping(value = "/delete-employee-{id}", method = RequestMethod.GET)
    public String deleteEmployee(@PathVariable Integer id) {

        Employee employee = employeeService.getEmployee(id);
        employeeService.deleteEmployee(employee);

        return "redirect:/admin";
    }

    @RequestMapping(value = "/add-project", method = RequestMethod.GET)
    public String addProject(@ModelAttribute("project") Project project, Model model) {

        return "admin";
    }

    @RequestMapping(value = "/add-project", method = RequestMethod.POST)
    public String addProject(@ModelAttribute("project") @Valid Project project,
                             BindingResult result, ModelMap model) {

        if(result.hasErrors()){
            model.addAttribute("errorProject", true);
            return "admin";
        }

        projectService.addProject(project);

        return "redirect:/admin";
    }

    @RequestMapping(value = "/edit-project-{id}", method = RequestMethod.GET)
    public String editProject(@PathVariable Integer id, ModelMap model) {

        Project project = projectService.getProjectById(id);
        model.addAttribute("editProject", true);
        model.addAttribute("project", project);

        return "admin";
    }

    @RequestMapping(value = "/edit-project", method = RequestMethod.POST)
    public String editProject(@ModelAttribute("project") @Valid Project project,
                              BindingResult result, ModelMap model) {

        if(result.hasErrors()){
            model.addAttribute("errorProject", true);
            model.addAttribute("editProject", true);
            return "admin";
        }

        projectService.updateProject(project);

        return "redirect:/admin";
    }

    @RequestMapping(value = "/assign-{id}", method = RequestMethod.GET)
    public String assign(@PathVariable Integer id, ModelMap model) {

        Employee employee = employeeService.getEmployee(id);
        model.addAttribute("employee", employee);
        model.addAttribute("assign", true);

        return "admin";
    }

    @RequestMapping(value = "/assign", method = RequestMethod.POST)
    public String assign(@ModelAttribute("member") @Valid Member member,
                         BindingResult result, ModelMap model) {

        int employeeId, projectId;
        Member existingMember;

        if (result.hasErrors()) {
            model.addAttribute("assign", true);
            return "admin";
        }
        try {
            employeeId = member.getEmployee().getId();
            projectId = member.getProject().getId();
            existingMember = memberService.getMember(projectId, employeeId);
        } catch (NullPointerException e) {
            model.addAttribute("errorAssign",
                    "Choose project and role");
            model.addAttribute("assign", true);
            return "admin";
        }

        if (existingMember != null) {
            model.addAttribute("errorAssign",
                    "The member already assigned to this project.");
            model.addAttribute("assign", true);
            return "admin";
        }

        memberService.addMember(member);

        return "redirect:/admin";
    }
}
