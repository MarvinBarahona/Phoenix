$(document).ready(function(){
	$.ajax({
		url: "obtenerDatosVentas.html",
		type: "POST", 
		data: {},
		success: function(resp){
			$('.loading', 'div').remove();		//Quita el loading. 
			
			var r = JSON.parse(resp);
			
			productos = r.productos;
			
			//Ordenar el arreglo por la cantidad vendida.
			productos.sort(function(a, b){
				 return (a.cantVendido == b.cantVendido) ? 0 : (a.cantVendido < b.cantVendido) ? 1 : -1;
			});
			
			//Recortar el objeto a solo los primeros 10. 
			productos = $.grep(productos, function(producto, i){
				return i < 10;
			});
			
			//Crear los datos para la gráfica.
			var label = [];
			var data = [];
			$.each(productos, function(i, producto){
				label.push(producto.nombre);
				data.push(producto.cantVendido);				
			});
			
			//Graficar. 
			var barChartData = {
                labels : label,
                datasets : [{
                        fillColor : "#FC8213",
                        data : data
                },]
            };
            new Chart(document.getElementById("bar").getContext("2d")).Bar(barChartData);
            
            
            //Graficar la venta de productos por departamento. 
            var departamentos = r.departamentos;
            
            $.each(departamentos, function(i, departamento){
            	var barra = $("<div class='bar_group__bar thin' label=" + departamento.nombre + " show_values='true' tooltip='true' value=" + departamento.cantVendido +">");
            	barra.appendTo($('.bar_group'));
            });
            
            $.getScript("../resources/js/vistas/bars.js");
            
            
            //Llenar la tabla con los pedidos. 
            $('#tblPedidos').DataTable({
				data: r.pedidos, 
				columns:
					[{
						name: "cliente",
						title: "Nombre del cliente", 
						data: "cliente"
					},
					{
						name: "cantidad",
						title: "Cantidad de productos",
						data: "cantidad"
					},
					{
						name: "total",
						title: "Total cancelado ($)",
						data: "total"
					}]
			});
		}
	});
});