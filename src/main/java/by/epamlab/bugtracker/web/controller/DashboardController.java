package by.epamlab.bugtracker.web.controller;

import by.epamlab.bugtracker.model.bean.*;
import by.epamlab.bugtracker.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Controller
@Secured("ROLE_USER")
public class DashboardController {

    private static final int FROM_INDEX = 0;
    private static final int TO_INDEX = 3;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private AssigmentService assigmentService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private AttachmentService attachmentService;

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String getDashboardPage(ModelMap model) {

        List<Activity> activities = activityService.listActivity(FROM_INDEX, TO_INDEX);
        List<Task> tasks = assigmentService.getAssignedTasks(getLoggedEmployee());

        model.addAttribute("activities", activities);
        model.addAttribute("tasks", tasks);

        return "dashboard";
    }

    @RequestMapping(value = "/dashboard/project-{id}", method = RequestMethod.GET)
    public String showProject(@PathVariable Integer id, Model model) {

        Project project = projectService.getProjectById(id);
        List<Member> projectMembers = project.getMembers();

        model.addAttribute("project", project);
        model.addAttribute("project_members", projectMembers);

        return "project";
    }

    @RequestMapping(value = "/dashboard/task-{id}", method = RequestMethod.GET)
    public String showTask(@PathVariable Integer id, Model model,
                           @ModelAttribute(value = "report_error") Object reportError,
                           @ModelAttribute(value = "upload_error") Object errorUpload) {

        Task task = taskService.getTask(id);
        List<Activity> activities = activityService.listActivity(FROM_INDEX, TO_INDEX);
        Assigment assignment = assigmentService.getAssigment(id);
        Project project = taskService.getProject(id);

        if (assignment == null) {
            assignment = new Assigment();
        }

        model.addAttribute("task", task);
        model.addAttribute("project", project);
        model.addAttribute("assigment", assignment);
        model.addAttribute("activities", activities);

        return "task";
    }

    @Secured(value = {"ROLE_PROJECT_MANAGER", "ROLE_TEAM_LEAD"})
    @RequestMapping(value = {"/create-issue"}, method = RequestMethod.POST)
    public String createIssue(@Valid Task task, BindingResult result,
                              Model model, HttpServletRequest request) {

        Integer selectedProjectId = task.getProject().getId();

        if (result.hasErrors() || selectedProjectId == null) {
            model.addAttribute("triggerIssueModal", true);
            model.addAttribute("error_issue", "Select project and date");
            return "dashboard";
        }

        Project project = projectService.getProjectById(selectedProjectId);
        task.setProject(project);

        long startDate = task.getPlanStartDate().getTime();
        long endDate = task.getPlanEndDate().getTime();
        if (startDate > endDate) {
            model.addAttribute("error_issue", "End date must be larger than start date.");
            model.addAttribute("triggerIssueModal", true);
            return "dashboard";
        }

        taskService.addTask(task);

        return "redirect:" + request.getHeader("referer"); //go to previous page
    }

    @RequestMapping(value = {"/dashboard/activity"}, method = RequestMethod.POST)
    @ResponseBody
    public String loadActivity(@RequestParam(value = "fromIndex") int fromIndex) {
        final int TO_INDEX = fromIndex + 3;

        List<Activity> activities = activityService.listActivity(fromIndex, TO_INDEX);
        return activityService.getJsonString(activities);
    }

    @Secured(value = {"ROLE_PROJECT_MANAGER", "ROLE_TEAM_LEAD"})
    @RequestMapping(value = {"/dashboard/assign"}, method = RequestMethod.POST)
    public String assign(@Valid Assigment assigment, BindingResult result,
                         RedirectAttributes redirectAttributes) {

        Integer selectedMemberId = assigment.getMember().getId();
        int taskId = assigment.getTask().getId();

        if (result.hasErrors() || selectedMemberId == null) {
            redirectAttributes.addFlashAttribute("triggerAssignModal", true);
            redirectAttributes.addFlashAttribute("assign_error", "Choose member");
            redirectAttributes.addFlashAttribute("showAssignError", true);
            return "redirect:/dashboard/task-" + taskId;
        }

        Member member = memberService.getMember(selectedMemberId);
        Task task = taskService.getTask(taskId);
        Assigment existingAssigment = assigmentService.getAssigment(taskId);

        assigment.setTask(task);
        assigment.setMember(member);

        if (existingAssigment != null) {
            existingAssigment.setTask(task);
            existingAssigment.setMember(member);
            existingAssigment.setDescription(assigment.getDescription());
            assigmentService.updateAssigment(existingAssigment);
            return "redirect:/dashboard/task-" + taskId;
        }

        assigmentService.addAssigment(assigment);

        return "redirect:/dashboard/task-" + taskId;
    }

