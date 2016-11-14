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
    <link href="${css}jquery.dataTables.min.css"  rel="stylesheet">
    
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
                <legend>Productos</legend>

                  <!--Bloque 1-->
                  <div class="col-sm-4 col-sm-offset-4">
                    <div class="from-group">
                      <label class="control-label col-sm-3" for="producto">Producto:</label>
                      <div class="col-sm-7">
                        <input type="text" class="form-control" id="producto" disabled>
                      </div>
                    </div><br>

                    <div class="from-group">
                      <label class="control-label col-sm-3" for="producto">Existencias registradas:</label>
                      <div class="col-sm-7">
                        <input type="text" class="form-control" id="producto" disabled>
                      </div>

                    </div><br>

                    <div class="from-group">
                      <label class="control-label col-sm-3" for="Stock">Actualizar stock:</label>
                      <div class="col-sm-4">
                        <input type="number" min="0">
                      </div>
                    </div><br>
                  </div><!--/Bloque 1-->

                  <!--NOTA: Creo haber entendido esto :o-->
                  <div class="form-group">
                    <div class="col-sm-5  col-sm-offset-5">
                      <button type="button" class="btn btn-default"><span class="fa fa-plus"></span></button>
                      <button type="button" class="btn btn-default"><span class="fa fa-minus"></span></button>
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-sm-5  col-sm-offset-5">
                      <button class="btn btn-default">Guardar</button>
                    </div>
                  </div>
                </form>

               <div class="row">
				  <div class="table-responsive col-sm-offset-1 col-sm-10">
				    <table id="productos" class="display">
				    <!-- Fila a clonar!
				      <tr>
				        <td></td>
				        <td></td>
				        <td></td>
				        <td></td>
				        <td></td>
				        <td></td>
				        <td><input type="check" name="disponible" value=""></td>
				      </tr>
				      -->
				    </table>
				  </div>
				</div>

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
  		      <li><a href="productManagement_gi.html" data-toggle="tooltip" data-placement="right" title="Gestión de productos"><i class="fa fa-shopping-bag"></i><span>Gestión de productos</span></a></li>
  	        <li><a href="stocks.html" data-toggle="tooltip" title="Control de existencias"><i class="fa fa-archive"></i><span>Control de existencias</span></a></li>
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
  <script src="${js}jquery.dataTables.min.js"></script>
  <script src="${js}productTable.js"></script>
  </body>

</html>
  