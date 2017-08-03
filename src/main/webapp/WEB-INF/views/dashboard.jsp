<%@ include file="header.jsp" %>
<%@ include file="modal/issue-modal.jsp" %>

<div class="container" style="padding-top: 100px">

    <%--Activities--%>
    <div class="col-xs-6">
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
                                (${activity.member.employee.login}):</code><c:out value="${activity.comment}"/></p>
                        </a>
                    </div>
                </c:forEach>
            </div>

        </div>
        <input id="more" class="show-more" type="button" value="Show more..."/>
    </div>
    <%--Activities--%>

    <%--Assigning--%>
    <div class="col-xs-6">
        <div class="panel panel-primary">

            <div class="panel-heading">
                <h3 class="panel-title">Assigned to me</h3>
            </div>

            <div class="panel-body">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>Project</th>
                        <th>Summary</th>
                    </tr>
                    </thead>
                    <tbody style="cursor:pointer">
                    <c:forEach var="task" items="${tasks}">
                        <tr class='clickable-row' data-href='<c:url value="/dashboard/task-${task.id}"/>'>
                            <td>${task.project.name}</td>
                            <td>${task.description}</td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>

            </div>

        </div>
    </div><%--Assigning--%>


</div>
<!-- /.container -->


<%@ include file="footer.jsp" %>

