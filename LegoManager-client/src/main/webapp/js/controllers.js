angular.module('legoApp.controllers', []).controller('LegoKitListController', function($scope, $state, LegoKit) {
    $scope.legoKits = LegoKit.query(); //fetch all lego kits. Issues a GET to /api/legoKits
});

angular.module('legoApp.controllers').controller('LegoKitCreateController', function($scope, $state, LegoKit, LegoPiece) {
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
});

angular.module('legoApp.controllers').controller('LegoKitEditController',function($scope, $state, $stateParams, LegoKit, LegoPiece){
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
});
