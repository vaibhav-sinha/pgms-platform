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
                            <li ng-class="{'active' : currentData.filter=='verification'}" ng-click="selectFilter('verification')"><a href="#/"><i class="fa fa-filter"></i> In Verification <span class="label label-warning pull-right">{{count.verification}}</span></a></li>
                            <li ng-class="{'active' : currentData.filter=='review'}" ng-click="selectFilter('review')"><a href="#/"><i class="fa fa-trash-o"></i> In Review <span class="label label-primary pull-right">{{count.review}}</span></a></li>
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
                                <input type="text" class="form-control input-sm" placeholder="Search" ng-model="searchText"/>
                                <span class="glyphicon glyphicon-search form-control-feedback"></span>
                            </div>
                        </div><!-- /.box-tools -->
                    </div><!-- /.box-header -->
                    <div class="box-body no-padding">
                        <div class="mailbox-controls">
                            <!-- Check all button -->
                            <button class="btn btn-default btn-sm checkbox-toggle"><i class="fa fa-square-o"></i></button>
                            <button class="btn btn-default btn-sm"><i class="fa fa-refresh"></i></button>
                            <div class="pull-right">
                                {{currentData.countDisplay}}
                                <div class="btn-group">
                                    <button class="btn btn-default btn-sm"><i class="fa fa-chevron-left"></i></button>
                                    <button class="btn btn-default btn-sm"><i class="fa fa-chevron-right"></i></button>
                                </div><!-- /.btn-group -->
                            </div><!-- /.pull-right -->
                        </div>
                        <div class="table-responsive mailbox-messages">
                            <table class="table table-hover table-striped">
                                <tbody>
                                <tr ng-repeat="complaint in currentData.complaintList">
                                    <td><input type="checkbox" /></td>
                                    <td class="mailbox-star"><a href="#"><i class="fa text-yellow" ng-class="complaint.urgent ? 'fa-star-o' : 'fa-star'"></i></a></td>
                                    <td class="mailbox-name"><a href="read-mail.html">{{complaint.submitter.name}}</a></td>
                                    <td class="mailbox-subject"><b>AdminLTE 2.0 Issue</b> - {{complaint.description | limitTo : 40}}...</td>
                                    <td class="mailbox-attachment"></td>
                                    <td class="mailbox-date">{{complaint.createdOn | timeago}}</td>
                                </tr>
                                <tr>
                                    <td><input type="checkbox" /></td>
                                    <td class="mailbox-star"><a href="#"><i class="fa fa-star-o text-yellow"></i></a></td>
                                    <td class="mailbox-name"><a href="read-mail.html">Alexander Pierce</a></td>
                                    <td class="mailbox-subject"><b>AdminLTE 2.0 Issue</b> - Trying to find a solution to this problem...</td>
                                    <td class="mailbox-attachment"><i class="fa fa-paperclip"></i></td>
                                    <td class="mailbox-date">28 mins ago</td>
                                </tr>
                                </tbody>
                            </table><!-- /.table -->
                        </div><!-- /.mail-box-messages -->
                    </div><!-- /.box-body -->
                    <div class="box-footer no-padding">
                        <div class="mailbox-controls">
                            <!-- Check all button -->
                            <button class="btn btn-default btn-sm checkbox-toggle"><i class="fa fa-square-o"></i></button>
                            <button class="btn btn-default btn-sm"><i class="fa fa-refresh"></i></button>
                            <div class="pull-right">
                                1-50/200
                                <div class="btn-group">
                                    <button class="btn btn-default btn-sm"><i class="fa fa-chevron-left"></i></button>
                                    <button class="btn btn-default btn-sm"><i class="fa fa-chevron-right"></i></button>
                                </div><!-- /.btn-group -->
                            </div><!-- /.pull-right -->
                        </div>
                    </div>
                </div><!-- /. box -->
            </div><!-- /.col -->
        </div><!-- /.row -->
    </section><!-- /.content -->
</div><!-- /.content-wrapper -->