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

    <title>Phoenix</title>
    
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
  <body>
    <div class="modal-header">

      <h4 class="modal-title" id="myModalLabel">
        Inicia sesión
      </h4>
    </div>
    <div class="modal-body">
      <form class="form-horizontal formularios">
        <span class="error">${msg}</span>
        <input type="email" class="form-control" id="loginEmail" placeholder="Correo electrónico" value = "${correo}"required>
       	<!-- <span class="error">Formato de correo incorrecto</span><!--Si el formato no es correcto-->
        <input type="password" id="password" name="name" value="" class="form-control" placeholder="Contraseña" required>
      </form>
      <p><a href="#">¿Olvidó su contraseña?</a></p>

      <p>¿No tiene una cuenta? <a href="#"> Cree una</a></p>

    </div>

    <div class="modal-footer">

      <button type="button" class="btn btn-default" data-dismiss="modal" onclick=login()>
        Entrar
      </button>
    </div>
    
    <script src="${jsControl}jquery.redirect.js"></script>
    <script src="${jsControl}login.js"></script>
  </body>
</html>