angular.module('legoApp.services', [])
.factory('LegoKit', function($resource){
  return $resource('http://localhost:8080/pa165/rest/legokits/:id',{id: '@id'}, {
        update: {
            method: 'PUT'
        }
    });
})
.factory('LegoPiece', function($resource){
    return $resource('http://localhost:8080/pa165/rest/legopieces/:id',{id: '@id'},{
        update: {
            method: 'PUT'
        }
    });    
});
//Jestli delame piece a kit jen tak tohle je tu navic

/*
angular.module('legoApp.services')
.factory('Category', function ($resource) {
    return $resource('http://localhost:8080/pa165/rest/categories/:id')

}).factory('LegoSet', function ($resource) {
    return $resource('http://localhost:8080/pa165/rest/legosets/:id')
});
*/