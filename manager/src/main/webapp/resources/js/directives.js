/**
 * Created by user-1 on 1/8/15.
 */
angular.module('officerApp')
    .directive('statsTable', [function () {
        return {
            restrict: 'E',
            templateUrl: 'partials/stats-table',
            scope: {
                keys: '=keys',
                datas: '=datas'
            }
        }
    }]);

angular.module('officerApp')
    .directive('timelineElement',[function () {
        return{
            restrict: 'E',
            templateUrl: 'partials/timeline-element',
            controller: 'TimeLineElementController',
            scope : false
        };

    }])
    .directive('timeline',[function () {
        return{
            restrict: 'E',
            templateUrl: 'partials/timeline',
            controller: 'TimeLineController',
            scope : false
        };

    }]);
