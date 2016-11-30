<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="es">
  <head>
    <title>Phoenix</title>

    <c:set var="css">../resources/css/</c:set>
	<c:set var="js" >../resources/js/vistas/</c:set>
	<c:set var="jsControl">../resources/js/control/</c:set>
	<c:set var="img">../resources/img/</c:set>

    <meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html" charset="utf-8" />

	<!--  Favicon -->
	<link rel="icon" type="image/png" href="${img}favicon.png" />

	<!--  Fuentes  -->
	<link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">

	<!-- Custom Theme files -->
	<link href="${css}bootstrap.css" rel="stylesheet" type="text/css" media="all" />
	<link href="${css}style.css" rel="stylesheet" type="text/css" media="all" />
	<link href="${css}ken-burns.css" rel="stylesheet" type="text/css" media="all" /> <!-- banner slider -->
	<link href="${css}font-awesome.css" rel="stylesheet"><!-- font-awesome icons -->
	<link href="${css}jPages.css" rel="stylesheet" type="text/css" media="all"><!-- pagination-->

  </head>
  <body>

  	<jsp:include page="headerC.jsp"></jsp:include>
	
	<p id="producto" hidden="hidden">${idProducto}</p>
	
    <div class="container">
      <div class="row product">
        <h3 class="productName text-center" id="txtNombre"></h3>
        <div class="col-md-7">
          <p class="loading"><i class="fa fa-spinner fa-spin fa-3x fa-fw"></i>Cargando...</p>
          <img src="" alt="imagen del producto " class="col-lg-8 col-xs-7 col-xs-offset-3" id="imgProducto"/>
          <button type="button" name="button" class="col-lg-4 col-xs-7 col-xs-offset-3" id="btnAgregar">Agregar al carro de compras</button>
          <button type="button" name="button" class="col-lg-3 col-xs-offset-1" id="btnCancelar">Cancelar</button>
        </div>

        <div class="col-md-5 col-xs-9">
          <div class="datosPrecio">
            <p>Precio: <del id="txtPrecio"></del><span id="txtPrecioD"></span>              
            </p>
          </div>
          <div class="datosProd">
            <label>Detalles</label>
           	<div id="divDetalles">
           	</div>
           	<br>
           	<label>Descripción</label>
           	<div>
           		<p id="txtDesc"></p>
           	</div>
          </div>
        </div>
      </div>
      </div>

  	<jsp:include page="footerC.jsp"></jsp:include>


    <!-- js de la vista-->
	<script src="${js}jquery.min.js"></script>
	<script src="${js}index.js"></script>
	<script src="${js}owl.carousel.js"></script>
	<script src="${js}move-top.js"></script>
	<script src="${js}easing.js"></script>
	<script src="${js}bootstrap.min.js"></script>
	
	<!-- js de control -->
	<script src="${jsControl}jquery.redirect.js"></script>
	<script src="${jsControl}clientPages.js" charset="utf-8"></script>
	<script src="${jsControl}product.js" charset="utf-8"></script>
  </body>
</html>
