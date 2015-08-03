<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Statistics
            <small>Location, Department and Officer stats</small>
        </h1>
        <ol class="breadcrumb">
            <li class="active">Statistics</li>
        </ol>
    </section>

    <br><br>

    <tabset>
        <tab heading="Officer">
            <stats-table keys="stats.officer.keys" datas = "stats.officer.data"></stats-table>
        </tab>
        <tab heading="Location">
            <stats-table keys="stats.location.keys" datas = "stats.location.data"></stats-table>
        </tab>
        <tab heading="Department">
            <stats-table keys="stats.department.keys" datas = "stats.department.data"></stats-table>
        </tab>
    </tabset>
    <!-- Main content -->
    <!-- /.content -->
</div><!-- /.content-wrapper -->
