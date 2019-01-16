angular.module('indexApp',[]).controller('indexController', function($scope,$http) {
	
	
	consultarServicios();
	
	
	function consultarServicios() {
		$http({
			  method: 'GET',
			  url: 'http://localhost:8080/controladorServicio/serviciosActivos'
		}).then(function successCallback(respuesta) {
			
			$scope.servicios = respuesta.data;
			
			console.log($scope.servicios);
			    // this callback will be called asynchronously
			    // when the response is available
	     }, function errorCallback(error) {
	    	 console.log(error);
		    // called asynchronously if an error occurs
		    // or server returns response with an error status.
		 });
	}
	
	
	
});