<%@ include file="header.jsp" %>
<%@ include file="modal/issue-modal.jsp" %>

<div class="container" style="padding-top: 40px">

    <div class="page-header" style="padding-left: 15px; padding-right: 15px; border-color: #CAC6C6">
        <h2>${project.name}
            <small>${project.description}</small>
        </h2>
    </div>

    <div class="col-xs-6">
        <div class="panel panel-primary">

            <div class="panel-heading">
                <h3 class="panel-title">Project Team Members</h3>
            </div>

            <div class="panel-body">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>Firstname</th>
                        <th>Lastname</th>
                        <th>Login</th>
                        <th>Role</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="member" items="${project_members}">
                        <tr>
                            <td>${member.employee.firstName}</td>
                            <td>${member.employee.lastName}</td>
                            <td>${member.employee.login}</td>
                            <td>${member.role.name}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </div>

        </div>
    </div>

    <div class="col-xs-6">
        <div class="panel panel-primary">

            <div class="panel-heading">
                <h3 class="panel-title">Project Tasks</h3>
            </div>

            <div class="panel-body">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>Summary</th>
                        <th>Status</th>
                    </tr>
                    </thead>
                    <tbody style="cursor:pointer">
                    <c:forEach var="task" items="${project.tasks}">
                        <tr class='clickable-row' data-href='<c:url value="/dashboard/task-${task.id}"/>'>
                            <td>${task.description}</td>
                            <td>${task.status.name}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </div>

        </div>
    </div>


</div>
<!-- /.container -->


<%@ include file="footer.jsp" %>

