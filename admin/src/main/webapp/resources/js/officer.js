/**
 * Created by user-1 on 5/7/15.
 */

var app = angular.module("officerApp", ["base64"]);

var controller = app.controller("officerController", function($scope, $http, $base64) {
    //Set basic-auth headers to all requests
    $http.defaults.headers.common.Authorization = "Basic " + $base64.encode("admin" + ":" + "admin");
    //Create the UI mode control flags
    $scope.displayMode = true;
    $scope.createMode = false;
    $scope.updateMode = false;
    //Define variables
    $scope.selectedOfficer = {};
    $scope.selectedDepartment = null;
    $scope.selectedDesignation = null;
    $scope.selectedRole = null;
    $scope.newOfficer = {};
    $scope.roles = ["ROLE_OFFICER", "ROLE_CMO", "ROLE_CALL_CENTRE"];
    //Define functions
    $scope.getAllDepartment = function() {
        $http.get("/admin/api/department/getAllActive").success(function(data, status) {
            $scope.departments = data.data;
        })
    };
    $scope.getAllDesignation = function() {
        $http.get("/admin/api/designation/getAllActive").success(function(data, status) {
            $scope.designations = data.data;
        })
    };
    $scope.create = function() {
        $scope.newOfficer.department = angular.copy($scope.selectedDepartment);
        $scope.newOfficer.designation = angular.copy($scope.selectedDesignation);
        $scope.newOfficer.role = angular.copy($scope.selectedRole);
        $http.post("/admin/api/officer/save", $scope.newOfficer).success(function(data, status) {
            $scope.getAllActive();
            $scope.switchToDisplayMode();
        }).error(function(status) {
        })
    };
    $scope.getAllActive = function() {
        $http.get("/admin/api/officer/getAllActive").success(function(data, status) {
            if(data.success == true) {
                $scope.allActive = data.data;
            }
        })
    };
    $scope.delete = function(entry) {
        $http.post("/admin/api/officer/delete", entry).success(function(data, status) {
            if(data.success == true) {
                $scope.getAllActive();
            }
        })
    };
    $scope.selectForUpdate = function(entry) {
        $scope.updateOfficer = angular.copy(entry);
        $scope.switchToUpdateMode();
    };
    $scope.update = function() {
        $scope.newOfficer.department = angular.copy($scope.selectedDepartment);
        $http.post("/admin/api/officer/update", $scope.updateOfficer).success(function(data, status) {
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
    $scope.getAllDepartment();
    $scope.getAllDesignation();
});
