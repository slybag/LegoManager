
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
    }).state('legoSets', { // state for showing all sets
        url: '/legoSets',
        templateUrl: 'legoSets.html',
        controller: 'LegoSetListController'
    }).state('legoPieces', { // state for showing all kits
        url: '/legoPieces',
        templateUrl: 'legoPieces.html',
        controller: 'LegoPieceListController'
    }).state('categories', { // state for showing all kits
        url: '/categories',
        templateUrl: 'categories.html',
        controller: 'CategoryListController'
    }).state('newCategory', {
        url: '/categories/new',
        templateUrl: 'category_add.html',
        controller: 'CategoryCreateController'
    });
    
}).run(function($state) {
  $state.go('legoKits'); //make a transition to legoKit state when app starts
});

