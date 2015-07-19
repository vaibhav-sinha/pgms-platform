<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html ng-app="officerApp">
<head>
    <meta charset="UTF-8">
    <%@ page isELIgnored="false" %>
    <title>PGMS | ComplaintBox</title>
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
<body class="skin-blue sidebar-mini">
<div class="wrapper">

    <header class="main-header">
        <sec:authentication var="username" property="name" />
        <sec:authentication var="designation" property="designation.name" />
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
                <ul class="nav navbar-nav">
                    <!-- User Account: style can be found in dropdown.less -->
                    <li class="dropdown user user-menu">
                        <a href="#/" class="dropdown-toggle" data-toggle="dropdown">
                            <img src="<c:url value="/resources/images/flag.jpg"/>" class="user-image" alt="Flag Image" />
                            <span class="hidden-xs">${user.name}</span>
                        </a>
                        <ul class="dropdown-menu">
                            <!-- User image -->
                            <li class="user-header">
                                <img src="<c:url value="/resources/images/flag.jpg"/>" class="img-circle" alt="Flag Image" />
                                <p>
                                    ${user.name}
                                    <small>${user.designation.name}</small>
                                </p>
                            </li>
                            <!-- Menu Body -->
                            <li class="user-body">
                                <div class="col-xs-4 text-center">
                                    <a href="#">Followers</a>
                                </div>
                                <div class="col-xs-4 text-center">
                                    <a href="#">Sales</a>
                                </div>
                                <div class="col-xs-4 text-center">
                                    <a href="#">Friends</a>
                                </div>
                            </li>
                            <!-- Menu Footer-->
                            <li class="user-footer">
                                <div class="pull-left">
                                    <a href="#/" class="btn btn-default btn-flat">Profile</a>
                                </div>
                                <div class="pull-right">
                                    <a href="signout" class="btn btn-default btn-flat">Sign out</a>
                                </div>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>
    </header>

    <!-- Content Wrapper. Contains page content -->
    <div ng-view>
    </div>
    <footer class="main-footer">
        <div class="pull-right hidden-xs">
            <b>Version</b> 1.0.0
        </div>
        <strong>Copyright &copy; 2015-2016 <a href="http://almsaeedstudio.com">JanSetu Trust</a>.</strong> All rights reserved.
    </footer>

</div><!-- ./wrapper -->

<!-- Angular -->
<script src="<c:url value="/resources/lib/angular.min.js"/>" type="text/javascript"></script>
<!-- Angular Route -->
<script src="<c:url value="/resources/lib/angular-route.min.js"/>" type="text/javascript"></script>
<!-- Angular Timeago -->
<script src="<c:url value="/resources/lib/ngtimeago.js"/>" type="text/javascript"></script>
<!-- jQuery 2.1.4 -->
<script src="<c:url value="/resources/plugins/jQuery/jQuery-2.1.4.min.js"/>" type="text/javascript"></script>
<!-- Bootstrap 3.3.2 JS -->
<script src="<c:url value="/resources/lib/bootstrap.min.js"/>" type="text/javascript"></script>
<!-- Slimscroll -->
<script src="<c:url value="/resources/plugins/slimScroll/jquery.slimscroll.min.js"/>" type="text/javascript"></script>
<!-- FastClick -->
<script src="<c:url value="/resources/plugins/fastclick/fastclick.min.js"/>" type="text/javascript"></script>
<!-- AdminLTE App -->
<script src="<c:url value="/resources/dist/js/app.min.js"/>" type="text/javascript"></script>
<!-- iCheck -->
<script src="<c:url value="/resources/plugins/iCheck/icheck.min.js"/>" type="text/javascript"></script>
<!-- Page Script -->
<script src="<c:url value="/resources/js/officer.js"/>" type="text/javascript"></script>
<script>
    var user = ${userJson};
</script>
<script>
    $(function () {
        //Enable iCheck plugin for checkboxes
        //iCheck for checkbox and radio inputs
        $('.mailbox-messages input[type="checkbox"]').iCheck({
            checkboxClass: 'icheckbox_flat-blue',
            radioClass: 'iradio_flat-blue'
        });

        //Enable check and uncheck all functionality
        $(".checkbox-toggle").click(function () {
            var clicks = $(this).data('clicks');
            if (clicks) {
                //Uncheck all checkboxes
                $(".mailbox-messages input[type='checkbox']").iCheck("uncheck");
                $(".fa", this).removeClass("fa-check-square-o").addClass('fa-square-o');
            } else {
                //Check all checkboxes
                $(".mailbox-messages input[type='checkbox']").iCheck("check");
                $(".fa", this).removeClass("fa-square-o").addClass('fa-check-square-o');
            }
            $(this).data("clicks", !clicks);
        });

        //Handle starring for glyphicon and font awesome
        $(".mailbox-star").click(function (e) {
            e.preventDefault();
            //detect type
            var $this = $(this).find("a > i");
            var glyph = $this.hasClass("glyphicon");
            var fa = $this.hasClass("fa");

            //Switch states
            if (glyph) {
                $this.toggleClass("glyphicon-star");
                $this.toggleClass("glyphicon-star-empty");
            }

            if (fa) {
                $this.toggleClass("fa-star");
                $this.toggleClass("fa-star-o");
            }
        });
    });
</script>
<!-- AdminLTE for demo purposes -->
<script src="<c:url value="/resources/dist/js/demo.js"/>" type="text/javascript"></script>
</body>
</html>
