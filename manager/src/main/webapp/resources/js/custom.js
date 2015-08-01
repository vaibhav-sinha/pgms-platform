/**
 * Created by manish on 1/8/15.
 */
angular.module('officerApp')
    .filter('reverse', function() {
    return function(items) {
        return items.slice().reverse();
    };
});
