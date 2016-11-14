
  $(document).ready(function() {
      $('#productos').DataTable( {
          columns: [
              { title: "Producto" },
              { title: "Departamento" },
              { title: "Categoria" },
              { title: "Cantidad" },
              { title: "Precio" },
              { title: "Descuento"},
              { title: "Disponible"}
          ]
      } );
  } );
