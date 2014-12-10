angular.module('legoApp.controllers', []).controller('LegoKitListController', function($scope, $state, LegoKit) {
    $scope.legoKits = LegoKit.query(); //fetch all lego kits. Issues a GET to /api/legoKits
})//;
//
//angular.module('legoApp.controllers')
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
})//;
//
//angular.module('legoApp.controllers')
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
})//;
//
//angular.module('legoApp.controllers')
.controller('LegoSetListController', function ($scope, $state, LegoSet) {
            $scope.legoSets = LegoSet.query(); //fetch all lego sets. Issues a GET to /api/legoSets
        })
        .controller('LegoPieceListController', function ($scope, $state, LegoPiece) {
            $scope.legoPiece = LegoPiece.query(); //fetch all lego pieces. Issues a GET to /api/legoPieces
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