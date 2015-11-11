var app = angular.module('project', ['ngRoute', 'MainModule', 'AppDirectivesModule']);

app.config(function($routeProvider, $locationProvider) {
    $routeProvider
        .when('/', {
            controller:'LoginController',
            templateUrl:'templates/login/login.html',
            resolve: {
                checkAuth: function(AuthenticationService) {
                    return AuthenticationService.checkAuth();
                }
            }
        })
        .when('/details/:id', {
            controller:'DetailsController',
            templateUrl:'templates/main/details.html',
            resolve: {
                checkAuth: function(AuthenticationService) {
                    return AuthenticationService.checkAuth();
                }
            }
        })
        .when('/list', {
            controller:'ListController',
            templateUrl:'templates/main/list.html',
            resolve: {
                checkAuth: function(AuthenticationService) {
                    return AuthenticationService.checkAuth();
                }
            }
        })
        .when('/createUser', {
            controller:'RegisterUserController',
            templateUrl:'templates/main/registerUser.html'
        })
        .otherwise({
            redirectTo:'/'
        });
    $locationProvider.html5Mode(true);
});

app.controller('DetailsController', function($scope, $location, $routeParams, checkAuth, MainService) {
    $scope.detailsModel = {};

    $scope.load = function(id) {
        MainService.details(id).then(function(data) {
            $scope.detailsModel = data;
        });
    };

    var init = function() {
        if (checkAuth.authenticated) {
            $scope.load($routeParams.id);
        }
    };

    init();
});

app.controller('ListController', function($scope, $location, $routeParams, checkAuth, MainService) {
    $scope.listModel = {};
    $scope.listModel.calls = [];

    $scope.load = function() {
        MainService.list().then(function(data) {
            console.log(data);
            $scope.listModel.calls = data;
        });
    };

    $scope.details = function(id) {
        $location.path('/details/'+id);
    };

    var init = function() {
        $scope.$emit('someEvent', "hello from list controller");
        if (checkAuth.authenticated) {
            $scope.load();
        }
    };

    init();
});

app.controller('LoginController', function($scope, $location, LoginService, checkAuth) {
    $scope.authCheck = checkAuth;
    $scope.name = "";
    $scope.pass = "";

    $scope.login = function() {
        LoginService.login($scope.name, $scope.pass).then(function(data) {
            if (data.success) {
                $location.path("/list");
            } else {
                // TODO: show login error
            }
        });
    };

    $scope.createUser = function() {
        $location.path("/createUser");
    };
});

app.controller('RegisterUserController', function($scope, LoginService) {
    $scope.name = "";
    $scope.password = "";

    $scope.register = function() {
        LoginService.register($scope.name, $scope.password).then(function(data) {
            console.log(data);
        });
    }
});


// TODO: delete
app.controller('TestController', function($scope) {
    $scope.test = "userUAUAUA";

    $scope.hello = function() {
        console.log("hello!!!!!!!!!!!!!!!!!!");
    };
});