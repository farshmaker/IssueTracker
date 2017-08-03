<!-- Bootstrap core JavaScript -->
<spring:url value="/resources/js/bootstrap.min.js" var="bootstrapJs"/>
<spring:url value="/resources/js/jquery-1.11.3.js" var="jqueryJs"/>
<spring:url value="/resources/js/bootstrap-datepicker.js" var="datePickerJs"/>
<script src="${jqueryJs}"></script>
<script src="${bootstrapJs}"></script>
<script src="${datePickerJs}"></script>
<script type="text/javascript">
    jQuery(document).ready(function ($) {
        $(".clickable-row").click(function () {
            window.document.location = $(this).data("href");
        });
    });
</script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#planStartDate').datepicker({
            format: "yyyy-mm-dd"
        });
    });
</script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#planEndDate').datepicker({
            format: "yyyy-mm-dd"
        });
    });
</script>
<c:if test="${triggerIssueModal}">
    <script type="text/javascript">
        $(window).load(function () {
            $('#issueModal').modal('show');
        });
    </script>
</c:if>
<c:if test="${triggerAttachmentModal}">
    <script type="text/javascript">
        $(window).load(function () {
            $('#attachmentModal').modal('show');
        });
    </script>
</c:if>
<c:if test="${triggerAssignModal}">
    <script type="text/javascript">
        $(window).load(function () {
            $('#assignModal').modal('show');
        });
    </script>
</c:if>
<c:if test="${triggerAttachmentModal}">
    <script type="text/javascript">
        $(window).load(function () {
            $('#attachmentModal').modal('show');
        });
    </script>
</c:if>
<script type="text/javascript">
    $(document).ready(function () {
        var fromIndex = 3;
        $('#more').click(function () {
            $.ajax({
                url: '/dashboard/activity',
                method: 'POST',
                data: {"fromIndex": fromIndex}
            }).done(function (data) {
                var activities = JSON.parse(data);
                if (data.length > 0) {
                    $.each(activities, function (index, element) {
                        $(".activity-body").append("<div class='list-group'> <a class='list-group-item'> " +
                                "<div class='list-group-item-heading'> <small>" + element["date"] +
                                "<b> " + element["duration"] + " min</b></small></div> " +
                                "<p class='list-group-item-text'><code>" + element["project"]
                                + "(" + element["login"] + "):</code>" +  element["comment"]
                                + " </p> </a> </div>");
                    });
                    fromIndex += 3;
                }
            });
        });
    });
</script>
</body>
</html>