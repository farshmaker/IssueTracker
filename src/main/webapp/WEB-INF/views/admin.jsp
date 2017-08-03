<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Admin page</title>
    <!-- Bootstrap core CSS -->
    <spring:url value="/resources/css/bootstrap.css" var="bootstrapCss"/>
    <link href="${bootstrapCss}" rel="stylesheet">

    <!-- Custom styles for this template -->
    <spring:url value="/resources/css/admin.css" var="userCss"/>
    <link href="${userCss}" rel="stylesheet">
    <SCRIPT Language="JavaScript">
        function refreshAdmin() {
            location.replace("http://localhost:8080/admin")
        }
    </SCRIPT>

</head>
<body>

<%@ include file="modal/employee-modal.jsp" %>
<%@ include file="modal/project-modal.jsp" %>
<%@ include file="modal/assign-project-modal.jsp" %>

<div class="generic-container">

    <div id="tabs">
        <!-- Nav tabs -->
        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="active">
                <a href="#employeeTab" aria-controls="employee" role="tab" data-toggle="tab">Employee list</a></li>
            <li role="presentation">
                <a href="#projectTab" aria-controls="project" role="tab" data-toggle="tab">Project list</a></li>
            <li class="dropdown" style="float: right !important;">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                   aria-haspopup="true" aria-expanded="false">Signed in as ${loggedEmployee}<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/j_spring_security_logout">Sign out</a></li>
                </ul>
            </li>
        </ul>

        <!-- Tab panes -->
        <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="employeeTab">
                <!-- Add new employee button -->
                <div class="well">
                    <button type="button" class="btn btn-primary btn-lg" data-toggle="modal"
                            data-target="#employeeModal">
                        Add new user
                    </button>
                </div>
                <!-- Add new employee button -->

                <!-- Employee list panel -->
                <div class="panel panel-default">
                    <div class="panel-heading"><span class="lead">List of Users </span></div>
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>Firstname</th>
                            <th>Lastname</th>
                            <th>Login</th>
                            <th>Password</th>
                            <th>Position</th>
                            <th width="100"></th>
                            <th width="100"></th>
                            <th width="100"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${employees}" var="employee">
                            <tr>
                                <td>${employee.firstName}</td>
                                <td>${employee.lastName}</td>
                                <td>${employee.login}</td>
                                <td>${employee.password}</td>
                                <td>${employee.position.name}</td>
                                <td><a href="<c:url value='/assign-${employee.id}' />" class="btn btn-primary
custom-width">assign</a></td>
                                <td><a href="<c:url value='/edit-employee-${employee.id}' />" class="btn btn-success

custom-width">edit</a></td>
                                <td><a href="<c:url value='/delete-employee-${employee.id}' />" class="btn btn-danger

custom-width">delete</a></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div><!-- Employee list panel -->
            </div>

            <div role="tabpanel" class="tab-pane" id="projectTab">
                <!-- Add new project button -->
                <div class="well">
                    <button type="button" class="btn btn-primary btn-lg" data-toggle="modal"
                            data-target="#projectModal">
                        Add new project
                    </button>
                </div><!-- Add new project button -->


                <!-- Project list panel -->
                <div class="panel panel-default">
                    <div class="panel-heading"><span class="lead">List of Projects</span></div>
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Description</th>
                            <th width="100"></th>
                            <th width="100"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${projects}" var="project">
                            <tr>
                                <td>${project.name}</td>
                                <td>${project.description}</td>
                                <td><a href="<c:url value='/edit-project-${project.id}' />" class="btn btn-success

custom-width">edit</a></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div><!-- Project list panel -->
            </div>
        </div>

    </div>


</div>
<spring:url value="/resources/js/bootstrap.min.js" var="bootstrapJs"/>
<spring:url value="/resources/js/jquery-1.11.3.js" var="jqueryJs"/>
<script src="${jqueryJs}"></script>
<script src="${bootstrapJs}"></script>

<c:if test="${editEmployee || errorEmployee}">
    <script type="text/javascript">
        $(window).load(function () {
            $('#employeeModal').modal('show');
        });
    </script>
</c:if>

<c:if test="${editProject || errorProject}">
    <script type="text/javascript">
        $(document).ready(function () {
            activateTab('projectTab');
        });

        function activateTab(tab) {
            $('.nav-tabs a[href="#' + tab + '"]').tab('show');
        }

        $(window).load(function () {
            $('#projectModal').modal('show');
        });
    </script>
</c:if>

<c:if test="${assign}">
    <script type="text/javascript">
        $(window).load(function () {
            $('#assignModal').modal('show');
        });
    </script>
</c:if>

</body>
</html>