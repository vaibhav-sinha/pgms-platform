<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>PGMS - Admin Dashboard</title>
</head>

<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#sidebar-collapse">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#"><span>PGMS</span>Admin</a>
      <ul class="user-menu">
        <li class="dropdown pull-right">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span> Admin <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
          </ul>
        </li>
      </ul>
    </div>

  </div><!-- /.container-fluid -->
</nav>

<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
  <ul class="nav menu">
    <li class="active"><a href="<c:url value="/ui/category"/>"><span class="glyphicon glyphicon-dashboard"></span> Category</a></li>
    <li><a href="<c:url value="/ui/complaintStatus"/>"><span class="glyphicon glyphicon-th"></span> Complaint Status</a></li>
    <li><a href="<c:url value="/ui/department"/>"><span class="glyphicon glyphicon-stats"></span> Department</a></li>
    <li><a href="<c:url value="/ui/designation"/>"><span class="glyphicon glyphicon-list-alt"></span> Designation</a></li>
    <li><a href="<c:url value="/ui/location"/>"><span class="glyphicon glyphicon-pencil"></span> Location</a></li>
    <li><a href="<c:url value="/ui/officer"/>"><span class="glyphicon glyphicon-info-sign"></span> Officer</a></li>
  </ul>
</div><!--/.sidebar-->

<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
  <div class="row">
    <ol class="breadcrumb">
      <li><a href="#"><span class="glyphicon glyphicon-home"></span></a></li>
      <li class="active">Dashboard</li>
    </ol>
  </div><!--/.row-->

  <div class="row">
    <div class="col-lg-12">
      <h1 class="page-header">Dashboard</h1>
    </div>
  </div><!--/.row-->

  <div>

    <div class="col-md-12">

      <div class="panel panel-blue">
        <div class="panel-heading dark-overlay"><span class="glyphicon glyphicon-check"></span>Category List</div>
          <div class="panel-footer">
              <div class="input-group">
							<span class="input-group-btn">
								<button class="btn btn-primary btn-md" id="btn-todo">Create</button>
							</span>
              </div>
          </div>
        <div class="panel-body">
          <ul class="todo-list">
            <li class="todo-list-item">
              <div>
                <table width="100%">
                    <tr>
                        <td align="center">Id</td>
                        <td align="center">Name</td>
                        <td align="center"><span class="glyphicon glyphicon-pencil"/></td>
                        <td align="center"><span class="glyphicon glyphicon-trash"/></td>
                    </tr>
                </table>
              </div>
            </li>
          </ul>
        </div>

      </div>

    </div><!--/.col-->
  </div><!--/.row-->
</div>	<!--/.main-->

<script src="<c:url value="/resources/lib/angular.min.js" />"></script>
<script src="<c:url value="/resources/lib/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/resources/lib/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/lib/html5shiv.min.js" />"></script>
<script src="<c:url value="/resources/lib/respond.min.js" />"></script>

<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/datepicker3.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet">

<script>
  $('#calendar').datepicker({
  });

  !function ($) {
    $(document).on("click","ul.nav li.parent > a > span.icon", function(){
      $(this).find('em:first').toggleClass("glyphicon-minus");
    });
    $(".sidebar span.icon").find('em:first').addClass("glyphicon-plus");
  }(window.jQuery);

  $(window).on('resize', function () {
    if ($(window).width() > 768) $('#sidebar-collapse').collapse('show')
  });
  $(window).on('resize', function () {
    if ($(window).width() <= 767) $('#sidebar-collapse').collapse('hide')
  });
</script>
</body>

</html>
