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

.controller('RecycleCtrl', function($scope, $stateParams, recycleService) {
  var type = 'day';
  $scope.title = 'Dagens aktivitet per tidsintervall';
  $scope.toggleTitle = 'Se per vecka';

  var init = function(type) {
    recycleService.getFuture(type).then(function (future) {
      $scope.labels = future.data.labels;
      $scope.series = future.data.series;
      $scope.data = future.data.data;
    });
  }

  $scope.onClick = function (points, evt) {
    console.log(points, evt);
  };
  $scope.toggle = function() {
    if (type === 'day') {
      type = 'week';
      $scope.title = 'Aktivitet per veckodag per tidsintervall';
      $scope.toggleTitle = 'Se för idag';
    }
    else {
      type = 'day';
      $scope.title = 'Dagens aktivitet per tidsintervall';
      $scope.toggleTitle = 'Se per vecka';
    }
    init(type);
  }
  init(type);
});
