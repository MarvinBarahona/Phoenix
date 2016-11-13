<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Crear cuenta</title>

    <c:set var="css">../resources/css/</c:set>
	<c:set var="js" >../resources/js/vistas/</c:set>
	<c:set var="jsControl">../resources/js/control/</c:set>
	<c:set var="img">../resources/img/</c:set>

	<!--  Favicon -->
    <link rel="icon" type="image/png" href="${img}favicon.png" />

    <link href="${css}bootstrap.min.css" rel="stylesheet">
    <link href="${css}magnific-popup.css" rel="stylesheet">
    <link href="${css}creative.min.css" rel="stylesheet">

	<script src="${js}jquery.min.js"></script>
    <script src="${js}bootstrap.min.js"></script>
  </head>
  <nav id="mainNav" class="navbar navbar-default navbar-fixed-top">
      <div class="container-fluid">
          <div class="navbar-header">
              <a class="navbar-brand page-scroll" href="/"><span class="glyphicon glyphicon-fire">PHOENIX</span></a>
          </div>
      </div>
    </nav>
    <header class="auxiliar">
        <div class="header-content" >
            <div class="container-fluid col-sm-6 col-sm-offset-3">
                <form class="form-horizontal formularios sing">
                  <span class="text-info">Confirmación de cuenta: </span>
                  <span class="text-info" id="correo">${correo}</span>
                  <span class="text-info" id="nombre">${nombre}</span>
                  <span class="text-info" id="apellido">${apellido}</span>

                  <input type="password" id="contra1" class="form-control" placeholder="Contraseña..." required>

                  <input type="password" id="contra2" class="form-control" placeholder="Confirmar contraseña..." required>
                  
                 <input type="text" id="pais" class="form-control" placeholder="Pais..." required>
         
                 <input type="text" id="ciudad" class="form-control" placeholder="Ciudad..." required>
               
                 <input type="text" id="direccion" class="form-control" placeholder="Dirección..." required>
               
                 <input type="text" id="zip" class="form-control" placeholder="Código ZIP..." required>
                  
                  <button type="button" class="btn btn-default" id="btnRegistrar">
                    Registrar
                  </button>
                  
                  <span class="error" id="errorMsg"></span>
                  
                </form>
            </div>
        </div><!--/.header-content-->
    </header>
    
    <script src="${jsControl}jquery.redirect.js"></script>
	<script src="${jsControl}login.js"></script>
    <script src="${jsControl}validate.js"></script>
    <script src="${jsControl}confirmAccount.js"></script>
  </body>
</html>