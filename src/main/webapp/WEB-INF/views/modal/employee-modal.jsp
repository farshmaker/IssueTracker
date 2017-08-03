<!-- Modal -->
<div class="modal fade" id="employeeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">

    <!-- Employee registration form-->
    <div class="generic-container">
      <div class="well lead">Employee Registration Form</div>

      <form:form method="POST"  modelAttribute="employee" class="form-horizontal">
        <form:input type="hidden" path="id" id="id" value="${employee.id}"/>

        <div class="row">
          <div class="form-group col-md-12">
            <label class="col-md-3 control-lable" for="firstName">First Name</label>

            <div class="col-md-7">
              <form:input type="text" path="firstName" id="firstName"
                          class="form-control input-sm" value="${employee.firstName}"/>
              <div class="has-error">
                <form:errors path="firstName" class="help-inline"/>
              </div>
            </div>
          </div>
        </div>

        <div class="row">
          <div class="form-group col-md-12">
            <label class="col-md-3 control-lable" for="lastName">Last Name</label>

            <div class="col-md-7">
              <form:input type="text" path="lastName" id="lastName"
                          class="form-control input-sm" value="${employee.lastName}"/>
              <div class="has-error">
                <form:errors path="lastName" class="help-inline"/>
              </div>
            </div>
          </div>
        </div>

        <div class="row">
          <div class="form-group col-md-12">
            <label class="col-md-3 control-lable" for="login">Login</label>

            <div class="col-md-7">
              <c:choose>
                <c:when test="${editEmployee}">
                  <form:input type="text" path="login" id="login" class="form-control

input-sm" readonly="true" value="${employee.login}"/>
                </c:when>
                <c:otherwise>
                  <form:input type="text" path="login" id="login" class="form-control
input-sm"/>
                  <div class="has-error">
                    <form:errors path="login" class="help-inline"/>
                  </div>
                </c:otherwise>
              </c:choose>
            </div>
          </div>
        </div>

        <div class="row">
          <div class="form-group col-md-12">
            <label class="col-md-3 control-lable" for="password">Password</label>

            <div class="col-md-7">
              <form:input type="text" path="password" id="password"
                          class="form-control input-sm" value="${employee.password}"/>
              <div class="has-error">
                <form:errors path="password" class="help-inline"/>
              </div>
            </div>
          </div>
        </div>

        <div class="row">
          <div class="form-group col-md-12">
            <label class="col-md-3 control-lable" for="position">Position</label>

            <div class="col-md-7">
              <form:select path="position" items="${positions}" multiple="true" itemValue="id"
                           itemLabel="name" class="form-control input-sm"/>
              <div class="has-error">
                <form:errors path="position" class="help-inline"/>
              </div>
            </div>
          </div>
        </div>

        <div class="row">
          <div class="form-actions floatRight">
            <c:choose>
              <c:when test="${editEmployee}">
                <input type="submit" value="Update" class="btn btn-primary btn-sm" formaction="edit-employee"/> or <a

                      href="JavaScript:refreshAdmin()">Cancel</a>
              </c:when>
              <c:otherwise>
                <input type="submit" value="Register" class="btn btn-primary btn-sm" formaction="add-employee"/> or <a

                      href="JavaScript:refreshAdmin()">Cancel</a>
              </c:otherwise>
            </c:choose>
          </div>
        </div>
      </form:form>
    </div> <!-- End of user registration form-->
  </div>
</div>
<!-- Modal -->