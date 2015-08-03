<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html ng-app="complaintApp">
<head>
    <meta charset="UTF-8">
    <%@ page isELIgnored="false" %>
    <title>PGMS | Submit Complaint</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.4 -->
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css" />
    <!-- Font Awesome Icons -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <!-- Ionicons -->
    <link href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet" type="text/css" />
    <!-- Theme style -->
    <link href="<c:url value="/resources/dist/css/AdminLTE.min.css"/>" rel="stylesheet" type="text/css" />
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link href="<c:url value="/resources/dist/css/skins/_all-skins.min.css"/>" rel="stylesheet" type="text/css" />
    <!-- iCheck -->
    <link href="<c:url value="/resources/plugins/iCheck/flat/blue.css"/>" rel="stylesheet" type="text/css" />
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="skin-blue sidebar-mini" ng-controller="complaintController">
<div class="wrapper">

    <header class="main-header">
        <!-- Logo -->
        <a href="#/" class="logo">
            <!-- mini logo for sidebar mini 50x50 pixels -->
            <span class="logo-mini"><b>PGMS</b>Delhi</span>
            <!-- logo for regular state and mobile devices -->
            <span class="logo-lg"><b>PGMS</b>Delhi</span>
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top" role="navigation">
            <div class="navbar-custom-menu">
            </div>
        </nav>
    </header>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <section class="content">
            <div class="row">
                <div class="col-md-9">
                    <div class="box box-primary">
                        <div class="box-header with-border">
                            <h3 class="box-title">Enter your complaint details</h3>
                        </div><!-- /.box-header -->
                        <!-- form start -->
                        <form role="form">
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="cDescription">Description</label>
                                    <input type="text" id="cDescription" name="Description" ng-model="newComplaint.description" class="form-control"/>
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" ng-model="newComplaint.urgent"> Urgent?
                                    </label>
                                </div>
                                <div class="form-group">
                                    <label for="cLocation">Location</label>
                                    <select id="cLocation" ng-options="location.name for location in locations" ng-model="selectedLocation"  class="form-control"></select>
                                </div>
                                <div class="form-group">
                                    <label for="cDepartment">Department</label>
                                    <select id="cDepartment" ng-options="department.name for department in departments" ng-model="selectedDepartment"  class="form-control"></select>
                                </div>
                            </div><!-- /.box-body -->
                        </form>
                    </div><!-- /.box -->
                    <div class="box box-primary">
                        <div class="box-header with-border">
                            <h3 class="box-title">Enter your personal details</h3>
                        </div><!-- /.box-header -->
                        <!-- form start -->
                        <form role="form">
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="sName">Name</label>
                                    <input type="text" id="sName" name="Description" ng-model="newComplaint.name" class="form-control"/>
                                </div>
                                <div class="form-group">
                                    <label for="cEmail">Email</label>
                                    <input type="text" id="cEmail" name="Email" ng-model="newComplaint.email" class="form-control"/>
                                </div>
                                <div class="form-group">
                                    <label for="cAddress">Address</label>
                                    <input type="text" id="cAddress" name="Address" ng-model="newComplaint.address" class="form-control"/>
                                </div>
                                <div class="form-group">
                                    <label for="sMobile">Mobile</label>
                                    <input type="text" id="sMobile" name="Mobile" ng-model="newComplaint.mobile" class="form-control"/>
                                </div>
                            </div><!-- /.box-body -->
                        </form>
                    </div><!-- /.box -->

                    <div class="box-footer">
                        <button ng-click="create()" class="btn btn-primary">Submit</button>
                    </div>

                </div>
            </div>
        </section>
    </div>
    <footer class="main-footer">
        <div class="pull-right hidden-xs">
            <b>Version</b> 1.0.0
        </div>
        <strong>Copyright &copy; 2015-2016 <a href="http://eswaraj.com">JanSetu Trust</a>.</strong> All rights reserved.
    </footer>

</div><!-- ./wrapper -->

<!-- Angular -->
<script src="<c:url value="/resources/lib/angular.min.js"/>" type="text/javascript"></script>
<!-- jQuery 2.1.4 -->
<script src="<c:url value="/resources/plugins/jQuery/jQuery-2.1.4.min.js"/>" type="text/javascript"></script>
<!-- Bootstrap 3.3.2 JS -->
<script src="<c:url value="/resources/lib/bootstrap.min.js"/>" type="text/javascript"></script>
<!-- Page Script -->
<script src="<c:url value="/resources/js/complaint.js"/>" type="text/javascript"></script>

</body>
</html>

