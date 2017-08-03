<!-- Modal -->
<div class="modal fade" id="assignModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">

        <!-- Assign project form-->
        <c class="generic-container">
            <div class="well lead">Assign Task Form</div>

            <form:form method="POST" modelAttribute="assigment" class="form-horizontal">
                <form:input type="hidden" path="task.id" id="id" value="${task.id}"/>

            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="member.id">Members</label>

                    <div class="col-md-7">
                        <form:select path="member.id" items="${task.project.members}" multiple="true" itemValue="id"
                                     itemLabel="employee.login" class="form-control input-sm"/>
                        <div class="has-error">
                            <form:errors path="member.id" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="description">Description</label>

                    <div class="col-md-7">
                        <form:textarea path="description" class="form-control" rows="3" placeholder="Description"/>
                        <div class="has-error">
                            <form:errors path="description" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>

            <c:if test="${showAssignError}">
                <div class="has-error">
                    <c:out value="${assign_error}"/>
                </div>
            </c:if>

            <div class="row">
                <div class="form-actions floatRight">
                    <input type="submit" value="Assign" class="btn btn-primary btn-sm"
                           formaction="/dashboard/assign"/> or <a href="/dashboard/task-${task.id}">Cancel</a>
                </div>
            </div>
            </form:form>
    </div>
    <!-- End of assign project form-->

</div>
</div>
<!-- Modal -->
