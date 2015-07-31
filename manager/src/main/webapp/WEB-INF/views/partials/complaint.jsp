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
                            <h5>Status : {{status}}</h5>
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
                <div class="box box-primary">
                    <div ng-if="user.role == 'ROLE_OFFICER'">
                        <label for="cStatus">ComplaintStatus</label><select id="cStatus" class="form-control" ng-options="status.name for status in statusList" ng-model="selectedComplaintStatus"></select>
                        <button ng-click="postUpdate('COMPLAINT_STATUS_CHANGE')">Save</button>
                    </div>
                    <div ng-if="user.role == 'ROLE_CALL_CENTRE'">
                        <label for="vStatus">VerificationStatus</label><select id="vStatus" class="form-control" ng-options="status.name for status in statusList" ng-model="selectedVerificationStatus"></select>
                        <button ng-click="postUpdate('VERIFICATION_STATUS_CHANGE')">Save</button>
                    </div>
                    <div ng-if="user.role == 'ROLE_CMO'">
                        <label for="rStatus">ReviewStatus</label><select id="rStatus" class="form-control" ng-options="status.name for status in statusList" ng-model="selectedReviewStatus"></select>
                        <button ng-click="postUpdate('REVIEW_STATUS_CHANGE')">Save</button>
                    </div>
                    <label for="comment">Comment</label><input type="text" id="comment" ng-model="comment"/>
                    <button ng-click="postUpdate('COMMENT')">Save</button>
                </div>
            </div><!-- /.col -->
        </div><!-- /.row -->
    </section><!-- /.content -->
</div>
