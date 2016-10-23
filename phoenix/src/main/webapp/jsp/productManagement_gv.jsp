<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Phoenix</title>

    <meta name="description" content="">
    <meta name="author" content="NightHawks">

    <link rel="stylesheet" href="../css/jquery.dataTables.min.css" media="screen" title="no title">
    <link href="../css/style.css" rel="stylesheet">
    <link href="../css/style_management.css" rel="stylesheet">
    <link rel="stylesheet" href="../css/bootstrap.min.css" media="screen" title="no title">

    <script src="../js/jquery.min.js"></script>
    <script src="../js/jquery.dataTables.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
  </head>

  <body>
    <section class="col-md-9">
      <form class="form-horizontal formularios">
        <legend>Productos</legend>
        <div class="from-group">
          <label class="control-label col-sm-3" for="producto">Producto: ${men1}</label>
          <div class="col-sm-7">
            <input type="text" class="form-control" id="producto" placeholder="">
          </div>
        </div><br>

        <div class="from-group">
          <label class="control-label col-sm-3" for="descripcion">Descripción:</label>
          <div class="col-sm-7">
            <textarea name="descripcion" rows="5" cols="30"></textarea>
          </div>
        </div><br>

        <div class="from-group">
          <label class="control-label col-sm-3" for="precio">Precio:</label>
          <div class="col-sm-7">
            <input type="number" class="form-control" id="precio" placeholder="">
          </div>
        </div><br>

        <div class="from-group">
          <label class="control-label col-sm-3" for="descuento">Descuento:</label>
          <div class="col-sm-7">
            <input type="number" class="form-control" id="descuento" placeholder="Valores de 0 a 100" min="0" max="100" step="5">
          </div>
        </div><br>

        <div class="form-group">
          <div class="col-sm-offset-2 col-sm-10 botones">
            <button type="submit" class="btn btn-default">Guardar</button>
            <button type="submit" class="btn btn-default" disabled>Modificar</button>
          </div>
        </div>
      </form>

      <div class="table-responsive">
        <table id="productos" class="display" width="100%">
        <!-- Fila a clonar!
          <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
          </tr>
        -->
        </table>
      </div>
    </section>


      <!--Función que permite buscar, ordenar y paginar la tabla-->
      <script type="text/javascript">
        $(document).ready(function() {
            $('#productos').DataTable( {
                columns: [
                    { title: "Producto" },
                    { title: "Descripción" },
                    { title: "Precio" },
                    { title: "Descuento" }
                ]
            } );
        } );
      </script>
  </body>
</html>