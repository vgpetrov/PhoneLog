// TODO: test only
(function() {

    var directives = angular.module('AppDirectivesModule', ['MainModule']);

    directives.directive('ngSparkline', function($parse, LoginService) {
        return {
            restrict: 'A',
            template: '<div ng-click="logout()">Logout</div>',
            replace: true,
            scope:{
              ngClick: "="
            },
            link: function($scope, element, attrs) {
            },
            controller: function($scope, LoginService) {
                $scope.$on('someEvent', function(event, args) {
                    console.log(event, args);
                });

                $scope.logout = function() {
                    console.log("Logout");
                    LoginService.logout().then(function(data) {
                        console.log("Logout", data);
                    });
                };
            }
        }
    });

})();
