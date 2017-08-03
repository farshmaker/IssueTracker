package by.epamlab.bugtracker.web.controller;

import by.epamlab.bugtracker.model.bean.Employee;
import by.epamlab.bugtracker.service.interfaces.EmployeeService;
import by.epamlab.bugtracker.service.interfaces.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Iterator;

@Controller
public class LoginController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PositionService positionService;

    @RequestMapping("/")
    public String home() {
        return "redirect:/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage(@RequestParam(value = "auth_error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("auth_error", "Wrong username or password.");
        }

        return "/login";
    }

    @RequestMapping(value = "/distributor", method = RequestMethod.GET)
    public String distribute() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        Collection roles = authentication.getAuthorities();
        Iterator itr = roles.iterator();
        if (itr.hasNext()) {
            SimpleGrantedAuthority simpleRole = (SimpleGrantedAuthority) itr.next();
            String role = simpleRole.getAuthority();
            return "ROLE_ADMIN".equals(role) ?
                    "redirect:/admin":
                    "redirect:/dashboard";
        }

        return "login";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied(Employee employee) {

        ModelAndView model = new ModelAndView();

        if (employee != null) {
            model.addObject("msg", "Hi " + employee.getLogin()
                    + ", you do not have permission to access this page!");
        } else {
            model.addObject("msg",
                    "You do not have permission to access this page!");
        }

        model.setViewName("403");
        return model;

    }

//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String postLogin(@ModelAttribute("employee") @Valid Employee employee,
//                             BindingResult result, ModelMap model) {
//
//        employee = employeeService.getEmployeeByLogin(employee.getLogin());
//
//        if(result.hasFieldErrors("login") || result.hasFieldErrors("password") || employee == null){
//            model.addAttribute("employee", new Employee());
//            return "login";
//        }
//
//        if("Administrator".equals(employee.getPosition().getName())) {
//
//            return "redirect:/admin";
//        }
//
//        return "redirect:/dashboard";
//    }



//    @RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
//    public ModelAndView hello(@PathVariable("name") String name) {
//
//        ModelAndView model = new ModelAndView();
//        model.setViewName("index");
//        model.addObject("msg", name);
//
//        return model;
//
//    }
}
