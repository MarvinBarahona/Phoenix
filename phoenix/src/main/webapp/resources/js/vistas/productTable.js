$(document).ready(function() {      
	fillTable();
});

function fillTable(){
	$('#productos').append("Cargando información de los productos...");
	
	$.ajax({
		url: "obtenerProductos.html", 
		type: "POST",
		data:{
			
		},
		success: function(resp){
			var productos = JSON.parse(resp);
			$('#productos').html('');
			$('#productos').DataTable( {
				data: productos, 
				columns: [
				          {
				        	  title: "Producto",
				        	  data: "producto"
				          },
				          {
				        	  title: "Departamento",
				        	  data: "departamento"
				          },
				          {
				        	 title: "Categoria",
				        	 data: "categoria"
				          },
				          {
				        	  title: "Cantidad",
				        	  data: "cantidad"
				          },
				          {
				        	  title: "Precio",
				        	  data: "precio"  
				          },
				          {
				        	  title: "Descuento",
				        	  data: "descuento"
				          },
				          {
				        	  title: "Disponible",
				        	  data: "disponible"
				          }				          
				         ]
			});
		}
	});
}