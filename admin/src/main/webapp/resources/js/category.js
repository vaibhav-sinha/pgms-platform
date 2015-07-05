/**
 * Created by user-1 on 5/7/15.
 */

var app = angular.module("categoryApp", ["base64"]);

var controller = app.controller("categoryController", function($scope, $http, $base64) {
    //Set basic-auth headers to all requests
    $http.defaults.headers.common.Authorization = "Basic " + $base64.encode("admin" + ":" + "admin");
    //Create the UI mode control flags
    $scope.displayMode = true;
    $scope.createMode = false;
    $scope.updateMode = false;
    //Define variables
    $scope.selectedCategory = {};
    $scope.selectedDepartment = {};
    $scope.newCategory = {};
    //Define functions
    $scope.getAllDepartment = function() {
        $http.get("/admin/api/department/getAllActive").success(function(data, status) {
            $scope.departments = data.data;
        })
    };
    $scope.create = function() {
        $scope.newCategory.department = angular.copy($scope.selectedDepartment);
        $http.post("/admin/api/category/save", $scope.newCategory).success(function(data, status) {
            $scope.getAllActive();
            $scope.switchToDisplayMode();
        }).error(function(status) {
        })
    };
    $scope.getAllActive = function() {
        $http.get("/admin/api/category/getAllActive").success(function(data, status) {
            if(data.success == true) {
                $scope.allActive = data.data;
            }
        })
    };
    $scope.delete = function(entry) {
        $http.post("/admin/api/category/delete", entry).success(function(data, status) {
            if(data.success == true) {
                $scope.getAllActive();
            }
        })
    };
    $scope.selectForUpdate = function(entry) {
        $scope.updateCategory = angular.copy(entry);
        $scope.switchToUpdateMode();
    };
    $scope.update = function() {
        $scope.newCategory.department = angular.copy($scope.selectedDepartment);
        $http.post("/admin/api/category/update", $scope.updateCategory).success(function(data, status) {
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
});
