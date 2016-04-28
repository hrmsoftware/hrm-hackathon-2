angular.module('starter.controllers', [])

.controller('AppCtrl', function($scope, $ionicModal, $timeout) {
  $ionicModal.fromTemplateUrl('templates/about.html', {
    scope: $scope
  }).then(function(modal) {
    $scope.modal = modal;
  });

  $ionicModal.fromTemplateUrl('templates/open.html', {
    scope: $scope
  }).then(function(modal) {
    $scope.openTimes = modal;
  });

  $scope.closeAbout = function() {
    $scope.modal.hide();
  };

  $scope.closeOpen = function() {
    $scope.openTimes.hide();
  };

  $scope.about = function() {
    $scope.modal.show();
  };
  $scope.open = function() {
    $scope.openTimes.show();
  };

})

.controller('RecycleCtrl', function($scope, $stateParams, recycleService) {
  $scope.MIT = 'The MIT License (MIT)' +
               'Copyright (c)' +
               '' +
               'Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:' +
               '' +
               'The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.' +
               '' +
               'THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.';

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
