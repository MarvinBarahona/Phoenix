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
    <title>Panel de administración</title>
	
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
              <div class="col-md-10">
                <ul class="nav nav-tabs">
                  <li class="active"><a data-toggle="tab" href="#gg">Gerente general</a></li>
                  <li><a data-toggle="tab" href="#gv">Gerente de ventas</a></li>
                  <li><a data-toggle="tab" href="#gi">Gerente de inventario</a></li>
                </ul>

                <div class="tab-content">
                  <!--Gerente general-->
                  <div id="gg" class="tab-pane fade in active">
                    <h4>Gerente general</h4>
                    <form class="form-horizontal formularios">
                      <div class="col-sm-5 col-sm-offset-3">
                        <div class="from-group">
                          <label class="control-label col-sm-3" for="nombre">Nombre:</label>
                          <div class="col-sm-7">
                            <input type="text" class="form-control" value="${nombreGG}" id="txtNombreGG">
                          </div>
                        </div><br>
                        <div class="from-group">
                          <label class="control-label col-sm-3" for="apellido">Apellido:</label>
                          <div class="col-sm-7">
                            <input type="text" class="form-control" value="${apellidoGG}" id="txtApellidoGG">
                          </div>
                        </div><br>

                        <div class="from-group">
                          <label class="control-label col-sm-3" for="correo" >Correo electrónico:</label>
                          <div class="col-sm-7">
                            <input type="email" class="form-control" value="${correoGG}" id="txtCorreoGG" disabled>
                          </div>
                        </div><br>

                        <div class="form-group">
                          <div class="col-sm-10 botones">
                          <span class="error" id="errorMsgGG"></span><br>
                            <button type="button" class="btn btn-default" id="btnGuardarGG">Guardar</button>
                          </div>
                        </div>
                      </div>
                    </form>
                  </div><!--/Gerente general-->

                  <!--Gerente de ventas-->
                  <div id="gv" class="tab-pane fade">
                    <h4>Gerente de ventas</h4>
                    <form class="form-horizontal formularios">
                      <div class="col-sm-5 col-sm-offset-3">
                        <div class="from-group">
                          <label class="control-label col-sm-3" for="nombre">Nombre:</label>
                          <div class="col-sm-7">
                            <input type="text" class="form-control" value="${nombreGV}" id="txtNombreGV">
                          </div>
                        </div><br>
                        <div class="from-group">
                          <label class="control-label col-sm-3" for="apellido">Apellido:</label>
                          <div class="col-sm-7">
                            <input type="text" class="form-control" value="${apellidoGV}" id="txtApellidoGV">
                          </div>
                        </div><br>

                        <div class="from-group">
                          <label class="control-label col-sm-3" for="correo">Correo electrónico:</label>
                          <div class="col-sm-7">
                            <input type="email" class="form-control" value="${correoGV}" id="txtCorreoGV">
                          </div>
                        </div><br>

                        <div class="form-group">
                          <div class="col-sm-10 botones">
                          <span class="error" id="errorMsgGV"></span><br>
                            <button type="button" class="btn btn-default" id="btnGuardarGV">Guardar</button>
                          </div>
                        </div>
                      </div>
                    </form>
                  </div><!--/Gerente de ventas-->

                  <!--Gerente de inventario-->
                  <div id="gi" class="tab-pane fade">
                    <h4>Gerente de inventario</h4>
                    <form class="form-horizontal formularios">
                      <div class="col-sm-5 col-sm-offset-3">
                        <div class="from-group">
                          <label class="control-label col-sm-3" for="nombre">Nombre:</label>
                          <div class="col-sm-7">
                            <input type="text" class="form-control" value="${nombreGI}" id="txtNombreGI">
                          </div>
                        </div><br>
                        <div class="from-group">
                          <label class="control-label col-sm-3" for="apellido">Apellido:</label>
                          <div class="col-sm-7">
                            <input type="text" class="form-control" value="${apellidoGI}" id="txtApellidoGI">
                          </div>
                        </div><br>

                        <div class="from-group">
                          <label class="control-label col-sm-3" for="correo">Correo electrónico:</label>
                          <div class="col-sm-7">
                            <input type="email" class="form-control" value="${correoGI}" id="txtCorreoGI">
                          </div>
                        </div><br>

                        <div class="form-group">
                          <div class="col-sm-10 botones">
                          	<span class="error" id="errorMsgGI"></span><br>
                            <button type="button" class="btn btn-default" id="btnGuardarGI">Guardar</button>
                          </div>
                        </div>
                      </div>
                    </form>
                  </div><!--/Gerente de inventario-->
                </div>
              </div>

            </div><!--/.blank-->
          </div><!--/contenido-->

          <jsp:include page="footer.jsp"></jsp:include>
        
        </div>
      </div>

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
   </div><!--/.page-container-->
   
  <!--scrolling js-->
  <script src="${js}jquery.nicescroll.js"></script>
  <script src="${js}scripts.js"></script>
  <script src="${js}bootstrap.min.js"></script>
  
  <script src="${jsControl}jquery.redirect.js"></script>
  <script src="${jsControl}logout.js"></script>
  <script src="${jsControl}validate.js"></script>
  <script src="${jsControl}employees.js" charset="utf-8"></script>
  </body>
</html>
