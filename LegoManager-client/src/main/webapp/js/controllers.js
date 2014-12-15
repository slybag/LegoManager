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
        document.getElementById("errors").innerHTML = '';
        if($scope.legoKit.legoPieces.length !== 0){
            var node = document.createElement("h4");
            var textnode = document.createTextNode("Cannot delete this kit because it is connected to some piece, please edit it and remove this connection");
            node.appendChild(textnode);
            document.getElementById("errors").appendChild(node);
        }else{
            $scope.legoKit.$delete(function(){
                $state.go('legoKits');
            });
        }
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
        document.getElementById("errors").innerHTML = '';
        if($scope.legoPiece.legoKits.length !== 0){
            var node = document.createElement("h4");
            var textnode = document.createTextNode("Cannot delete this piece because it is connected to some kit, please edit it and remove this connection");
            node.appendChild(textnode);
            document.getElementById("errors").appendChild(node);
        }else{
            $scope.legoPiece.$delete(function(){
                $state.go('legoPieces');
            });
        }
    };
});