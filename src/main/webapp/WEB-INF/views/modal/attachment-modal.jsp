<!-- Modal -->
<div class="modal fade" id="attachmentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">

        <!-- Employee registration form-->
        <div class="generic-container">
            <div class="well lead">Employee Registration Form</div>

            <form:form method="POST" enctype="multipart/form-data" modelAttribute="attachment" class="form-horizontal">
                <form:input type="hidden" path="id" id="id" value="${employee.id}"/>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-3 control-lable" for="file">File</label>

                        <div class="col-md-7">
                            <input type="file" class="file" id="file" name="file"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-3 control-lable" for="description">Description</label>

                        <div class="col-md-7">
                            <form:textarea type="text" path="description" id="description"
                                           class="form-control input-sm"/>
                            <div class="has-error">
                                <form:errors path="description" class="help-inline"/>
                            </div>
                        </div>
                    </div>
                </div>
                <form:input path="task.id" type="hidden" value="${task.id}"/>
                <form:input path="project.id" type="hidden" value="${project.id}"/>

                <c:if test="${showAttachError}">
                    <div class="has-error">
                        <c:out value="${upload_error}"/>
                    </div>
                </c:if>

                <div class="row">
                    <div class="form-actions floatRight">

                        <input type="submit" value="Upload" class="btn btn-primary btn-sm"
                               formaction="/dashboard/upload"/>

                    </div>
                </div>
            </form:form>
        </div>
        <!-- End of user registration form-->
    </div>
</div>
<!-- Modal -->