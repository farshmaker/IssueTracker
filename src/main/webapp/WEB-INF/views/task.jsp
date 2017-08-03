<%@ include file="header.jsp" %>
<%@ include file="modal/assign-task-modal.jsp" %>
<%@ include file="modal/issue-modal.jsp" %>
<%@ include file="modal/attachment-modal.jsp" %>

<div class="container" style="padding-top: 40px">

    <div class="page-header" style="padding-left: 15px; padding-right: 15px; border-color: #CAC6C6">
        <h2>${task.description}
            <small>${assigment.description}</small>
        </h2>
    </div>

    <%--Button panel--%>
    <div class="btn-panel col-xs-9">

        <sec:authorize access="hasAnyRole('ROLE_PROJECT_MANAGER','ROLE_TEAM_LEAD')">
            <div class="btn-group" role="group" aria-label="...">
                <button type="button" class="btn btn-default" data-toggle="modal"
                        data-target="#assignModal">Assign
                </button>
            </div>
        </sec:authorize>

        <c:if test="${loggedEmployee eq assigment.member.employee.login}">

            <div class="btn-group" role="group" aria-label="...">
                <c:choose>
                    <c:when test="${task.status.name ne 'Todo'}">
                        <a href="" role="button" class="btn btn-default
                        disabled">Start Progress</a>
                    </c:when>
                    <c:otherwise>
                        <a href="/dashboard/start-progress-${task.id}" role="button" class="btn btn-default">Start
                            Progress</a>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${task.status.name ne 'In progress'}">
                        <a href="" role="button" class="btn btn-default
                        disabled">Resolve Task</a>
                    </c:when>
                    <c:otherwise>
                        <a href="/dashboard/resolve-task-${task.id}" role="button" class="btn btn-default">Resolve
                            Task</a>
                    </c:otherwise>
                </c:choose>
            </div>

            <div class="btn-group" role="group" aria-label="...">
                <a href="/dashboard/export-${task.id}.xml" role="button" class="btn btn-default">Export</a>
            </div>
        </c:if>
    </div>
    <%--Button panel--%>

    <%--Details--%>
    <div class="col-md-7">
        <div class="panel panel-primary">

            <div class="panel-heading">
                <h3 class="panel-title">Details</h3>
            </div>

            <div class="panel-body">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>Assignee</th>
                        <th>Plan start day</th>
                        <th>Plan end day</th>
                        <th>Status</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <c:choose>
                            <c:when test="${not empty assigment.member.employee.login}">
                                <td>${assigment.member.employee.firstName}
                                        ${assigment.member.employee.lastName}
                                    (${assigment.member.employee.login})
                                </td>
                            </c:when>
                            <c:otherwise>
                                <td>Nobody</td>
                            </c:otherwise>
                        </c:choose>
                        <td>${task.planStartDate}</td>
                        <td>${task.planEndDate}</td>
                        <td>${task.status.name}</td>
                    </tr>
                    </tbody>

                    <thead>
                    <tr>
                        <th width="100px"></th>
                        <th>Actual start day</th>
                        <th>Actual end day</th>
                        <th width="100px"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td width="100px"></td>
                        <td>${task.actualStartDate}</td>
                        <td>${task.actualEndDate}</td>
                        <td width="100px"></td>
                    </tr>
                    </tbody>

                </table>
            </div>
        </div>
    </div>
    <%--End of details--%>

    <%--Report--%>
    <div class="col-md-5">
        <div class="panel panel-primary">

            <div class="panel-heading">
                <h3 class="panel-title">Report</h3>
            </div>

            <div class="panel-body">
                <form:form method="POST" modelAttribute="activity" action="/dashboard/report-${task.id}">
                    <div class="form-group">
                        <label for="comment" class="col-sm-2 control-label">Comment</label>

                        <div class="col-sm-10">
                            <form:textarea path="comment" class="form-control" rows="3" placeholder="What you did"/>
                            <div class="has-error">
                                <form:errors path="comment" class="help-inline"/>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="duration" class="col-sm-2 control-label">Duration</label>

                        <div class="col-sm-10">
                            <form:input path="duration" type="text" class="form-control"
                                        id="duration" placeholder="How many time you did"/>
                            <div class="has-error">
                                <form:errors path="duration" class="help-inline"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">

                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">Report</button>
                        </div>
                    </div>
                </form:form>
            </div>

        </div>
        <c:if test="${report_error eq 'You need fill out all fields'}">
            <div class="alert alert-danger" role="alert" style="display:inline-block; float:right;" >${report_error}</div>
        </c:if>
    </div>
    <%--End of reports--%>

    <%--Attachments--%>
    <div class="col-md-7">
        <div class="panel panel-primary">

            <div class="panel-heading">
                <h3 class="panel-title">Attachments</h3>
            </div>

            <div class="panel-body">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>File Name</th>
                        <th>Size (KB)</th>
                        <th>Description</th>
                    </tr>
                    </thead>
                    <tbody style="cursor:pointer">
                    <c:forEach var="attachment" items="${task.project.attachments}">
                        <tr class='clickable-row' data-href='<c:url value="/dashboard/download-${attachment.id}"/>'>
                            <td>${attachment.name}</td>
                            <td>${attachment.size}</td>
                            <td>${attachment.description}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="form-group">
                    <div class="col-sm-offset-5 col-sm-8">
                        <input type="submit" value="Attach" data-toggle="modal"
                               data-target="#attachmentModal" class="btn btn-default btn-sm"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%--End of attachments--%>

    <%--Activities--%>
    <div class="col-md-5">
        <div class="panel panel-primary">

            <div class="panel-heading">
                <h3 class="panel-title">Activity stream</h3>
            </div>

            <div class="activity-body">
                <c:forEach var="activity" items="${activities}">
                    <div class="list-group">
                        <a class="list-group-item">
                            <div class="list-group-item-heading">
                                <small>${activity.date} <b>${activity.duration} min</b></small>
                            </div>
                            <p class="list-group-item-text"><code>${activity.member.project.name}
                                (${activity.member.employee.login}):</code><c:out value="${activity.comment}"/>
                            </p>
                        </a>
                    </div>
                </c:forEach>
            </div>
        </div>
        <input id="more" class="show-more" type="button" value="Show more..."/>
    </div>
    <%--End of activities--%>


</div>
<!-- /.container -->

<%@ include file="footer.jsp" %>