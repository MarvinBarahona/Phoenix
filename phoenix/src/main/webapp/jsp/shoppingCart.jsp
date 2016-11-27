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

    <div class="container">
      <div class="table-responsive">
        <!--NOTA: En caso de estar vacio
         <h3 class="text-center">Carro de compras vacio</h3>
         -->
        <table class="table table-hover">
          <thead>
            <tr>
              <th>Producto</th>
              <th>Precio unitario</th>
              <th>Cantidad</th>
              <th>Subtotal</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td></td>
              <td></td>
              <td>
                <input type="number" name="name" value="" min="1" max="">
                <button type="button" name="button"><span class="fa fa-trash" data-toggle="tooltip" title="Eliminar"></span></button>
              </td>
              <td></td>
            </tr>
          </tbody>
        </table>
      </div>

      <div class="row">
        <div class="col-md-6 envio">
          <!--Aqui pondre la info del cliente -->
        </div>
        <div class="col-md-6 consolidado">
          <table class="table">
            <tr>
              <td>Subtotal</td>
              <td>$000.00</td>
            </tr>
            <tr>
              <td>Impuestos</td>
              <td></td>
            </tr>
            <tr>
              <td>Cobro de envio</td>
              <td></td>
            </tr>
            <tr>
              <td>Total</td>
              <td>$0000.00</td>
            </tr>
          </table>

          <form>
            <div class="form-group acciones">
              <a href="home.html"><span class="fa fa-long-arrow-left"></span>Continuar comprando</a>
              <button class="btn btn-default">Realizar compra</button>
              <button class="btn btn-danger">Limpiar carrito</button>
            </div>
          </form>
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

  </body>
</html>
