angular.module('starter')
.factory('recycleService', function($http) {
	return {
		getFuture: function(type) {
			return $http.get("http://localhost:8080/trashme/future?type=" + type);
		}
	}
})
