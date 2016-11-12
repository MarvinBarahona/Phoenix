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
    <nav id="mainNav" class="navbar navbar-default navbar-fixed-top">
      <div class="container-fluid">
          <div class="navbar-header">
              <a class="navbar-brand page-scroll" href="/"><span class="glyphicon glyphicon-fire">PHOENIX</span></a>
          </div>
      </div>
    </nav>
    <header class="auxiliar">
        <div class="header-content" >
            <div class="header-content-inner">
              <h4 class="tituloE">Iniciar sesión</h4>
            </div>
            <div class="container-fluid col-sm-6 col-sm-offset-3">
                <form class="form-horizontal formularios sing">
                  <span class="error">${msg}</span><!--Si el formato no es correcto-->

                  <div class="form-group">
                    <label class="control-label col-sm-2" for="e-mail">Correo:</label>
                    <input type="email" id="loginEmail"class="form-control" placeholder="ejemplo@dominio.com" value="${correo}" required>
                  </div>

                  <div class="form-group">
                    <label class="control-label col-sm-2" for="contraseña">Contraseña:</label>
                    <input type="password" id="password" class="form-control" placeholder="" required>
                  </div>
                  <button type="button" class="btn btn-default" id="btnLogin">
                    Aceptar
                  </button>
                </form>
                <br/>
                <p><a href="recoverPassword.html">¿Olvidó su contraseña?</a></p>
                <p><a href="singIn.html">¿No tiene una cuenta?</a></p>
            </div>
        </div><!--/.header-content-->
    </header>
    
    <script src="${jsControl}jquery.redirect.js"></script>
    <script src="${jsControl}validate.js"></script>
    <script src="${jsControl}login.js"></script>
  </body>
</html>