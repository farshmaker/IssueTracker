<!-- Modal -->
<div class="modal fade" id="assignModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">

        <!-- Assign project form-->
        <div class="generic-container">
            <div class="well lead">Assign Project Form</div>

            <form:form method="POST" modelAttribute="member" class="form-horizontal">
                <form:input type="hidden" path="id" id="id"/>

                <div class="row">
                    <div class="form-group col-md-12">
                        <div class="col-md-7">
                            <form:input type="hidden" path="employee.id" id="employee.id"
                                        class="form-control input-sm" readonly="readonly" value="${employee.id}"/>
                            <div class="has-error">
                                <form:errors path="employee.id" class="help-inline"/>
                            </div>
                        </div>
                    </div>
                </div>

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
                        <label class="col-md-3 control-lable" for="role">Role</label>

                        <div class="col-md-7">
                            <form:select path="role.id" items="${roles}" multiple="true" itemValue="id"
                                         itemLabel="name" class="form-control input-sm"/>
                            <div class="has-error">
                                <form:errors path="role.id" class="help-inline"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="has-error">
                    <c:out value="${errorAssign}"/>
                </div>

                <div class="row">
                    <div class="form-actions floatRight">
                        <input type="submit" value="Assign" class="btn btn-primary btn-sm"
                               formaction="assign"/> or <a href="JavaScript:refreshAdmin()">Cancel</a>
                    </div>
                </div>
            </form:form>
        </div>
        <!-- End of assign project form-->


    </div>
</div>
<!-- Modal -->