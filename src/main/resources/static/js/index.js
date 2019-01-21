angular.module('indexApp',[]).controller('indexController', function($scope,$http) {
	
	
	consultarServicios();
	historialServicios();
	
	
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
	
	function historialServicios(){
		$http({
			  method: 'GET',
			  url: 'http://localhost:8080/controladorServicio/historialServicios'
		}).then(function successCallback(respuesta) {
			$scope.historialServicios = respuesta.data;
			console.log($scope.historialServicios);
	     }, function errorCallback(error) {
	    	 console.log(error);
		 });
		
	}
	
//	$scope.consultarServicios();
	
	$scope.finalizarServicio = function(id) {
		
		 $http.get('http://localhost:8080/controladorServicio/finalizarServicio'+'/'+id).then(function(respuesta) {

				$scope.servicios = respuesta.data;
				
				alert("servicio finalizado, costo: "+$scope.servicios.valorServicio) ;
				
				consultarServicios();
				historialServicios();
				
				console.log($scope.servicios);
		    });
	}
	
	
	
	
	
});