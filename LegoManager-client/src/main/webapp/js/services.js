angular.module('legoApp.services', []).factory('LegoKit', function($resource) {
  return $resource('http://localhost:8080/pa165/rest/legokits/:id',{id: '@id'}, {
    update: {
      method: 'PUT'
    }
  });
}).factory('LegoPiece', function($resource){
    return $resource('http://localhost:8080/pa165/rest/legopieces/:id')
    
}).factory('Category', function ($resource) {
    return $resource('http://localhost:8080/pa165/rest/categories/:id')

}).factory('LegoSet', function ($resource) {
    return $resource('http://localhost:8080/pa165/rest/legosets/:id')
});