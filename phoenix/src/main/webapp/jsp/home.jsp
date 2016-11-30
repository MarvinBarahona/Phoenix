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

    <!-- banner -->
    	<div class="banner">
    		<div id="kb" class="carousel kb_elastic animate_text kb_wrapper" data-ride="carousel" data-interval="6000" data-pause="hover">
    			<!-- Wrapper-for-Slides -->
          <div class="carousel-inner" role="listbox">
            <div class="item active"><!-- Primero -->
              <img src="${img}nuevo.png" alt="" class="img-responsive previa" />
              <div class="carousel-caption kb_caption kb_caption_center">
                <h3 data-animation="animated flipInX">Ahora a la venta</h3>
                <h4 data-animation="animated flipInX">Smartphones de última generación</h4>
              </div>
            </div>
            <div class="item"> <!-- Segundo -->
              <img src="${img}computadoras.jpg" alt="" class="img-responsive previa" />
              <div class="carousel-caption kb_caption kb_caption_right">
                <h3 data-animation="animated fadeInDown">Un toque de genialidad</h3>
                <h4 data-animation="animated fadeInUp"></h4>
              </div>
            </div>
    				<div class="item"> <!-- Tercero -->
              <img src="${img}moviles.png" alt="" class="img-responsive previa" />
              <div class="carousel-caption kb_caption kb_caption_right">
                <h3 data-animation="animated fadeInDown">Smartphones y tablets</h3>
                <h4 data-animation="animated fadeInUp">Accesibilidad e innovación</h4>
              </div>
            </div>
            <div class="item"><!-- Cuarto -->
              <img src="${img}Accesorios.jpg" alt="" class="img-responsive previa"/>
              <div class="carousel-caption kb_caption kb_caption_left">
                <h3 data-animation="animated fadeInLeft">Accesorios y más</h3>
                <h4 data-animation="animated flipInX">Los mejores complementos</h4>
              </div>
            </div>
          </div>
          <!-- Left-Button -->
          <a class="left carousel-control kb_control_left" href="#kb" role="button" data-slide="prev">
    				<span class="fa fa-angle-left kb_icons" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
          </a>
          <!-- Right-Button -->
          <a class="right carousel-control kb_control_right" href="#kb" role="button" data-slide="next">
            <span class="fa fa-angle-right kb_icons" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
          </a>
        </div>
    	</div>
    	<!-- //banner -->

      <div class="container">
        <div class="col-md-10 col-md-offset-1 destacados">
          <h3>Productos destacados</h3>
          <img src="${img}linea3.png" alt="separador" />
          <div class="col-lg-3 col-md-3 col-xs-6">
              <img class="img-responsive previa imgProducto" id="${idProducto0}" src="${imgProducto0}" alt="Imagen producto">
          </div>
          <div class="col-lg-3 col-md-3 col-xs-6">
              <img class="img-responsive previa imgProducto" id="${idProducto1}" src="${imgProducto1}" alt="Imagen producto">
          </div>
          <div class="col-lg-3 col-md-3 col-xs-6">
              <img class="img-responsive previa imgProducto" id="${idProducto2}" src="${imgProducto2}" alt="Imagen producto">
          </div>
          <div class="col-lg-3 col-md-3 col-xs-6">
              <img class="img-responsive previa imgProducto" id="${idProducto3}" src="${imgProducto3}" alt="Imagen producto">
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
	<script src="${jsControl}home.js" charset="utf-8"></script>
  </body>
</html>
