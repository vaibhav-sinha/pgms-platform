/**
 * Created by manish on 1/8/15.
 */
angular.module('officerApp')
    .controller('TimeLineController', ['$scope', '$http', '$log', '$routeParams', function ($scope, $http, $log, $routeParams) {


        $http.get('/manager/getUpdateHistory/' + $routeParams.complaintId).success(function (data) {
            $scope.updateHistory = data.data;
        });


    }])
    .controller('TimeLineElementController', ['$scope', '$http', '$log', '$routeParams', function ($scope, $http, $log, $routeParams) {


        $scope.mapping = {
            'COMPLAINT_STATUS_CHANGE' : ['Complaint Status', 'ComplaintStatus'],
            'FORWARD' : ['Department','Department'],
            'VERIFICATION_STATUS_CHANGE' : ['Verfication Status','VerificationStatus'],
            'REVIEW_STATUS_CHANGE' : ['Review Status','ReviewStatus']
        };


        var updateMessage = function() {
            var change = $scope.mapping[$scope.historyElem.userAction];

            var isComment = $scope.historyElem.userAction == 'COMMENT';
            $scope.historyElem.messsage = $scope.historyElem.officer.name + (isComment ? ' commented' : ' changed ' + change[0] + ' from ' + $scope.historyElem['old' + change[1]].name + ' to ' + $scope.historyElem['new' + change[1]].name);
        };

        $scope.$watch('historyElem',updateMessage);



    }]);