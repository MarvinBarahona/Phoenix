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
      <div class="table-responsive" id="contenido">
      </div>

      <div class="row">
        <div class="col-md-6 envio">
          <h4>Información del cliente</h4>
          <p>Nombre: ${nombreCliente}</p>
		  <p ${hidden}>Inicie sesión para poder realizar la compra!</p>
        </div>
        <div class="col-md-6 consolidado">
          <table class="table">
            <tr>
              <td>Subtotal productos</td>
              <td id="tdTotalPedido"> $ ${total}</td>
            </tr>
            <tr>
              <td>Cobro de envio</td>
              <td>$ <span id="totalEnvio">${totalEnvio}</span></td>
            </tr>
            <tr>
              <td>Total</td>
              <td id="tdTotal">$ ${totalConEnvio}</td>
            </tr>
          </table>

          <form>
            <div class="form-group acciones">
              <a href="home.html"><span class="fa fa-long-arrow-left"></span>Continuar comprando</a>
              <button type="button" class="btn btn-default" ${enabled}>Realizar compra</button>
              <button type="button" class="btn btn-danger" id="btnLimpiar">Limpiar carrito</button>
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

	<!-- js de control -->
	<script src="${jsControl}jquery.redirect.js"></script>
	<script src="${jsControl}clientPages.js" charset="utf-8"></script>
	<script src="${jsControl}shoppingCart.js" charset="utf-8"></script>
  </body>
</html>
