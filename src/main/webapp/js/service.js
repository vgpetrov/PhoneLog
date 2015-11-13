(function() {
    var service = angular.module('MainModule', []);

    service.service('LoginService', function($q, $http) {
        var data = {};

        data.login = function(name, password) {
            var deferred = $q.defer();

            var requestData = {
                'username': name,
                'password': password
            };

            $http({
                method: 'POST',
                url: '/login',
                //params: requestData,
                data: "username="+ name+"&password="+password,
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            }).success(function(data, status, headers, config) {
                deferred.resolve(data);
            }).error(function(data, status, headers, config) {
                deferred.resolve(false);
            });

            return deferred.promise;
        };

        data.logout = function() {
            var deferred = $q.defer();

            $http({
                method: 'GET',
                url: '/logout'
            }).success(function(data, status, headers, config) {
                deferred.resolve(data);
            }).error(function(data, status, headers, config) {
                deferred.resolve(false);
            });

            return deferred.promise;
        };

        data.register = function(name, password, grecaptcha) {
            var deferred = $q.defer();

            var requestData = {
                'name': name,
                'password': password,
                'grecaptcha': grecaptcha
            };

            $http({
                method: 'POST',
                url: '/register',
                params: requestData
                //data: "name=" + name+"&password="+password +"&grecapthca="+grecaptcha
            }).success(function(data, status, headers, config) {
                deferred.resolve(data);
            }).error(function(data, status, headers, config) {
                deferred.resolve(false);
            });

            return deferred.promise;
        };

        return data;
    });

    service.service('AuthenticationService', function($q, $http) {
        var data = {};

        data.checkAuth = function() {
            var deferred = $q.defer();

            $http({
                method: 'GET',
                url: '/check/auth'
            }).success(function(data, status, headers, config) {
                deferred.resolve(data);
            }).error(function(data, status, headers, config) {
                deferred.resolve(false);
            });

            return deferred.promise;
        };

        return data;
    });

    service.service('MainService', function($q, $http) {
        var data = {};

        data.list = function() {
            var deffered = $q.defer();

            $http({
                method: 'GET',
                url: '/web/list'
            }).success(function(data, status, headers, config) {
                deffered.resolve(data);
            }).error(function(data, status, headers, config) {
                deffered.resolve(false);
            });

            return deffered.promise;
        };

        data.details = function(id) {
            var deffered = $q.defer();

            var requestData = {'id':id};

            $http({
                method: 'GET',
                url: '/web/details',
                params: requestData
            }).success(function(data, status, headers, config) {
                deffered.resolve(data);
            }).error(function(data, status, headers, config) {
                deffered.resolve(false);
            });

            return deffered.promise;
        };

        return data;
    });

})();
