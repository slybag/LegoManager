
angular.module('legoApp', ['ngResource','ui.router','legoApp.services','legoApp.controllers','checklist-model']);

angular.module('legoApp').config(function($stateProvider) {
    $stateProvider.state('index', {
        url: '/'
    }).state('legoKits', {
        url: '/legoKits',
        templateUrl: 'legoKits.html',
        controller: 'LegoKitListController'
    }).state('newLegoKit', {
        url: '/legoKits/new',
        templateUrl: 'legokit_add.html',
        controller: 'LegoKitCreateController'
    }).state('readLegoKit', {
        url: '/legoKits/:id/read',
        templateUrl: 'legokit_read.html',
        controller: 'LegoKitReadController'
    }).state('updateLegoKit',{
        url: '/legoKits/:id/edit',
        templateUrl: 'legokit_edit.html',
        controller: 'LegoKitEditController'
    }).state('deleteLegoKit',{
        url: '/legoKits/:id/delete',
        templateUrl: 'legokit_delete.html',
        controller: 'LegoKitDeleteController'
    }).state('legoPieces', { 
        url: '/legoPieces',
        templateUrl: 'legoPieces.html',
        controller: 'LegoPieceListController'
    }).state('newLegoPiece', {
        url: '/legoPieces/new',
        templateUrl: 'legopiece_add.html',
        controller: 'LegoPieceCreateController'
    }).state('readLegoPiece', {
        url: '/legoPieces/:id/read',
        templateUrl: 'legopiece_read.html',
        controller: 'LegoPieceReadController'
    }).state('updateLegoPiece',{
        url: '/legoPieces/:id/edit',
        templateUrl: 'legopiece_edit.html',
        controller: 'LegoPieceEditController'
    }).state('deleteLegoPiece',{
        url: '/legoPieces/:id/delete',
        templateUrl: 'legopiece_delete.html',
        controller: 'LegoPieceDeleteController'
    });
    //Jestli delame piece a kit jen tak tohle je tu navic
    /*
    $stateProvider.state('legoSets', { // state for showing all sets
        url: '/legoSets',
        templateUrl: 'legoSets.html',
        controller: 'LegoSetListController'
    }).state('categories', { // state for showing all kits
        url: '/categories',
        templateUrl: 'categories.html',
        controller: 'CategoryListController'
    }).state('newCategory', {
        url: '/categories/new',
        templateUrl: 'category_add.html',
        controller: 'CategoryCreateController'
    });
    */
    
}).run(function($state) {
  $state.go('index'); //make a transition to legoKit state when app starts
});

