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
    <link rel="icon" type="image/png" href="${img}]favicon.png" />
    <!--  Fonts  -->
    <link href="https://fonts.googleapis.com/css?family=Allerta+Stencil|Nobile:700i|Noto+Sans" rel="stylesheet">

    <link href="${css}jquery.dataTables.min.css" rel="stylesheet">
    <link href="${css}style.css" rel="stylesheet">
    <link href="${css}sidebar.css" rel="stylesheet">
    <link href="${css}bootstrap.min.css" rel="stylesheet">

    <script src="${js}jquery.min.js"></script>
    <script src="${js}jquery.dataTables.min.js"></script>
  </head>

  <body>
    <!--Navegador y Header-->
    <nav class="navbar navbar-inverse">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbarE">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand"><img src="${img}no-image.jpg" alt="'Empresa'" class="logo"/></a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbarE">
          <ul class="nav navbar-nav">
            <li><a>'Nombre de la empresa'</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
    			<a href="#" data-toggle="dropdown" class="dropdown-toggle"><span class="glyphicon glyphicon-user"></span>${nombre}<strong class="caret"></strong></a>
    				<ul class="dropdown-menu">
                		<li>
                  			<a href="#"><i class="glyphicon glyphicon-cog"></i> Settings</a>
                		</li>
                		<li class="divider"></li>
                		<li>
                			<a href=""><i class="glyphicon glyphicon-log-out"></i> Logout</a>
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
          <li><a href="">Resumen de ventas</a></li>
          <li><a href="">Gestión de productos</a></li>
        </ul>
      </div>
    </div>

    <!--  Contenido -->
    <section class="col-md-9">
      <form action="guardar_empresaProv" class="form-horizontal formularios">
        <legend>Productos</legend>
        <div class="from-group">
          <label class="control-label col-sm-3" for="producto">Producto:</label>
          <div class="col-sm-7">
            <input type="text" class="form-control" name="nombre_producto" id="nombre_producto" placeholder="">
          </div>
        </div><br>

        <div class="from-group">
          <label class="control-label col-sm-3" for="descripcion">Descripción:</label>
          <div class="col-sm-7">
            <textarea name="descripcion_producto" id="descripcion_producto" rows="5" cols="30"></textarea>
          </div>
        </div><br>

        <div class="from-group">
          <label class="control-label col-sm-3" for="precio">Precio:</label>
          <div class="col-sm-7">
            <input type="number" class="form-control" name="precio" id="precio" placeholder="">
          </div>
        </div><br>

        <div class="from-group">
          <label class="control-label col-sm-3" for="descuento">Descuento:</label>
          <div class="col-sm-7">
            <input type="number" class="form-control" nombre="descuento" id="descuento" placeholder="Valores de 0 a 100" min="0" max="100" step="5">
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

    <!--  Footer  -->
    <!-- <footer class="container-fluid text-center">
      <p id="copy">'NightHawks 2016 ©'</p>
      <p>Si tiene dudas o comentarios <a href="#"> contáctanos <span class="glyphicon glyphicon-envelope"></span></a></p>

    </footer>-->
    
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
      <!-- Para desplegar sidebar -->
        <script type="text/javascript">
          $(document).ready(function(){
            $('.menujq > ul > li:has(ul)').addClass('desplegable');
             $('.menujq > ul > li > a').click(function() {
               var comprobar = $(this).next();
               $('.menujq li').removeClass('active');
               $(this).closest('li').addClass('active');
               if((comprobar.is('ul')) && (comprobar.is(':visible'))) {
                  $(this).closest('li').removeClass('active');
                  comprobar.slideUp('normal');
               }
               if((comprobar.is('ul')) && (!comprobar.is(':visible'))) {
                  $('.menujq ul ul:visible').slideUp('normal');
                  comprobar.slideDown('normal');
               }
            });
            $('.menujq > ul > li > ul > li:has(ul)').addClass('desplegable');
             $('.menujq > ul > li > ul > li > a').click(function() {
               var comprobar = $(this).next();
               $('.menujq ul ul li').removeClass('active');
               $(this).closest('ul ul li').addClass('active');
               if((comprobar.is('ul ul')) && (comprobar.is(':visible'))) {
                  $(this).closest('ul ul li').removeClass('active');
                  comprobar.slideUp('normal');
               }
               if((comprobar.is('ul ul')) && (!comprobar.is(':visible'))) {
                  $('.menujq ul ul ul:visible').slideUp('normal');
                  comprobar.slideDown('normal');
               }
            });
          });
        </script>

  </body>
</html>