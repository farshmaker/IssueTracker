<!-- Modal -->
<div class="modal fade" id="projectModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">

    <!-- Project registration form-->
    <div class="generic-container">
      <div class="well lead">Project Registration Form</div>

      <form:form method="POST"  modelAttribute="project" class="form-horizontal">
        <form:input type="hidden" path="id" id="id" value="${project.id}"/>

        <div class="row">
          <div class="form-group col-md-12">
            <label class="col-md-3 control-lable" for="name">Name</label>

            <div class="col-md-7">
              <form:input type="text" path="name" id="name"
                          class="form-control input-sm" value="${project.name}"/>
              <div class="has-error">
                <form:errors path="name" class="help-inline"/>
              </div>
            </div>
          </div>
        </div>

        <div class="row">
          <div class="form-group col-md-12">
            <label class="col-md-3 control-lable" for="description">Description</label>

            <div class="col-md-7">
              <form:textarea type="text" path="description" id="description"
                          class="form-control input-sm" value="${project.description}"/>
              <div class="has-error">
                <form:errors path="description" class="help-inline"/>
              </div>
            </div>
          </div>
        </div>

        <div class="row">
          <div class="form-actions floatRight">
            <c:choose>
              <c:when test="${editProject}">
                <input type="submit" value="Update" class="btn btn-primary btn-sm" formaction="edit-project"/> or <a

                      href="JavaScript:refreshAdmin()">Cancel</a>
              </c:when>
              <c:otherwise>
                <input type="submit" value="Register" class="btn btn-primary btn-sm" formaction="add-project"/> or <a

                      href="JavaScript:refreshAdmin()">Cancel</a>
              </c:otherwise>
            </c:choose>
          </div>
        </div>
      </form:form>
    </div><!-- End of project registration form-->


  </div>
</div>
<!-- Modal -->