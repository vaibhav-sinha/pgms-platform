/**
 * Created by user-1 on 5/7/15.
 */

var app = angular.module("departmentApp", ["base64"]);

var controller = app.controller("departmentController", function($scope, $http, $base64) {
    //Set basic-auth headers to all requests
    $http.defaults.headers.common.Authorization = "Basic " + $base64.encode("admin" + ":" + "admin");
    //Create the UI mode control flags
    $scope.displayMode = true;
    $scope.createMode = false;
    $scope.updateMode = false;
    //Define variables
    $scope.selectedDepartment = {};
    $scope.selectedHod = null;
    $scope.selectedGro = null;
    $scope.selectedAgro = null;
    $scope.newDepartment = {};
    //Define functions
    $scope.getAllOfficer = function() {
        $http.get("/admin/api/officer/getAllActive").success(function(data, status) {
            $scope.departments = data.data;
        })
    };
    $scope.create = function() {
        $scope.newDepartment.hod = angular.copy($scope.selectedHod);
        $scope.newDepartment.gro = angular.copy($scope.selectedGro);
        $scope.newDepartment.agro = angular.copy($scope.selectedAgro);
        $http.post("/admin/api/department/save", $scope.newDepartment).success(function(data, status) {
            $scope.getAllActive();
            $scope.switchToDisplayMode();
        }).error(function(status) {
        })
    };
    $scope.getAllActive = function() {
        $http.get("/admin/api/department/getAllActive").success(function(data, status) {
            if(data.success == true) {
                $scope.allActive = data.data;
            }
        })
    };
    $scope.delete = function(entry) {
        $http.post("/admin/api/department/delete", entry).success(function(data, status) {
            if(data.success == true) {
                $scope.getAllActive();
            }
        })
    };
    $scope.selectForUpdate = function(entry) {
        $scope.updateDepartment = angular.copy(entry);
        $scope.switchToUpdateMode();
    };
    $scope.update = function() {
        $scope.newDepartment.hod = angular.copy($scope.selectedHod);
        $scope.newDepartment.gro = angular.copy($scope.selectedGro);
        $scope.newDepartment.agro = angular.copy($scope.selectedAgro);
        $http.post("/admin/api/department/update", $scope.updateDepartment).success(function(data, status) {
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
    $scope.getAllOfficer();
});
