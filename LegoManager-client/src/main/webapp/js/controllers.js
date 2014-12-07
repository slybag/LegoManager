angular.module('legoApp.controllers', []).controller('LegoKitListController', function($scope, $state, LegoKit) {
  $scope.legoKits = LegoKit.query(); //fetch all lego kits. Issues a GET to /api/legoKits
});
 