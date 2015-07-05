/**
 * Created by user-1 on 5/7/15.
 */

var app = angular.module("designationApp", ["base64"]);

var controller = app.controller("designationController", function($scope, $http, $base64) {
    //Set basic-auth headers to all requests
    $http.defaults.headers.common.Authorization = "Basic " + $base64.encode("admin" + ":" + "admin");
    //Create the UI mode control flags
    $scope.displayMode = true;
    $scope.createMode = false;
    $scope.updateMode = false;
    //Define variables
    $scope.newDesignation = {};
    //Define functions
    $scope.create = function() {
        $http.post("/admin/api/designation/save", $scope.newDesignation).success(function(data, status) {
            $scope.getAllActive();
            $scope.switchToDisplayMode();
        }).error(function(status) {
        })
    };
    $scope.getAllActive = function() {
        $http.get("/admin/api/designation/getAllActive").success(function(data, status) {
            if(data.success == true) {
                $scope.allActive = data.data;
            }
        })
    };
    $scope.delete = function(entry) {
        $http.post("/admin/api/designation/delete", entry).success(function(data, status) {
            if(data.success == true) {
                $scope.getAllActive();
            }
        })
    };
    $scope.selectForUpdate = function(entry) {
        $scope.updateDesignation = angular.copy(entry);
        $scope.switchToUpdateMode();
    };
    $scope.update = function() {
        $http.post("/admin/api/designation/update", $scope.updateDesignation).success(function(data, status) {
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
