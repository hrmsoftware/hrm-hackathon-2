angular.module('starter')
.factory('recycleService', function($http) {
	return {
		getFuture: function(type) {
			return $http.get("http://172.20.10.105:8080/trashme/future?type=" + type);
		}
	}
})
