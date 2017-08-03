<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- Modal -->
<div class="modal fade" id="issueModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">

        <!-- Employee registration form-->
        <div class="generic-container">
            <div class="well lead">Create Issue</div>

            <form:form method="POST" action="/create-issue" modelAttribute="task" class="form-horizontal">
                <form:input type="hidden" path="id" id="id" value="${task.id}"/>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-3 control-lable" for="project.id">Project</label>

                        <div class="col-md-7">
                            <form:select path="project.id" items="${projects}" multiple="true" itemValue="id"
                                         itemLabel="name" class="form-control input-sm"/>
                            <div class="has-error">
                                <form:errors path="project.id" class="help-inline"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-3 control-lable" for="planStartDate">Start date</label>

                        <div class="col-md-7">
                            <form:input path="planStartDate" id="planStartDate" class="form-control input-sm"
                                        placeholder="click to show datepicker"
                                        readonly="true" cssStyle="cursor: default"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-3 control-lable" for="planEndDate">End date</label>

                        <div class="col-md-7">
                            <form:input path="planEndDate" id="planEndDate" class="form-control input-sm"
                                        placeholder="click to show datepicker"
                                        readonly="true" cssStyle="cursor: default"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-3 control-lable" for="description">Description</label>

                        <div class="col-md-7">
                            <form:textarea type="text" path="description" id="description"
                                           class="form-control input-sm"/>
                            <div>
                                <form:errors path="description" class="help-inline"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="has-error">
                    <c:out value="${error_issue}"/>
                </div>

                <div class="row">
                    <div class="form-actions floatRight">

                        <input type="submit" value="Create" class="btn btn-primary btn-sm"/>

                    </div>
                </div>
            </form:form>
        </div>
        <!-- End of user registration form-->
    </div>
</div>
<!-- Modal -->