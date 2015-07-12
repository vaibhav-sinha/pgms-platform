var app = angular.module("adminApp", ['ngRoute']);

app.config(function($routeProvider) {
    $routeProvider
        .when('/category', {
        templateUrl : 'partials/category',
        controller : 'categoryController'
        })
        .when('/complaintStatus', {
            templateUrl : 'partials/complaintStatus',
            controller : 'complaintStatusController'
        })
        .when('/verificationStatus', {
            templateUrl : 'partials/verificationStatus',
            controller : 'verificationStatusController'
        })
        .when('/reviewStatus', {
            templateUrl : 'partials/reviewStatus',
            controller : 'reviewStatusController'
        })
        .when('/department', {
            templateUrl : 'partials/department',
            controller : 'departmentController'
        })
        .when('/designation', {
            templateUrl : 'partials/designation',
            controller : 'designationController'
        })
        .when('/location', {
            templateUrl : 'partials/location',
            controller : 'locationController'
        })
        .when('/officer', {
            templateUrl : 'partials/officer',
            controller : 'officerController'
        });
});

var categoryController = app.controller("categoryController", function($scope, $http) {
    //Create the UI mode control flags
    $scope.displayMode = true;
    $scope.createMode = false;
    $scope.updateMode = false;
    //Define variables
    $scope.selectedCategory = {};
    $scope.selectedDepartment = null;
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


var complaintStatusController = app.controller("complaintStatusController", function($scope, $http) {
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


var verificationStatusController = app.controller("verificationStatusController", function($scope, $http) {
    //Create the UI mode control flags
    $scope.displayMode = true;
    $scope.createMode = false;
    $scope.updateMode = false;
    //Define variables
    $scope.newVerificationStatus = {};
    //Define functions
    $scope.create = function() {
        $http.post("/admin/api/verificationStatus/save", $scope.newVerificationStatus).success(function(data, status) {
            $scope.getAllActive();
            $scope.switchToDisplayMode();
        }).error(function(status) {
        })
    };
    $scope.getAllActive = function() {
        $http.get("/admin/api/verificationStatus/getAllActive").success(function(data, status) {
            if(data.success == true) {
                $scope.allActive = data.data;
            }
        })
    };
    $scope.delete = function(entry) {
        $http.post("/admin/api/verificationStatus/delete", entry).success(function(data, status) {
            if(data.success == true) {
                $scope.getAllActive();
            }
        })
    };
    $scope.selectForUpdate = function(entry) {
        $scope.updateVerificationStatus = angular.copy(entry);
        $scope.switchToUpdateMode();
    };
    $scope.update = function() {
        $http.post("/admin/api/verificationStatus/update", $scope.updateVerificationStatus).success(function(data, status) {
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


var reviewStatusController = app.controller("reviewStatusController", function($scope, $http) {
    //Create the UI mode control flags
    $scope.displayMode = true;
    $scope.createMode = false;
    $scope.updateMode = false;
    //Define variables
    $scope.newReviewStatus = {};
    //Define functions
    $scope.create = function() {
        $http.post("/admin/api/reviewStatus/save", $scope.newReviewStatus).success(function(data, status) {
            $scope.getAllActive();
            $scope.switchToDisplayMode();
        }).error(function(status) {
        })
    };
    $scope.getAllActive = function() {
        $http.get("/admin/api/reviewStatus/getAllActive").success(function(data, status) {
            if(data.success == true) {
                $scope.allActive = data.data;
            }
        })
    };
    $scope.delete = function(entry) {
        $http.post("/admin/api/reviewStatus/delete", entry).success(function(data, status) {
            if(data.success == true) {
                $scope.getAllActive();
            }
        })
    };
    $scope.selectForUpdate = function(entry) {
        $scope.updateReviewStatus = angular.copy(entry);
        $scope.switchToUpdateMode();
    };
    $scope.update = function() {
        $http.post("/admin/api/reviewStatus/update", $scope.updateReviewStatus).success(function(data, status) {
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

var departmentController = app.controller("departmentController", function($scope, $http) {
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
            $scope.officers = data.data;
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
        /*$scope.selectedHod = angular.copy(entry.hod);
        $scope.selectedGro = angular.copy(entry.gro);
        $scope.selectedAgro = angular.copy(entry.agro);*/
        $scope.switchToUpdateMode();
    };
    $scope.update = function() {
        /*$scope.newDepartment.hod = angular.copy($scope.selectedHod);
        $scope.newDepartment.gro = angular.copy($scope.selectedGro);
        $scope.newDepartment.agro = angular.copy($scope.selectedAgro);*/
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



var designationController = app.controller("designationController", function($scope, $http) {
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



var locationController = app.controller("locationController", function($scope, $http) {
    //Create the UI mode control flags
    $scope.displayMode = true;
    $scope.createMode = false;
    $scope.updateMode = false;
    //Define variables
    $scope.newLocation = {};
    //Define functions
    $scope.create = function() {
        $http.post("/admin/api/location/save", $scope.newLocation).success(function(data, status) {
            $scope.getAllActive();
            $scope.switchToDisplayMode();
        }).error(function(status) {
        })
    };
    $scope.getAllActive = function() {
        $http.get("/admin/api/location/getAllActive").success(function(data, status) {
            if(data.success == true) {
                $scope.allActive = data.data;
            }
        })
    };
    $scope.delete = function(entry) {
        $http.post("/admin/api/location/delete", entry).success(function(data, status) {
            if(data.success == true) {
                $scope.getAllActive();
            }
        })
    };
    $scope.selectForUpdate = function(entry) {
        $scope.updateLocation = angular.copy(entry);
        $scope.switchToUpdateMode();
    };
    $scope.update = function() {
        $http.post("/admin/api/location/update", $scope.updateLocation).success(function(data, status) {
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




var officerController = app.controller("officerController", function($scope, $http) {
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
