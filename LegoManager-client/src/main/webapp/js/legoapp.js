
angular.module('legoApp', ['ngResource','ui.router','legoApp.services','legoApp.controllers','checklist-model']);

angular.module('legoApp').config(function($stateProvider) {
    $stateProvider.state('legoKits', { // state for showing all kits
        url: '/legoKits',
        templateUrl: 'legoKits.html',
        controller: 'LegoKitListController'
    }).state('newLegoKit', {
        url: '/legoKits/new',
        templateUrl: 'legokit_add.html',
        controller: 'LegoKitCreateController'
    }).state('updateLegoKit',{
        url: '/legoKits/:id/edit',
        templateUrl: 'legokit_edit.html',
        controller: 'LegoKitEditController'
    });
    
}).run(function($state) {
  $state.go('legoKits'); //make a transition to legoKit state when app starts
});

