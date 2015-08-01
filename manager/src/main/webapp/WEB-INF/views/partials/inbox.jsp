<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            ComplaintBox
            <small>{{count.new}} new complaints</small>
        </h1>
        <ol class="breadcrumb">
            <li class="active">ComplaintBox</li>
        </ol>
    </section>

    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-3" ng-if="user.role == 'ROLE_CMO'">
                <div class="box box-solid">
                    <div class="box-header with-border">
                        <h3 class="box-title">Filters</h3>
                    </div>
                    <div class="box-body no-padding">
                        <label for="department">Department</label><select id="department" ng-options="department.id as department.name for department in departmentList" ng-model="pgmsFilter.custom.departmentId"></select>
                        <label for="createdAfter">Created After</label><input type="date" id="createdAfter" ng-model="pgmsFilter.custom.createdAfter">
                        <label for="updatedAfter">Updated After</label><input type="date" id="updatedAfter" ng-model="pgmsFilter.custom.updatedAfter">
                        <button ng-click="selectFilter('custom')">Search</button>
                        <button ng-click="removeAllFilter()">Clear</button>
                    </div><!-- /.box-body -->
                </div><!-- /. box -->
            </div><!-- /.col -->
            <div class="col-md-3">
                <div class="box box-solid">
                    <div class="box-header with-border">
                        <h3 class="box-title">Folders</h3>
                    </div>
                    <div class="box-body no-padding">
                        <ul class="nav nav-pills nav-stacked">
                            <li ng-class="{'active' : currentData.filter=='new'}" ng-click="selectFilter('new')"><a href="#/"><i class="fa fa-inbox"></i> New <span class="label label-primary pull-right">{{count.new}}</span></a></li>
                            <li ng-class="{'active' : currentData.filter=='updated'}" ng-click="selectFilter('updated')"><a href="#/"><i class="fa fa-envelope-o"></i> Updated <span class="label label-primary pull-right">{{count.updated}}</span></a></li>
                            <li ng-class="{'active' : currentData.filter=='urgent'}" ng-click="selectFilter('urgent')"><a href="#/"><i class="fa fa-file-text-o"></i> Urgent <span class="label label-primary pull-right">{{count.urgent}}</span></a></li>
                            <li ng-class="{'active' : currentData.filter=='pending'}" ng-click="selectFilter('pending')"><a href="#/"><i class="fa fa-envelope-o"></i> Pending <span class="label label-primary pull-right">{{count.pending}}</span></a></li>
                            <li ng-if="user.role == 'ROLE_OFFICER' || user.role == 'ROLE_CMO'" ng-class="{'active' : currentData.filter=='verification'}" ng-click="selectFilter('verification')"><a href="#/"><i class="fa fa-filter"></i> In Verification <span class="label label-warning pull-right">{{count.verification}}</span></a></li>
                            <li ng-if="user.role == 'ROLE_OFFICER' || user.role == 'ROLE_CMO'" ng-class="{'active' : currentData.filter=='review'}" ng-click="selectFilter('review')"><a href="#/"><i class="fa fa-trash-o"></i> In Review <span class="label label-primary pull-right">{{count.review}}</span></a></li>
                        </ul>
                    </div><!-- /.box-body -->
                </div><!-- /. box -->
            </div><!-- /.col -->
            <div class="col-md-9">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">ComplaintBox</h3>
                        <div class="box-tools pull-right">
                            <div class="has-feedback">
                                <input type="text" class="form-control input-sm" placeholder="Search" ng-model="searchText" ng-model-options="{ debounce: 500 }"/>
                                <span class="glyphicon glyphicon-search form-control-feedback"></span>
                            </div>
                        </div><!-- /.box-tools -->
                    </div><!-- /.box-header -->
                    <div class="box-body no-padding">
                        <div class="mailbox-controls">
                            <!-- Check all button -->
                            <button class="btn btn-default btn-sm checkbox-toggle" style="visibility:hidden"><i class="fa fa-square-o"></i></button>
                            <div class="pull-right">
                                {{currentData.countDisplay}}
                                <div class="btn-group">
                                    <button class="btn btn-default btn-sm" ng-click="previousPage()" ng-disabled="currentData.page==0"><i class="fa fa-chevron-left"></i></button>
                                    <button class="btn btn-default btn-sm" ng-click="nextPage()" ng-disabled="currentData.page==currentData.totalPages-1"><i class="fa fa-chevron-right"></i></button>
                                </div><!-- /.btn-group -->
                            </div><!-- /.pull-right -->
                        </div>
                        <div class="table-responsive mailbox-messages">
                            <table class="table table-hover table-striped">
                                <tbody>
                                <tr ng-repeat="complaint in currentData.complaintList" ng-click="showComplaint(complaint)">
                                    <td class="mailbox-star"><i class="fa text-yellow" ng-class="complaint.urgent ? 'fa-star-o' : 'fa-star'"></i></td>
                                    <td class="mailbox-name">{{complaint.submitter.name}}</td>
                                    <td class="mailbox-subject"><b>Issue</b> - {{complaint.id | limitTo : 40}}</td>
                                    <td class="mailbox-attachment"></td>
                                    <td class="mailbox-date">{{complaint.createdOn | timeago}}</td>
                                </tr>
                                </tbody>
                            </table><!-- /.table -->
                        </div><!-- /.mail-box-messages -->
                    </div><!-- /.box-body -->
                    <div class="box-footer no-padding">
                        <div class="mailbox-controls">
                            <!-- Check all button -->
                            <button class="btn btn-default btn-sm checkbox-toggle" style="visibility:hidden"><i class="fa fa-square-o"></i></button>
                            <div class="pull-right">
                                {{currentData.countDisplay}}
                                <div class="btn-group">
                                    <button class="btn btn-default btn-sm"  ng-click="previousPage()" ng-disabled="currentData.page==0"><i class="fa fa-chevron-left"></i></button>
                                    <button class="btn btn-default btn-sm" ng-click="nextPage()" ng-disabled="currentData.page==currentData.totalPages-1"><i class="fa fa-chevron-right"></i></button>
                                </div><!-- /.btn-group -->
                            </div><!-- /.pull-right -->
                        </div>
                    </div>
                </div><!-- /. box -->
            </div><!-- /.col -->
        </div><!-- /.row -->
    </section><!-- /.content -->
</div><!-- /.content-wrapper -->