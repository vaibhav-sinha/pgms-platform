<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Read Complaint
        </h1>
        <ol class="breadcrumb">
            <li><a href="#/"><i class="fa fa-dashboard"></i> Home</a></li>
            <li class="active">Mailbox</li>
        </ol>
    </section>

    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-9">
                <div class="box box-primary">
                    <div class="box-body no-padding">
                        <div class="mailbox-read-info">
                            <h3>Complaint Number : {{complaint.id}}</h3>
                            <h5>Department : {{complaint.department.name}} <span class="mailbox-read-time pull-right">{{complaint.createdOn | timeago}}</span></h5>
                            <h5>Status : {{status.name}}</h5>
                        </div><!-- /.mailbox-read-info -->
                        <div class="mailbox-read-message">
                            <b>Name</b> : {{complaint.submitter.name}}
                            <br>
                            <b>Email</b> : {{complaint.submitter.email}}
                            <br>
                            <b>Mobile</b> : {{complaint.submitter.mobile}}
                            <br>
                            <b>Address</b> : {{complaint.submitter.address}}
                        </div><!-- /.mailbox-read-message -->
                        <div class="mailbox-read-message">
                            <b>Complaint</b><br>
                            {{complaint.description}}
                        </div><!-- /.mailbox-read-message -->
                    </div><!-- /.box-body -->
                </div><!-- /. box -->
                </div>
            </div>
        </section>

                <section class="content-header">
                <h1>
                    Update Complaint
                </h1>
                </section>
    <section class="content">
        <div class="row">
            <div class="col-md-9">
                <tabset>
                    <tab heading="Comment">
                        <div class="box-body box">
                            <div class="form-group">
                                <label>Comment</label>
                                <textarea id="comment" ng-model="update.comment" class="form-control" rows="3" placeholder="Comment here..."></textarea>
                                <div class="box-footer">
                                    <button class="btn btn-primary" ng-click="postUpdate('COMMENT')">Submit</button>
                                </div>
                            </div>
                        </div>
                    </tab>
                    <tab heading="Change Status">
                        <div class="box-body box">
                        <div ng-if="user.role == 'ROLE_OFFICER'" class="form-group">
                            <label for="cStatus">Complaint Status</label>
                            <select id="cStatus" class="form-control" ng-options="status.name for status in statusList" ng-model="complaintUpdate.selectedComplaintStatus"></select>
                            <label>Comment</label>
                            <textarea id="commentStatus" ng-model="update.comment" class="form-control" rows="3" placeholder="Comment here..."></textarea>
                            <div class="box-footer">
                                <button class="btn btn-primary" ng-click="postUpdate('COMPLAINT_STATUS_CHANGE')">Save</button>
                            </div>
                        </div>
                        <div ng-if="user.role == 'ROLE_CALL_CENTRE'" class="form-group">
                            <label for="vStatus">Verification Status</label>
                            <select id="vStatus" class="form-control" ng-options="status.name for status in statusList" ng-model="complaintUpdate.selectedVerificationStatus"></select>
                            <label>Comment</label>
                            <textarea id="commentStatus" ng-model="update.comment" class="form-control" rows="3" placeholder="Comment here..."></textarea>
                            <div class="box-footer">
                                <button class="btn btn-primary" ng-click="postUpdate('VERIFICATION_STATUS_CHANGE')">Save</button>
                            </div>
                        </div>
                        <div ng-if="user.role == 'ROLE_CMO'" class="form-group">
                            <label for="rStatus">Review Status</label>
                            <select id="rStatus" class="form-control" ng-options="status.name for status in statusList" ng-model="complaintUpdate.selectedReviewStatus"></select>
                            <label>Comment</label>
                            <textarea id="commentStatus" ng-model="update.comment" class="form-control" rows="3" placeholder="Comment here..."></textarea>
                            <div class="box-footer">
                                <button class="btn btn-primary" ng-click="postUpdate('REVIEW_STATUS_CHANGE')">Save</button>
                            </div>
                        </div>
                            </div>
                    </tab>
                    <tab heading="Forward" ng-if = "user.role != 'ROLE_CALL_CENTRE'">
                        <div class="box-body box">
                            <label for="department">Department</label>
                                <select id="department" class="form-control" ng-options="department.name for department in departmentList" ng-model="complaintUpdate.selectedDepartment"></select>
                            <label>Comment</label>
                            <textarea id="commentStatus" ng-model="update.comment" class="form-control" rows="3" placeholder="Comment here..."></textarea>
                            <div class="box-footer">
                                <button class="btn btn-primary" ng-click="postUpdate('FORWARD')">Save</button>
                            </div>
                        </div>

                    </tab>
                </tabset>
            </div><!-- /.col -->
        </div><!-- /.row -->
    </section><!-- /.content -->
    <section class="content-header">
        <h1>Timeline</h1>
    </section>
    <section class="content">
        <div class="row">
            <timeline complaint_id="complaint.id"></timeline>
        </div>
    </section>
</div>
