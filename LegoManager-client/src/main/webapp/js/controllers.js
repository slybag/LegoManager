angular.module('legoApp.controllers', [])
        .controller('LegoKitListController', function($scope, $state, LegoKit) {
    $scope.legoKits = LegoKit.query(); //fetch all lego kits. Issues a GET to /api/legoKits
})
        .controller('LegoKitCreateController', function($scope, $state, LegoKit, LegoPiece) {
    $scope.legoKit = new LegoKit();
    $scope.legoPieces = LegoPiece.query();
    $scope.legoPieceSelected = [];
    $scope.addLegoKit = function() {
        $scope.legoKit.$save(function() {
            $state.go('legoKits');
        });
    };
    $scope.togglePieceSelection = function togglePieceSelection(piece) {
        console.log(piece);
        console.log($scope.legoPieceSelected.length);
        var idx = $scope.legoPieceSelected.indexOf(piece);
        
        // is currently selected
        if (idx > -1) {
            $scope.legoPieceSelected.splice(idx, 1);
        }

        // is newly selected
        else {
            $scope.legoPieceSelected.push(piece);
        }
        console.log($scope.legoPieceSelected.length);
    };
});