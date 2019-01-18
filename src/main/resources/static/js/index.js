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
	
//	$scope.consultarServicios();
	
	$scope.finalizarServicio = function(id) {
		
		 $http.get('http://localhost:8080/controladorServicio/finalizarServicio'+'/'+id).then(function(respuesta) {
			 debugger;
				$scope.servicios = respuesta.data;
				
				alert("servicio finalizado");
				
				consultarServicios();
				
				console.log($scope.servicios);
		    });
		
//		$http({
//			  method: 'GET',
//			  url: 'http://localhost:8080/controladorServicio/finalizarServicio'+'/'+id
//		}).then(function (respuesta) {
//			
//			debugger;
//			$scope.servicios = respuesta.data;
//			
//			alert("servicio finalizado");
//			
//			consultarServicios();
//			
//			console.log($scope.servicios);
//			    // this callback will be called asynchronously
//			    // when the response is available
//	     }, function (error) {
//	    	 debugger;
//	    	 alert("error");
//	    	 console.log(error);
//		    // called asynchronously if an error occurs
//		    // or server returns response with an error status.
//		 });
		
		
	}
	
	
	
	
	
});