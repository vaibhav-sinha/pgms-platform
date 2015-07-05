/**
 * Created by user-1 on 5/7/15.
 */

var app = angular.module("complaintApp", []);

var controller = app.controller("complaintController", function($scope, $http) {
    //Define variables
    $scope.selectedDepartment = null;
    $scope.selectedLocation = null;
    $scope.selectedCategory = null;
    $scope.newComplaint = {};
    //Define functions
    $scope.getAllDepartment = function() {
        $http.get("/portal/getAllDepartments").success(function(data, status) {
            $scope.departments = data.data;
        })
    };
    $scope.getAllLocation = function() {
        $http.get("/portal/getAllLocations").success(function(data, status) {
            $scope.locations = data.data;
        })
    };
    $scope.getAllCategory = function() {
        $http.get("/portal/getAllCategories").success(function(data, status) {
            $scope.categories = data.data;
        })
    };
    $scope.create = function() {
        $scope.newComplaint.department = angular.copy($scope.selectedDepartment);
        $scope.newComplaint.location = angular.copy($scope.selectedLocation);
        $scope.newComplaint.category = angular.copy($scope.selectedCategory);
        $http.post("/portal/submit", $scope.newComplaint).success(function(data, status) {
            $scope.newComplaint = null;
        }).error(function(status) {
        })
    };

    //Run on load
    $scope.getAllDepartment();
    $scope.getAllLocation();
    $scope.getAllCategory();
});
