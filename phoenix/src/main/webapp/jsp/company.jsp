<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
  <head>
    <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="author" content="NightHawks">
	<meta name="description" content="">
    <title>Panel de administraci�n</title>
	
	<c:set var="css">../resources/css/</c:set>
	<c:set var="js" >../resources/js/vistas/</c:set>
	<c:set var="jsControl">../resources/js/control/</c:set>
	<c:set var="img">../resources/img/</c:set>
	
	<!--  Favicon -->
	<link rel="icon" type="image/png" href="${img}favicon.png" />
	
	<!--  Fonts  -->
	<link href="https://fonts.googleapis.com/css?family=PT+Sans" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Rancho&effect=3d-float" rel="stylesheet">    
        
    <!--css-->
    <link href="${css}bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="${css}font-awesome.css" rel="stylesheet">
    <link href="${css}styleG.css" rel="stylesheet" type="text/css"/>
    
    <!--js-->
    <script src="${js}jquery.min.js"></script>
    
  </head>
  <body >
    <div class="page-container">
     <div class="left-content">
  	   <div class="mother-grid-inner">
  	   
         <jsp:include page="header.jsp"></jsp:include>

          <!--Contenido-->
          <div class="inner-block">
            <div class="blank">
              <form class="form-horizontal formularios">
                <!--Bloque 1-->
                <div class="col-sm-5 col-sm-offset-1">
                  <div class="from-group">
                    <label class="control-label col-sm-3" for="nombre">Nombre:</label>
                    <div class="col-sm-7">
                      <input type="text" class="form-control" value="${nombreEmpresa}" id="txtNombre">
                    </div>
                  </div><br>

                  <div class="from-group">
                    <label class="control-label col-sm-3" for="telefono">Tel�fono:</label>
                    <div class="col-sm-7">
                      <input type="tel" class="form-control" value="${telefono}" id="txtTelefono">
                    </div>
                  </div><br>
                  
                  <div class="from-group">
                    <label class="control-label col-sm-3" for="costoEnvio">Costo de envio:</label>
                    <div class="col-sm-7">
                      <input type="number" class="form-control" min="0" step="0.01" name="precio" value="${costoEnvio}" id="txtCostoEnvio">
                    </div>
                  </div><br>

                  <div class="from-group">
                    <label class="control-label col-sm-3" for="direcci�n">Ubicaci�n:</label>
                      <div class="col-sm-7">
                      	<input type="text" class="form-control" placeholder="Pa�s" value="${pais}" id="txtPais"><br>
                        <input type="text" class="form-control" placeholder="Ciudad" value="${ciudad}" id="txtCiudad"><br>
                        <input type="text" class="form-control" placeholder="Direcci�n" value="${direccion}" id="txtDir"><br>
                        <input type="text" class="form-control" placeholder="ZIP" value="${zip}" id="txtZip"><br>
                      </div>
                  </div><br>
                </div><!--/Bloque 1-->

                <!--Bloque 2-->
                <div class="col-sm-4">
                  <div class="from-group">
                    <label class="control-label col-sm-3" for="imagen">Logo:</label>
                    <div class="col-sm-7">
                      <div class="form-group">
                        <input type="checkbox" value="" id="checkImg">Modificar logo<br>
                        <input type="file" id="file_url" name="imagen" />
                        <img alt="Logo de la empresa" id="img_destino" src="${imagenEmpresa}"/>
                      </div>
                    </div>
                  </div><br>
                </div><!--/Bloque 2-->
                
                <div class="from-group">
                  <div class="col-sm-offset-4 col-sm-5 botones">
                  <span class="error" id="errorMsg"></span>
                    <button type="button" class="btn btn-default" id="btnGuardar">Guardar</button>
                  </div>
                </div>
                
              </form>
            </div>
          </div><!--/contenido-->

          <jsp:include page="footer.jsp"></jsp:include>

        </div><!--/.mother-grid-inner-->
      </div><!--/.left-content-->

      <!--sidebar menu-->
      <div class="sidebar-menu">
  		  <div class="logo">
          <a class="sidebar-icon">
            <span class="fa fa-bars"></span>
          </a>
        </div>
  		<div class="menu">
  		  <ul id="menu" >
            <li><a href="employees.html" data-toggle="tooltip" data-placement="right" title="Control de empleados"><i class="fa fa-users"></i><span>Empleados</span></a></li>
            <li><a href="company.html" data-toggle="tooltip" data-placement="right" title="Datos de la empresa"><i class="fa fa-building"></i><span>Empresa</span></a></li>
  		  	<li><a href="dashboard_gg.html" data-toggle="tooltip" data-placement="right" title="Resumen de ventas"><i class="fa fa-clipboard"></i><span>Dashboard</span></a></li>
  		  </ul>
  	    </div>
  	 </div><!--/.sidebar-menu-->

  	 <div class="clearfix"> </div>
   </div>   <!--/.page-container-->
   
  <!--scrolling js-->
  <script src="${js}jquery.nicescroll.js"></script>
  <script src="${js}scripts.js"></script>
  <script src="${js}imagen.js"></script>
  <script src="${js}bootstrap.min.js"></script>
  <script src="${js}jquery.dataTables.min.js"></script>
  
  <script src="${jsControl}jquery.redirect.js"></script>
  <script src="${jsControl}logout.js"></script>
  <script src="${jsControl}company.js" charset="utf-8"></script>
  <script src="${jsControl}guardarImagen.js" charset="utf-8"></script>
  </body>

</html>