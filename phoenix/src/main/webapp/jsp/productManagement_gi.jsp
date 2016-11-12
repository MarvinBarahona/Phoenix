<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
    <meta name="author" content="NightHawks">
    
    <title>Phoenix - Gestión</title>

    <c:set var="css">../resources/css/</c:set>
	<c:set var="js" >../resources/js/vistas/</c:set>
	<c:set var="jsControl">../resources/js/control/</c:set>
	<c:set var="img">../resources/img/</c:set>

    <!--  Favicon -->
    <link rel="icon" type="image/png" href="${img}favicon.png" />
    <!--  Fonts  -->
    <link href="https://fonts.googleapis.com/css?family=Allerta+Stencil|Nobile:700i|Noto+Sans" rel="stylesheet">

    <link href="${css}jquery.dataTables.min.css" rel="stylesheet">
    <link href="${css}style.css" rel="stylesheet">
    <link href="${css}sidebar.css" rel="stylesheet">
    <link href="${css}bootstrap.min.css" rel="stylesheet">

    <script src="${js}jquery.min.js"></script>
    <script src="${js}jquery.dataTables.min.js"></script>
    <script src="${js}bootstrap.min.js"></script>
  </head>

  <body>
    <!--  Menú  -->
    <nav class="navbar navbar-inverse">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbarE">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand"><img src="${imagenEmpresa}" alt="${nombreEmpresa}" class="logo"/></a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbarE">
          <ul class="nav navbar-nav">
            <li><a>${nombreEmpresa}</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">

            <li class="dropdown">
    					<a href="#" data-toggle="dropdown" class="dropdown-toggle"><span class="glyphicon glyphicon-user"></span>${nombre}<strong class="caret"></strong></a>
    					<ul class="dropdown-menu">
                <li>
                  <a href="#"><i class="glyphicon glyphicon-cog"></i> Settings</a>
                </li>
                <li class="divider"></li>
                <li><a href="#" id="btnLogout">
                  <i class="glyphicon glyphicon-log-out"></i> Logout</a>
                </li>
    					</ul>
    				</li>
          </ul>
        </div>
      </div>
    </nav>

    <!--  Sidebar -->
    <div class="col-md-3">
      <div class="menujq">
        <ul>
          <li><a href="">Gestión de Productos</a></li>
        </ul>
      </div>
    </div>
    <!--  Contenido principal -->
    <section class="col-md-9">
      <form class="form-horizontal formularios">
        <legend>Productos</legend>
        <div class="from-group">
          <label class="control-label col-sm-3" for="producto">Producto:</label>
          <div class="col-sm-7">
            <input type="text" class="form-control" name="nombre_producto" id="nombre_producto" placeholder="">
          </div>
        </div><br>

        <div class="from-group">
          <label class="control-label col-sm-3" for="puesto">Departamento:</label>
          <div class="col-sm-7">
            <select name="nombre_departamento">
              <option value="">Seleccione..</option>
            </select>
          </div>
        </div><br>

        <div class="from-group">
          <label class="control-label col-sm-3" for="categoria">Categoría:</label>
          <div class="col-sm-7">
            <select name="categoria">
              <option value="">Seleccionar..</option>
            </select>
          </div>
        </div><br>

        <div class="from-group">
          <label class="control-label col-sm-3" for="descripcion">Descripción:</label>
          <div class="col-sm-7">
            <textarea name="descripcion_producto" id="descripcion_producto" rows="3" cols="30"></textarea>
          </div>
        </div><br>

        <div class="from-group">
          <label class="control-label col-sm-3" for="Stock">Stock:</label>
          <div class="col-sm-7">
            <input type="number" class="" name="existencia" id="existencia" min="0">
          </div>
        </div><br>

        <div class="from-group">
          <label class="control-label col-sm-3" for="detalles">Detalles:</label>
          <div class="col-sm-7">
            <input type="checkbox" name="name" value="">Detalle 1 <br>
            <input type="checkbox" name="name" value="">Detalle 2 <br>
          </div>
        </div><br>

        <div class="from-group">
          <label class="control-label col-sm-3" for="imagen">Imagen:</label>
          <div class="col-sm-7">
            <div class="form-group">
              <input type="file" id="file_url" name="imagen" />
              <img alt="Imagen del producto" id="img_destino" src="#"/>
            </div>
          </div>
        </div><br/>

        <div class="form-group">
          <div class="col-sm-offset-2 col-sm-7 botones">
            <button type="button" class="btn btn-default" id="btnGuardar">Guardar</button>
            <button type="button" class="btn btn-default" disabled>Modificar</button>
            <button type="button" class="btn btn-default" disabled>Eliminar</button>
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
            <td></td>
          </tr>
        -->
        </table>
      </div>
    </section>

    <!--  Footer  -->
    <!--  <footer class="container-fluid text-center">
      <p id="copy">'NightHawks 2016 ©'</p>
      <p>Si tiene dudas o comentarios <a href="#"> contáctanos <span class="glyphicon glyphicon-envelope"></span></a></p>
    </footer>-->

      <script src="${js}imagen.js"></script>
      
      <!--DataTable: Permite buscar, ordenar y paginar la tabla-->
      <script type="text/javascript">
        $(document).ready(function() {
            $('#productos').DataTable( {
                columns: [
                    { title: "Producto" },
                    { title: "Departamento" },
                    { title: "Categoria" },
                    { title: "Detalles" },
                    { title: "Stock" }
                ]
            } );
        } );
      </script>
      
      <script src="${jsControl}jquery.redirect.js"></script>
      <script type="text/javascript" src="${jsControl}guardarImagen.js"></script>
      <script type="text/javascript" src="${jsControl}productManagement_gi.js"></script>
      <script type="text/javascript" src="${jsControl}logout.js"></script>
  </body>
</html>