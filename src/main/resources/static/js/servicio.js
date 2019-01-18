angular.module('servicioApp',[]).controller('servicioController', function($scope,$http, $window) {
	
	
	
	$scope.ver = function(id) {
		$scope.celdaunit = id;
	}
	
	$scope.crearServicio = function() {
		
		
         if(!$scope.placa){
		    	alert("Ingresar el campo placa");
		 }
         
         if(!$scope.cilindraje){
        	 alert("Ingresar el campo cilindraje");
         }
         
         if(!$scope.celdaunit){
        	 alert("seleccionar una celda");
         }
		
		var dto = {};
		dto.placa = $scope.placa;
		dto.cilindraje = $scope.cilindraje;
		dto.celda = $scope.celdaunit;
		
		if($scope.placa && $scope.cilindraje && $scope.celdaunit){
			$http({
				  method: 'POST',
				  url: 'http://localhost:8080/controladorServicio/registrarServicio',
				  dataType: "json",
				  data: dto,
				  headers: {"Content-Type": "application/json"}
			}).then(function successCallback(respuesta) {
				
//				$scope.mensaje = respuesta.data;
//				console.log($scope.celdas);
				debugger;
				alert("Registro creado exitosamente");
				
				$window.location.href = '/';
				
		     }, function errorCallback(mensaje) {
		    	 debugger;
		    	 console.log(mensaje);
		    	 alert("error");
			 });
		}
	}
	
	
	$scope.consultarCeldasSelect = function () {
		console.log($scope.tipo);		
		$http({
			  method: 'GET',
			  url: 'http://localhost:8080/controladorCelda/listaCeldasPorTipo',
			  params: {'tipo': $scope.tipo}
		}).then(function successCallback(respuesta) {
			
			$scope.celdas = respuesta.data;
			
			console.log($scope.celdas);
			
	     }, function errorCallback(error) {
	    	 console.log(error);
		 });
	}
	
	
	
});