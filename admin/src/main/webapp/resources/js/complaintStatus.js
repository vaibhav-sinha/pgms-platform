/**
 * Created by user-1 on 5/7/15.
 */

var app = angular.module("complaintStatusApp", ["base64"]);

var controller = app.controller("complaintStatusController", function($scope, $http, $base64) {
    //Set basic-auth headers to all requests
    $http.defaults.headers.common.Authorization = "Basic " + $base64.encode("admin" + ":" + "admin");
    //Create the UI mode control flags
    $scope.displayMode = true;
    $scope.createMode = false;
    $scope.updateMode = false;
    //Define variables
    $scope.newComplaintStatus = {};
    //Define functions
    $scope.create = function() {
        $http.post("/admin/api/complaintStatus/save", $scope.newComplaintStatus).success(function(data, status) {
            $scope.getAllActive();
            $scope.switchToDisplayMode();
        }).error(function(status) {
        })
    };
    $scope.getAllActive = function() {
        $http.get("/admin/api/complaintStatus/getAllActive").success(function(data, status) {
            if(data.success == true) {
                $scope.allActive = data.data;
            }
        })
    };
    $scope.delete = function(entry) {
        $http.post("/admin/api/complaintStatus/delete", entry).success(function(data, status) {
            if(data.success == true) {
                $scope.getAllActive();
            }
        })
    };
    $scope.selectForUpdate = function(entry) {
        $scope.updateComplaintStatus = angular.copy(entry);
        $scope.switchToUpdateMode();
    };
    $scope.update = function() {
        $http.post("/admin/api/complaintStatus/update", $scope.updateComplaintStatus).success(function(data, status) {
            if(data.success == true) {
                $scope.getAllActive();
                $scope.switchToDisplayMode();
            }
        })
    };
    $scope.switchToDisplayMode = function() {
        $scope.displayMode = true;
        $scope.createMode = false;
        $scope.updateMode = false;
    };
    $scope.switchToCreateMode = function() {
        $scope.displayMode = false;
        $scope.createMode = true;
        $scope.updateMode = false;
    };$scope.switchToUpdateMode = function() {
        $scope.displayMode = false;
        $scope.createMode = false;
        $scope.updateMode = true;
    };

    //Run on load
    $scope.getAllActive();
});
