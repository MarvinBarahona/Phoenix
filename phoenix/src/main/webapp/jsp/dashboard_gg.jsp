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
    <script src="${js}Chart.min.js"></script>
    
  </head>
  
  <body >
    <div class="page-container">
     <div class="left-content">
  	   <div class="mother-grid-inner">
          
		<jsp:include page="header.jsp"></jsp:include>

          <!--Contenido-->
          <div class="inner-block">
            <div class="blank">
            
              <p class="loading"><i class="fa fa-spinner fa-spin fa-3x fa-fw"></i>Cargando...</p>
              
              <div class="table-responsive col-sm-offset-1 col-sm-11">
                  <table id="tblPedidos" class="display" width="100%">
                  </table>
              </div>
                          
              <!--Gráficas: main-page-charts-->
              <div class="main-page-charts">
                 <div class="main-page-chart-layer1">
              		<div class="col-md-6 chart-layer1-left">
              			<div class="glocy-chart">
              			<div class="span-2c">
                      <h3 class="tlt">Sales Analytics</h3>
                        <canvas id="bar" height="300" width="400" style="width: 400px; height: 300px;"></canvas>
                        </div>
              			</div>
              		</div>
              		<div class="col-md-6 chart-layer1-right">
                    <div class="prograc-blocks">
              		     <!--Progress bars-->
              	        <div class="home-progres-main">
              	           <h3>Total de ventas</h3>
              	         </div>
                         <!--NOTA: Por departamento, el valor vendrá dado por la
                          relación unidades vendidas vs unidades en inventario-->
              	        <div class='bar_group'>
                		</div>
              	      <!--//Progress bars-->
              	      </div>
              		</div>
              	 <div class="clearfix"> </div>
                </div>
              </div><!--/.main-page-charts-->
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
            <li><a href="productManagement_gv.html" data-toggle="tooltip" data-placement="right" title="Gestión de productos"><i class="fa fa-shopping-bag"></i><span>Gestión de productos</span></a></li>
            <li><a href="dashboard_gv.html" data-toggle="tooltip" data-placement="right" title="Resumen de ventas"><i class="fa fa-clipboard"></i><span>Dashboard</span></a></li>      
  	      </ul>
  	    </div>
  	 </div><!--/.sidebar-menu-->

  	 <div class="clearfix"> </div>
   </div><!--/.page-container-->

  <!--scrolling js-->
  <script src="${js}jquery.nicescroll.js"></script>
  <script src="${js}scripts.js"></script>
  <script src="${js}imagen.js"></script>
  <script src="${js}bootstrap.min.js"></script>
  
  <!-- js de control -->
  <script src="${js}jquery.dataTables.min.js"></script>
  <script src="${jsControl}jquery.redirect.js"></script>
  <script src="${jsControl}logout.js"></script>
  <script src="${jsControl}dashboard.js" charset="utf-8"></script>
  </body>

</html>  