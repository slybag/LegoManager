angular.module('legoApp.services', [])
.factory('LegoKit', function($resource){
    return $resource('http://localhost\:8080/pa165/rest/legokits/:id',{id: '@id'}, {
          update: {
              method: 'PUT',
            interceptor: {responseError : showAlert}
          },
          query: {
              method: 'GET', isArray:true, 
              interceptor: {responseError : showAlert}
          },
          get: {
               method: 'GET', 
              interceptor: {responseError  : showAlert}         
          },
          save: {
              method: 'POST', 
              interceptor: {responseError : showAlert}  
          },
          remove: {
              method: 'DELETE',
            interceptor: {responseError : showAlert}  
          }
    });
})
.factory('LegoPiece', function($resource){
    return $resource('http://localhost\:8080/pa165/rest/legopieces/:id',{id: '@id'},{
        update: {
              method: 'PUT',
            interceptor: {responseError : showAlert}
          },
          query: {
              method: 'GET', isArray:true, 
              interceptor: {responseError : showAlert}
          },
          get: {
               method: 'GET', 
              interceptor: {responseError : showAlert}         
          },
          save: {
              method: 'POST', 
              interceptor: {responseError : showAlert}  
          },
          remove: {
              method: 'DELETE',
            interceptor: {responseError : showAlert}  
          }
    });    
});

function showAlert(){alert("There was an internal server error, server may be unavailable or recieved invalid request. Please try again or later if that does not help");}
