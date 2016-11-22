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
  <body>
    <div class="page-container">
     <div class="left-content">
  	   <div class="mother-grid-inner">
  	   
		<jsp:include page="header.jsp"></jsp:include>          

          <!--Contenido-->
          <div class="inner-block">
            <div class="blank">
              <h3>Agregar usuario</h3>
              <form class="form-horizontal formularios">
                <!--Bloque 1-->
                <div class="col-sm-5 col-sm-offset-2">
                  <div class="from-group">
                    <label class="control-label col-sm-5" for="nombre">Nombre del gerente:</label>
                    <div class="col-sm-7">
                      <input type="text" class="form-control" id="txtNombre">
                    </div>
                  </div><br>
                  
                  <div class="from-group">
                    <label class="control-label col-sm-5" for="nombre">Apellido del gerente:</label>
                    <div class="col-sm-7">
                      <input type="text" class="form-control" id="txtApellido">
                    </div>
                  </div><br>
                  
				  <div class="from-group">
                    <label class="control-label col-sm-5" for="empresa">Nombre de la empresa:</label>
                    <div class="col-sm-7">
                      <input type="text" class="form-control" id="txtNombreEmpresa">
                    </div>
                  </div><br>
                  
                  <div class="from-group">
                    <label class="control-label col-sm-5" for="correo">Correo electrónico:</label>
                    <div class="col-sm-7">
                      <input type="email" class="form-control" id="txtCorreo">
                    </div>
                  </div><br>
                
                <div class="from-group">
                	<div class="col-sm-9 col-sm-offset-3">
                      <span class="error" id="errorMsg"></span>
                    </div>                    
                  </div>                  
                  <br>    
                             
                </div><!--/Bloque 1-->
				
                
                <div class="form-group">
                  <div class="col-sm-8 botones">
                    <button type="button" class="btn btn-default" id="btnGuardar">Guardar</button>
                  </div>
                </div>
                
              </form>
            </div>
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
  		    <li><a href="wm_create.html" data-toggle="tooltip" data-placement="right" title="Crear departamentos, categorias y detalles"><i class="fa fa-file-text"></i><span>Gestionar categorización</span></a></li>
            <li><a href="wm_adduser.html" data-toggle="tooltip" data-placement="right" title="Agregar usuarios"><i class="fa fa-clipboard"></i><span>Agregar empresa</span></a></li> 
          </ul>
  	    </div>
  	 </div><!--/.sidebar-menu-->

  	 <div class="clearfix"> </div>
   </div><!--/.page-container-->
   
  <!--scrolling js-->
  <script src="${js}jquery.nicescroll.js"></script>
  <script src="${js}scripts.js"></script>
  <script src="${js}bootstrap.min.js"></script>
  
  <script src="${jsControl}validate.js"></script>
  <script src="${jsControl}jquery.redirect.js"></script>
  <script src="${jsControl}logout.js"></script>
  <script src="${jsControl}wm_adduser.js" charset="utf-8"></script>
  </body>

</html>