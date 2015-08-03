var app = angular.module("officerApp", ['ngRoute', 'ngtimeago','ui.bootstrap']);

app.config(function($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl : 'partials/inbox',
            controller : 'inboxController'
        })
        .when('/complaint/:complaintId', {
            templateUrl : 'partials/complaint',
            controller : 'complaintController'
        })
        .when('/stats', {
            templateUrl : 'partials/stats',
            controller : 'statsController'
        })
});

var shared = app.factory('shared', function() {
    return {};
});

var inboxController = app.controller('inboxController', function($scope, $http, $rootScope, $location, shared) {
    $scope.user = user;
    $scope.searchText = "";
    $scope.currentData = {
        'filter' : 'urgent',
        'complaintList' : null,
        'totalCount' : null,
        'page' : 0,
        'countDisplay' : null,
        'totalPages' : null
    };
    $scope.count = {
        'new': null,
        'updated' : null,
        'urgent' : null,
        'pending' : null,
        'verification' : null,
        'review' : null
    };
    $scope.pgmsFilter = {};
    $scope.pgmsFilter.none = {
        'departmentId' : null,
        'categoryId' : null,
        'locationId' : null,
        'page' : 0,
        'pageSize' : 20,
        'createdAfter' : null,
        'updatedAfter' : null,
        'complaintStatusId' : null,
        'verificationStatusId' : null,
        'reviewStatusId' : null,
        'reopened' : null,
        'searchText' : null,
        'isUrgent' : null,
        'userRole' : null,
        'phase' : null
    };
    $scope.pgmsFilter.new = {
        'departmentId' : user.department != null ? user.department.id : null,
        'categoryId' : null,
        'locationId' : null,
        'page' : 0,
        'pageSize' : 20,
        'createdAfter' : user.lastSignedIn,
        'updatedAfter' : null,
        'complaintStatusId' : null,
        'verificationStatusId' : null,
        'reviewStatusId' : null,
        'reopened' : null,
        'searchText' : null,
        'isUrgent' : null,
        'userRole' : null,
        'phase' : null
    };
    $scope.pgmsFilter.updated = {
        'departmentId' : user.department != null ? user.department.id : null,
        'categoryId' : null,
        'locationId' : null,
        'page' : 0,
        'pageSize' : 20,
        'createdAfter' : null,
        'updatedAfter' : user.lastSignedIn,
        'complaintStatusId' : null,
        'verificationStatusId' : null,
        'reviewStatusId' : null,
        'reopened' : null,
        'searchText' : null,
        'isUrgent' : null,
        'userRole' : null,
        'phase' : null
    };
    $scope.pgmsFilter.urgent = {
        'departmentId' : user.department != null ? user.department.id : null,
        'categoryId' : null,
        'locationId' : null,
        'page' : 0,
        'pageSize' : 20,
        'createdAfter' : null,
        'updatedAfter' : null,
        'complaintStatusId' : null,
        'verificationStatusId' : null,
        'reviewStatusId' : null,
        'reopened' : null,
        'searchText' : null,
        'isUrgent' : true,
        'userRole' : null,
        'phase' : null
    };
    $scope.pgmsFilter.pending    = {
        'departmentId' : user.department != null ? user.department.id : null,
        'categoryId' : null,
        'locationId' : null,
        'page' : 0,
        'pageSize' : 20,
        'createdAfter' : null,
        'updatedAfter' : null,
        'complaintStatusId' : null,
        'verificationStatusId' : null,
        'reviewStatusId' : null,
        'reopened' : null,
        'searchText' : null,
        'isUrgent' : null,
        'userRole' : null,
        'phase' : 'complaint'
    };
    $scope.pgmsFilter.verification = {
        'departmentId' : user.department != null ? user.department.id : null,
        'categoryId' : null,
        'locationId' : null,
        'page' : 0,
        'pageSize' : 20,
        'createdAfter' : null,
        'updatedAfter' : null,
        'complaintStatusId' : null,
        'verificationStatusId' : null,
        'reviewStatusId' : null,
        'reopened' : null,
        'searchText' : null,
        'isUrgent' : null,
        'userRole' : null,
        'phase' : 'verification'
    };
    $scope.pgmsFilter.review = {
        'departmentId' : user.department != null ? user.department.id : null,
        'categoryId' : null,
        'locationId' : null,
        'page' : 0,
        'pageSize' : 20,
        'createdAfter' : null,
        'updatedAfter' : null,
        'complaintStatusId' : null,
        'verificationStatusId' : null,
        'reviewStatusId' : null,
        'reopened' : null,
        'searchText' : null,
        'isUrgent' : null,
        'userRole' : null,
        'phase' : 'review'
    };
    $scope.pgmsFilter.search = {
        'departmentId' : user.department != null ? user.department.id : null,
        'categoryId' : null,
        'locationId' : null,
        'page' : 0,
        'pageSize' : 20,
        'createdAfter' : null,
        'updatedAfter' : null,
        'complaintStatusId' : null,
        'verificationStatusId' : null,
        'reviewStatusId' : null,
        'reopened' : null,
        'searchText' : $scope.searchText,
        'isUrgent' : null,
        'userRole' : null
    };
    $scope.pgmsFilter.custom = {
        'departmentId' : user.department != null ? user.department.id : null,
        'categoryId' : null,
        'locationId' : null,
        'page' : 0,
        'pageSize' : 20,
        'createdAfter' : null,
        'updatedAfter' : null,
        'complaintStatusId' : null,
        'verificationStatusId' : null,
        'reviewStatusId' : null,
        'reopened' : null,
        'searchText' : null,
        'isUrgent' : null,
        'userRole' : null,
        'phase' : null
    };

    $scope.getComplaints = function(filter) {
        $http.post("/manager/complaints", filter).success(function(data) {
            $scope.currentData.complaintList = data.data;
        })
    };
    $scope.getComplaintsCount = function(filter, key, callback) {
        $http.post("/manager/complaints/count", filter).success(function(data) {
            $scope.count[key] = data.data;
            if(callback != null) {
                callback(data.data);
            }
        })
    };

    $scope.processCountForDisplay = function(count) {
        if(count === undefined) {
            $scope.currentData.countDisplay = '';
            return;
        }
        if(count == 0) {
            $scope.currentData.countDisplay = '0/0';
        }
        else {
            var start = ($scope.currentData.page)*20 + 1;
            var end = ($scope.currentData.page + 1)*20;

            if(end > count) {
                end = count;
            }

            $scope.currentData.countDisplay = start + '-' + end + '/' + count;
        }
        $scope.currentData.totalPages = Math.floor(count/20 + (count%20 > 0));
    };

    $scope.getAllCounts = function() {
        $scope.getComplaintsCount($scope.pgmsFilter.new, 'new');
        $scope.getComplaintsCount($scope.pgmsFilter.updated, 'updated');
        $scope.getComplaintsCount($scope.pgmsFilter.urgent, 'urgent', $scope.processCountForDisplay);
        $scope.getComplaintsCount($scope.pgmsFilter.pending, 'pending');
        $scope.getComplaintsCount($scope.pgmsFilter.verification, 'verification');
        $scope.getComplaintsCount($scope.pgmsFilter.review, 'review');
    };

    $scope.selectFilter = function(filter) {
        $scope.pgmsFilter[$scope.currentData.filter].page = 0;
        $scope.currentData.filter = filter;
        $scope.getComplaints($scope.pgmsFilter[filter]);
        $scope.currentData.totalCount = $scope.count[filter];
        $scope.currentData.totalPages = Math.floor($scope.currentData.totalCount/20 + ($scope.currentData.totalCount%20 > 0));
        $scope.processCountForDisplay($scope.currentData.totalCount);
    };

    $scope.previousPage = function() {
        $scope.pgmsFilter[$scope.currentData.filter].page = $scope.pgmsFilter[$scope.currentData.filter].page - 1;
        $scope.currentData.page = $scope.currentData.page - 1;
        $scope.getComplaints($scope.currentData.filter);
    };

    $scope.nextPage = function() {
        $scope.filter[$scope.currentData.filter].page = $scope.pgmsFilter[$scope.currentData.filter].page + 1;
        $scope.currentData.page = $scope.currentData.page + 1;
        $scope.getComplaints($scope.currentData.filter);
    };

    $scope.$watch('searchText', function() {
        if($scope.searchText.length == 0) {
            $scope.selectFilter('urgent');
            return;
        }
        if($scope.searchText.length > 3) {
            $scope.pgmsFilter.search.searchText = $scope.searchText;
            $scope.selectFilter('search');
        }
    });

    $scope.showComplaint = function(complaint) {
        $location.path('/complaint/' + complaint.id);
    };

    $scope.removeAllFilter = function () {
        $scope.pgmsFilter.custom.departmentId = null;
        $scope.pgmsFilter.custom.createdAfter = null;
        $scope.pgmsFilter.custom.updatedAfter = null;
        $scope.selectFilter('custom');
    };

    $scope.getDepartmentList = function() {
        $http.get("/manager/departmentList").success(function(data) {
            $scope.departmentList = data.data;
        });
    };

    //Calls
    $scope.getAllCounts();
    $scope.getDepartmentList();
    $scope.getComplaints($scope.pgmsFilter.urgent);
    $scope.currentData.totalCount = $scope.count.urgent;

});

