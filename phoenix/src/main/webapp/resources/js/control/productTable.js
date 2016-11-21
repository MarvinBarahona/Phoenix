$(document).ready(function() {      
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
				        	  data: "nombre"
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
			
			$('#productos tbody').on( 'dblclick', 'tr', function () {
				producto = $('#productos').DataTable().row(this).data();
			    productDblClick(producto);
			});
		}
	});
});