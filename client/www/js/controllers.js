angular.module('starter.controllers', [])

.controller('AppCtrl', function($scope, $ionicModal, $timeout) {
  $ionicModal.fromTemplateUrl('templates/about.html', {
    scope: $scope
  }).then(function(modal) {
    $scope.modal = modal;
  });

  $scope.closeAbout = function() {
    $scope.modal.hide();
  };

  $scope.about = function() {
    $scope.modal.show();
  };
})

.controller('RecycleCtrl', function($scope, $stateParams) {
  $scope.index = 1;
  $scope.labels = ["Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "Lördag", "Söndag"];
  $scope.series = ['8-12', '12-16', '16-20'];
  $scope.onClick = function (points, evt) {
    console.log(points, evt);
  };
  $scope.toggle = function() {
    if ($scope.index == 0) {
      $scope.index = 1;
      $scope.labels = ["Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "Lördag", "Söndag"];
      $scope.series = ['8-12', '12-16', '16-20'];
      $scope.data = [
          [15, 29, 50, 41, 76, 65, 45],
          [65, 59, 80, 81, 56, 55, 40],
          [28, 48, 40, 19, 86, 27, 90]
        ];
    }
    else {
      $scope.index = 0;
      $scope.labels = ["Måndag", "Tisdag", "Onsdag"];
      $scope.series = ['8-10', '10-12', '12-14', '14-16', '16-18', '18-20'];
      $scope.data = [
          [65, 59, 80],
          [35, 39, 30],
          [25, 29, 20],
          [15, 29, 50],
          [28, 48, 40]
        ];
    }

  };
  $scope.toggle();
});
