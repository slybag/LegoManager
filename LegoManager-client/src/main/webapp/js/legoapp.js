
angular.module('legoApp', ['ngResource','ui.router','legoApp.services','legoApp.controllers']);

angular.module('legoApp').config(function($stateProvider) {
    $stateProvider.state('legoKits', { // state for showing all kits
        url: '/legoKits',
        templateUrl: 'legoKits.html',
        controller: 'LegoKitListController'
    });
}).run(function($state) {
  $state.go('legoKits'); //make a transition to legoKit state when app starts
});

