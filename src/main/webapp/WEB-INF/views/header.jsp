<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Bug Tracker</title>

    <!-- Bootstrap core CSS -->
    <spring:url value="/resources/css/bootstrap.min.css" var="bootstrapCss"/>
    <link href="${bootstrapCss}" rel="stylesheet">

    <!-- Custom styles for this template -->
    <spring:url value="/resources/css/starter-template.css" var="userCss"/>
    <spring:url value="/resources/css/style.css" var="myStyle"/>
    <spring:url value="/resources/css/admin.css" var="userCss"/>
    <link href="${userCss}" rel="stylesheet">
    <link href="${myStyle}" rel="stylesheet">
    <link href="${userCss}" rel="stylesheet">

    <!-- Datepicker -->
    <spring:url value="/resources/css/datepicker" var="datePickerCss"/>
    <link href="${datePickerCss}" rel="stylesheet">

    <script Language="JavaScript">
        function refreshDashboard() {
            location.replace("http://localhost:8080/dashboard")
        }
    </script>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand">Bug Tracker</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="/dashboard">Dashboards</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                       aria-haspopup="true" aria-expanded="false">Projects<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <c:forEach var="member" items="${current_projects}">
                            <li><a href="<c:url value="/dashboard/project-${member.project.id}"/>">${member.project.name}</a></li>
                        </c:forEach>
                    </ul>
                </li>
                <li><a href="#issues">Issues</a></li>
            </ul>
            <sec:authorize
                    access="hasAnyRole('ROLE_PROJECT_MANAGER','ROLE_TEAM_LEAD')">
            <button type="button" class="btn btn-primary" data-toggle="modal"
                    data-target="#issueModal" style="margin-top: 7px">Create issue</button>
            </sec:authorize>

            <sec:authorize access="isAuthenticated()">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                           aria-haspopup="true" aria-expanded="false">Signed in as ${loggedEmployee}<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="/j_spring_security_logout">Sign out</a></li>
                        </ul>
                    </li>
                </ul>
            </sec:authorize>

            <sec:authorize access="!isAuthenticated()">
                    <form method="post" action="/j_spring_security_check" class="navbar-form navbar-right">
                        <div class="form-group">
                            <input id="username" name="j_username" type="text" placeholder="Login" class="form-control">
                        </div>
                        <div class="form-group">
                            <input id="password" name="j_password" type="password" placeholder="Password" class="form-control">
                        </div>
                        <button type="submit" class="btn btn-success">Sign in</button>
                    </form>
            </sec:authorize>

        </div>
        <!--/.nav-collapse -->
    </div>
</nav>