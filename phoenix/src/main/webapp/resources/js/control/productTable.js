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
			
			$('#productos').DataTable({
				data: productos,
				
				//Asignar una función al crear un nuevo row. 
				"createdRow": function(row, data, index){
					productoAgregado(index);		//Se crea la función (si se usa) en los js de cada vista.
 				},
				
				columns: [
				          {
				        	  title: "Producto",
				        	  data: "nombre",
				        	  name: "nombre"
				          },
				          {
				        	  title: "Departamento",
				        	  data: "departamento",
				        	  name: "departamento"
				          },
				          {
				        	 title: "Categoria",
				        	 data: "categoria",
				        	 name: "categoria"
				          },
				          {
				        	  title: "Cantidad",
				        	  data: "cantidad",
				        	  name: "cantidad"
				          },
				          {
				        	  title: "Precio ($)",
				        	  data: "precio",
				        	  name: "precio"
				          },
				          {
				        	  title: "Descuento (%)",
				        	  data: "descuento",
				        	  name: "descuento"
				          },
				          {
				        	  title: "Disponible",
				        	  data: "disponible",
				        	  name: "disponible"
				          },				          
				         ]
			});
			
			$('#productos tbody').on( 'dblclick', 'tr', function () {
				var prod = $('#productos').DataTable().row(this).data();
				var index = $('#productos').DataTable().row(this).index();
				
				//Función al dar doble click a un row, manda la data y el index de row. 
			    productDblClick(prod, index);	//Se crea la función (si se usa) en los js de cada vista.
			});
		}
	});
});