var complaintController = app.controller('complaintController', function($scope, $http, $location, shared,$routeParams) {

    $scope.user = user;

    $scope.complaint= {};
    $scope.complaintUpdate = {};
    $scope.update = {};
    $http.get('/manager/complaint/' + $routeParams.complaintId).success(function(data) {
        $scope.complaint = data.data;
        $scope.status = $scope.complaint.complaintStatus || $scope.complaint.verificationStatus || $scope.complaint.reviewStatus;
        $scope.update = {
            "complaint" : $scope.complaint,
            "userAction" : null,
            "oldComplaintStatus" : $scope.complaint.complaintStatus,
            "newComplaintStatus" : null,
            "oldVerificationStatus" : $scope.complaint.verificationStatus,
            "newVerificationStatus" : null,
            "oldReviewStatus" : $scope.complaint.reviewStatus,
            "newReviewStatus" : null,
            "oldCategory" : $scope.complaint.category,
            "newCategory" : null,
            "oldDepartment" : $scope.complaint.department,
            "newDepartment" : null,
            "comment" : null
        };
    });

    $scope.comment = "";




    $scope.postUpdate = function(userAction) {
        var updateData = angular.copy($scope.update);
        updateData.userAction = userAction;
        updateData.newComplaintStatus = $scope.complaintUpdate.selectedComplaintStatus;
        updateData.newVerificationStatus = $scope.complaintUpdate.selectedVerificationStatus;
        updateData.newReviewStatus = $scope.complaintUpdate.selectedReviewStatus;
        updateData.newCategory = $scope.selectedCategory;
        updateData.newDepartment = $scope.complaintUpdate.selectedDepartment;

        $http.post('/manager/saveUpdate', updateData).success(function(data) {
            $scope.updateHistory.push(data.data);
            $http.get('/manager/complaint/' + $scope.complaint.id).success(function(data) {
                $scope.complaint = data.data;
                $scope.status = $scope.complaint.complaintStatus || $scope.complaint.verificationStatus || $scope.complaint.reviewStatus;
                $scope.complaintUpdate = {};
            });

        });
    };

    $scope.getDepartmentList = function() {
        $http.get("/manager/departmentList").success(function(data) {
           $scope.departmentList = data.data;
        });
    };

    $scope.getStatusList = function() {
        $http.get("/manager/statusList").success(function(data) {
            $scope.statusList = data.data;
        });
    };

    //Calls
    $scope.getDepartmentList();
    $scope.getStatusList();

});

var statsController = app.controller('statsController', function($scope, $http) {
    $scope.stats = {};
    $scope.getStats = function() {
        $http.get('/manager/cmo/locationStats').success(function(data) {
            $scope.stats.location = data.data;
        });
        $http.get('/manager/cmo/departmentStats').success(function(data) {
            $scope.stats.department = data.data;
        });
        $http.get('/manager/cmo/officerStats').success(function(data) {
            $scope.stats.officer = data.data;
        });
    };

    //Calls
    $scope.getStats();
});
