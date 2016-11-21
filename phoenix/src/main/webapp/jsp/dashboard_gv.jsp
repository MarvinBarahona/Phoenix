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
              <!--Market update-->
              <div class="market-updates">
           			<div class="col-md-4 market-update-gd">
           				<div class="market-update-block clr-block-2">
           				 <div class="col-md-8 market-update-left">
           					<h3>##</h3>
           					<h4>Visitantes</h4>
           				  </div>
           				  <div class="clearfix"> </div>
           				</div>
           			</div>
           			<div class="col-md-4 market-update-gd">
           				<div class="market-update-block clr-block-3">
           					<div class="col-md-8 market-update-left">
           						<h3>##</h3>
           						<h4>Productos vendidos</h4>
           					</div>
           				  <div class="clearfix"> </div>
           				</div>
           			</div>
           		  <div class="clearfix"> </div>
           		</div><!--/.market-update-->
              <!--Gráficas: main-page-charts-->
              <div class="main-page-charts">
                 <div class="main-page-chart-layer1">
              		<div class="col-md-6 chart-layer1-left">
              			<div class="glocy-chart">
              			<div class="span-2c">
                      <h3 class="tlt">Sales Analytics</h3>
                        <canvas id="bar" height="300" width="400" style="width: 400px; height: 300px;"></canvas>
                        <script>
                            var barChartData = {
                            labels : ["Jan","Feb","Mar","Apr","May","Jun","jul"],
                            datasets : [
                                {
                                    fillColor : "#FC8213",
                                    data : [65,59,90,81,56,55,40]
                                },
                                {
                                    fillColor : "#337AB7",
                                    data : [28,48,40,19,96,27,100]
                                }
                            ]

                        };
                            new Chart(document.getElementById("bar").getContext("2d")).Bar(barChartData);

                        </script>
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
                					<div class='bar_group__bar thin' label='Depto' show_values='true' tooltip='true' value='343'></div>
                					<div class='bar_group__bar thin' label='Quality' show_values='true' tooltip='true' value='235'></div>
                					<div class='bar_group__bar thin' label='Amount' show_values='true' tooltip='true' value='550'></div>
                					<div class='bar_group__bar thin' label='Farming' show_values='true' tooltip='true' value='456'></div>
              		      </div>
              				<script src="${js}bars.js"></script>

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
  
  <script src="${jsControl}jquery.redirect.js"></script>
  <script src="${jsControl}logout.js"></script>
  </body>

</html>