    @ResponseBody
    @RequestMapping(value = "/dashboard/export-{id}.xml", method = RequestMethod.GET)
    public Task exportToXML(@PathVariable Integer id) {
        return taskService.getTask(id);
    }

    @RequestMapping(value = "/dashboard/start-progress-{id}", method = RequestMethod.GET)
    public String startProgress(@PathVariable Integer id, HttpServletRequest request) {
        final int IN_PROGRESS_STATUS_ID = 2;

        Task task = taskService.getTask(id);
        Date actualStartDate = new Date(Calendar.getInstance().getTime().getTime());

        task.setStatus(statusService.getStatus(IN_PROGRESS_STATUS_ID));
        task.setActualStartDate(actualStartDate);
        taskService.updateTask(task);

        return "redirect:" + request.getHeader("referer");
    }

    @RequestMapping(value = "/dashboard/resolve-task-{id}", method = RequestMethod.GET)
    public String resolveTask(@PathVariable Integer id, HttpServletRequest request) {
        final int DONE_STATUS_ID = 3;

        Task task = taskService.getTask(id);
        Date actualEndDate = new Date(Calendar.getInstance().getTime().getTime());

        task.setStatus(statusService.getStatus(DONE_STATUS_ID));
        task.setActualEndDate(actualEndDate);
        taskService.updateTask(task);

        return "redirect:" + request.getHeader("referer");
    }

    @RequestMapping(value = "/dashboard/report-{id}", method = RequestMethod.POST)
    public String createReport(@PathVariable Integer id, @Valid Activity activity,
                               BindingResult result, HttpServletRequest request,
                               RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("report_error", "You need fill out all fields");
            return "redirect:/dashboard/task-" + id;
        }

        Task task = taskService.getTask(id);
        int projectId = task.getProject().getId();
        int employeeId = employeeService.getEmployee(getLoggedEmployee()).getId();
        Member member = memberService.getMember(projectId, employeeId);

        Assigment assigment = assigmentService.getAssigment(id);

        Date date = new Date(Calendar.getInstance().getTime().getTime());

        activity.setDate(date);
        activity.setMember(member);
        activity.setAssigment(assigment);

        activityService.addActivity(activity);
        return "redirect:" + request.getHeader("referer");
    }


    @RequestMapping(value = "/dashboard/upload", method = RequestMethod.POST)
    public String fileUpload(@Valid Attachment attachment, HttpServletRequest request,
                             RedirectAttributes redirectAttributes,
                             @RequestParam("file") MultipartFile file) {

        Integer taskId = attachment.getTask().getId();

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("upload_error", "The upload file is empty.");
            redirectAttributes.addFlashAttribute("showAttachError", true);
            redirectAttributes.addFlashAttribute("triggerAttachmentModal", true);
            return "redirect:/dashboard/task-" + taskId;
        }

        Task task = taskService.getTask(taskId);
        Project project = projectService.getProjectById(attachment.getProject().getId());

        attachment.setTask(task);
        attachment.setProject(project);
        attachment.setSize(file.getSize());
        attachment.setName(file.getOriginalFilename());

        try (BufferedOutputStream stream = new BufferedOutputStream(
                new FileOutputStream(new File("E://uploadedFiles//" + file.getOriginalFilename())))) {
            byte[] bytes = file.getBytes();
            stream.write(bytes);
            attachmentService.addAttachment(attachment);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("upload_error", "Error loading file.");
            redirectAttributes.addFlashAttribute("showAttachError", true);
            redirectAttributes.addFlashAttribute("triggerAttachmentModal", true);
            return "redirect:/dashboard/task-" + taskId;
        }

        return "redirect:" + request.getHeader("referer");
    }

    @RequestMapping(value = "/dashboard/download-{id}", method = RequestMethod.GET)
    public void download(@PathVariable Integer id, HttpServletResponse response) {
        final int BUFFER_SIZE = 1024;
        final int OFFSET = 0;

        Attachment attachment = attachmentService.getAttachment(id);
        File file = new File("E://uploadedFiles//" + attachment.getName());

        try (InputStream is = new FileInputStream(file);
             OutputStream os = response.getOutputStream()) {

            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + attachment.getName() + "\"");

            byte[] buffer = new byte[BUFFER_SIZE];
            int len;
            while ((len = is.read(buffer)) != -1) {
                os.write(buffer, OFFSET, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getLoggedEmployee() {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        Employee employee = employeeService.getEmployee(login);
        return (employee != null) ? login : "none";
    }
}
