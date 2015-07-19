var app = angular.module("officerApp", ['ngRoute', 'ngtimeago']);

app.config(function($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl : 'partials/inbox',
            controller : 'inboxController'
        })
        .when('/complaint', {
            templateUrl : 'partials/complaint',
            controller : 'complaintController'
        })
});

var inboxController = app.controller('inboxController', function($scope, $http, $rootScope, $location) {
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
        'departmentId' : user.department.id,
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
        'departmentId' : user.department.id,
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
        'departmentId' : user.department.id,
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
        'departmentId' : user.department.id,
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
        'departmentId' : user.department.id,
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
        'departmentId' : user.department.id,
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
        'departmentId' : user.department.id,
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
        $rootScope.$broadcast('selectedComplaint', complaint);
        $location.path('#/complaint');
    };

    //Calls
    $scope.getAllCounts();
    $scope.getComplaints($scope.pgmsFilter.urgent);
    $scope.currentData.totalCount = $scope.count.urgent;

});

var complaintController = app.controller('complaintController', function($scope, $http, $location) {
    $scope.$on('selectedComplaint', function(event, arg) {
        console.log(arg);
        $scope.complaint = arg;
    });
});