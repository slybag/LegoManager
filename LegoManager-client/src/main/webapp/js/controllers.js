angular.module('legoApp.controllers', []).controller('LegoKitListController', function($scope, $state, LegoKit) {
    $scope.legoKits = LegoKit.query(); //fetch all lego kits. Issues a GET to /api/legoKits
})
.controller('LegoKitCreateController', function($scope, $state, LegoKit, LegoPiece) {
    $scope.legoKit = new LegoKit();
    LegoPiece.query(function(data){
        $scope.legoPieces = data;
    });
    $scope.selectedIds = [];
    $scope.legoKit.legoPieces = [];
    $scope.addLegoKit = function() {
        for(i=0; i<$scope.selectedIds.length;i++){
            for(j=0; j<$scope.legoPieces.length;j++){
                if($scope.selectedIds[i] === $scope.legoPieces[j].id) $scope.legoKit.legoPieces.push($scope.legoPieces[j]);
            }; 
        };
        $scope.legoKit.$save(function() {
            $state.go('legoKits');
        });
    };
})
.controller('LegoKitReadController',function($scope, $state, $stateParams, LegoKit, LegoPiece){
    LegoPiece.query(function(data){
        $scope.legoPieces = data;
    });

    $scope.legoKit = LegoKit.get({ id: $stateParams.id },function(){ 
        for(i=0; i<$scope.legoKit.legoPieces.length;i++){
            $scope.selectedIds.push($scope.legoKit.legoPieces[i].id);
        };
    });
    $scope.selectedIds = [];
})
.controller('LegoKitEditController',function($scope, $state, $stateParams, LegoKit, LegoPiece){
    LegoPiece.query(function(data){
        $scope.legoPieces = data;
    });

    $scope.legoKit = LegoKit.get({ id: $stateParams.id },function(){ 
        for(i=0; i<$scope.legoKit.legoPieces.length;i++){
            $scope.selectedIds.push($scope.legoKit.legoPieces[i].id);
        };
    });
    $scope.selectedIds = [];
    $scope.updateLegoKit = function(){
        $scope.legoKit.legoPieces = [];
        for(i=0; i<$scope.selectedIds.length;i++){
            for(j=0; j<$scope.legoPieces.length;j++){
                if($scope.selectedIds[i] === $scope.legoPieces[j].id) $scope.legoKit.legoPieces.push($scope.legoPieces[j]);
            }; 
        };
 
        $scope.legoKit.$update(function(){
            $state.go('legoKits');
        });
    };
})
.controller('LegoKitDeleteController',function($scope, $state, $stateParams, LegoKit){
    $scope.legoKit = LegoKit.get({ id: $stateParams.id });
    $scope.deleteLegoKit = function(){
        console.log($scope.legoKit);
        $scope.legoKit.$delete(function(){
            $state.go('legoKits');
        });
    };
})


.controller('LegoPieceListController', function($scope, $state, LegoPiece) {
    $scope.legoPieces = LegoPiece.query(); //fetch all lego kits. Issues a GET to /api/legoKits
})
.controller('LegoPieceCreateController', function($scope, $state, LegoPiece) {
    $scope.legoPiece = new LegoPiece();
    $scope.colors = ['BLACK','WHITE','RED','BLUE','YELLOW','PURPLE','GREEN','VIOLET','GREY'];
    $scope.addLegoPiece = function(){
        $scope.legoPiece.$save(function() {
            $state.go('legoPieces');
        });
    };
})
.controller('LegoPieceReadController',function($scope, $state, $stateParams, LegoPiece){
    $scope.legoPiece = LegoPiece.get({ id: $stateParams.id },function(){ 
        for(i=0; i<$scope.legoPiece.legoKits.length;i++){
            $scope.selectedIds.push($scope.legoPiece.legoKits[i].id);
        };
    });
    $scope.selectedIds = [];
})
.controller('LegoPieceEditController',function($scope, $state, $stateParams, LegoPiece){
    $scope.colors = ['BLACK','WHITE','RED','BLUE','YELLOW','PURPLE','GREEN','VIOLET','GREY'];
    $scope.legoPiece = LegoPiece.get({ id: $stateParams.id });
    $scope.updateLegoPiece = function(){ 
        $scope.legoPiece.$update(function(){
            $state.go('legoPieces');
        });
    };
})
.controller('LegoPieceDeleteController',function($scope, $state, $stateParams, LegoPiece){
    $scope.legoPiece = LegoPiece.get({ id: $stateParams.id });
    $scope.deleteLegoPiece = function(){
        console.log($scope.legoPiece);
        $scope.legoPiece.$delete(function(){
            $state.go('legoPieces');
        });
    };
});

//Jestli delame piece a kit jen tak tohle je tu navic
/*
angular.module('legoApp.controllers')
.controller('LegoSetListController', function ($scope, $state, LegoSet) {
            $scope.legoSets = LegoSet.query(); //fetch all lego sets. Issues a GET to /api/legoSets
})
.controller('CategoryListController', function ($scope, $state, Category) {
    $scope.categories = Category.query(); //fetch all lego categories. Issues a GET to /api/categories
})
.controller('CategoryCreateController', function ($scope, $state, Category, LegoKit, LegoSet) {
    $scope.category = new Category();
    $scope.legoKits = LegoKit.query();
    $scope.legoSets = LegoSet.query();
    $scope.legoKitSelected = [];
    $scope.legoSetSelected = [];
    $scope.addCategory = function () {
        $scope.category.legoKits = $scope.legoKitSelected;
        $scope.category.legoSets = $scope.legoSetSelected;
        $scope.category.$save(function () {
            $state.go('categories');
        });
    };
    $scope.toggleKitSelection = function toggleKitSelection(kit) {
        var idx = $scope.legoKitSelected.indexOf(kit);

        // is currently selected
        if (idx > -1) {
            $scope.legoKitSelected.splice(idx, 1);
        }

        // is newly selected
        else {
            $scope.legoKitSelected.push(kit);
        }
    };
    $scope.toggleSetSelection = function toggleSetSelection(set) {
        var idx = $scope.legoSetSelected.indexOf(set);

        // is currently selected
        if (idx > -1) {
            $scope.legoSetSelected.splice(idx, 1);
        }

        // is newly selected
        else {
            $scope.legoSetSelected.push(set);
        }
    };
});
*/