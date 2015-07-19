<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>PGMS Admin</title>
</head>

<body>

<div class="row">
  <div class="col-xs-10 col-xs-offset-1 col-sm-8 col-sm-offset-2 col-md-4 col-md-offset-4">
    <div class="login-panel panel panel-default">
      <div class="panel-heading">Log in</div>
      <c:if test="${not empty sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}">
        <div class="margin-bottom-30 text-danger font-size-13 row">
          <div class="col-xs-10 col-xs-offset-1">
              ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
          </div>
        </div>
      </c:if>
      <div class="panel-body">
        <form role="form" action="doLogin" method="post">
          <fieldset>
            <div class="form-group">
              <input class="form-control" placeholder="Username" name="j_username" type="text" autofocus="">
            </div>
            <div class="form-group">
              <input class="form-control" placeholder="Password" name="j_password" type="password" value="">
            </div>
            <button type="submit" class="btn btn-primary">Login</button>
          </fieldset>
        </form>
      </div>
    </div>
  </div><!-- /.col-->
</div><!-- /.row -->



<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/datepicker3.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet">

<script src="<c:url value="/resources/lib/angular.min.js" />"></script>
<script src="<c:url value="/resources/lib/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/resources/lib/bootstrap.min.js" />"></script>


<script>
  !function ($) {
    $(document).on("click","ul.nav li.parent > a > span.icon", function(){
      $(this).find('em:first').toggleClass("glyphicon-minus");
    });
    $(".sidebar span.icon").find('em:first').addClass("glyphicon-plus");
  }(window.jQuery);

  $(window).on('resize', function () {
    if ($(window).width() > 768) $('#sidebar-collapse').collapse('show')
  })
  $(window).on('resize', function () {
    if ($(window).width() <= 767) $('#sidebar-collapse').collapse('hide')
  })
</script>
</body>

</html>