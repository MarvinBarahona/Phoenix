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
                  <li class="active"><a data-toggle="tab" href="#departamentos">Departamentos</a></li>
                  <li><a data-toggle="tab" href="#categorias">Categorías</a></li>
                  <li><a data-toggle="tab" href="#detalles">Detalles</a></li>
                </ul>

                <div class="tab-content">
                  <!--Departamentos-->
                  <div id="departamentos" class="tab-pane fade in active">
                    <h4>Departamentos</h4><br>
                    <div class="row">
                      <!--Formulario-->
                      <div class="col-sm-5">
                        <form class="form-horizontal formularios">

                          <div class="from-group">
                            <label class="control-label col-sm-4">Departamento:</label>
                            <div class="col-sm-7">
                              <input type="text" class="form-control">
                            </div>
                          </div><br>

                          <div class="from-group">
                            <label class="control-label col-sm-4">Descripción:</label>
                            <div class="col-sm-7">
                              <input type="text" class="form-control">
                            </div>
                          </div><br>

                          <div class="form-group">
                            <div class="col-sm-8 botones">
                              <button class="btn btn-default">Guardar</button>
                              <button class="btn btn-default" disabled>Eliminar</button>
                            </div>
                          </div>
                        </form>
                      </div><!--/Formulario-->

                      <!--Tabla-->
                      <div class="col-sm-7">
                        <div class="row">
                          <div class="table-responsive col-sm-offset-1 col-sm-11">
                            <table id="depto" class="display" width="100%">
                            <!-- Fila a clonar!
                              <tr>
                                <td></td>
                                <td></td>
                              </tr>
                            -->
                            </table>
                          </div>
                        </div>
                      </div><!--/Tabla-->
                    </div>
                  </div><!--/Departamentos-->

                  <!--Categorias-->
                  <div id="categorias" class="tab-pane fade">
                    <h4>Categorías</h4><br>
                    <div class="row">
                      <!--Formulario-->
                      <div class="col-sm-5">
                        <form class="form-horizontal formularios">

                          <div class="from-group">
                            <label class="control-label col-sm-4">Departamento:</label>
                            <div class="col-sm-7">
                              <select name="departamento"  class="cmbBox">
                                <option value="">...</option>
                              </select>
                            </div>
                          </div><br>

                          <div class="from-group">
                            <label class="control-label col-sm-4">Categoría:</label>
                            <div class="col-sm-7">
                              <input type="text" class="form-control">
                            </div>
                          </div><br>

                          <div class="from-group">
                            <label class="control-label col-sm-4">Descripción:</label>
                            <div class="col-sm-7">
                              <input type="text" class="form-control">
                            </div>
                          </div><br>

                          <div class="form-group">
                            <div class="col-sm-8 botones">
                              <button class="btn btn-default">Guardar</button>
                              <button class="btn btn-default" disabled>Eliminar</button>
                            </div>
                          </div>
                        </form>
                      </div><!--/Formulario-->

                      <!--Tabla-->
                      <div class="col-sm-7">
                        <div class="row">
                          <div class="table-responsive col-sm-offset-1 col-sm-11">
                            <table id="categoria" class="display" width="100%">
                            <!-- Fila a clonar!
                              <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                              </tr>
                            -->
                            </table>
                          </div>
                        </div>
                      </div><!--/Tabla-->
                    </div>
                  </div><!--/Categorias-->

                  <!--Detalles-->
                  <div id="detalles" class="tab-pane fade">
                    <h4>Detalles</h4><br>
                    <div class="row">
                      <!--Formulario-->
                      <form class="form-horizontal formularios">
                      <div class="col-sm-5">
                        <div class="from-group">
                          <label class="control-label col-sm-4">Departamento:</label>
                          <div class="col-sm-7">
                            <select name="departamento"  class="cmbBox">
                              <option value="">...</option>
                            </select>
                          </div>
                        </div><br>

                        <div class="from-group">
                          <label class="control-label col-sm-4">Categoría:</label>
                          <div class="col-sm-7">
                            <select name="categoria"  class="cmbBox">
                              <option value="">...</option>
                            </select>
                          </div>
                        </div><br>

                        <div class="from-group">
                          <label class="control-label col-sm-4">Detalle:</label>
                          <div class="col-sm-7">
                            <input type="text" class="form-control">
                          </div>
                        </div><br>
                      </div>

                      <div class="col-sm-5">

                        <p class="text-warning col-sm-12 text-center">
                          Digite una especificación por línea.
                        </p><br>
                        <div class="from-group">
                          <label class="control-label col-sm-4">Valores:</label>
                          <div class="col-sm-7">
                            <textarea name="descripcion" rows="3" cols="30"></textarea>
                          </div>
                        </div><br>
                      </div>

                      <div class="form-group">
                        <div class="col-sm-12 botones">
                          <button class="btn btn-default">Guardar</button>
                          <button class="btn btn-default" disabled>Eliminar</button>
                        </div>
                      </div>
                      </form><!--/Formulario-->

                      </div>
                      <!--Tabla-->
                        <div class="row">
                          <div class="table-responsive col-sm-12">
                            <table id="detalle" class="display" width="100%">
                            <!-- Fila a clonar!
                              <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                              </tr>
                            -->
                            </table>
                          </div>
                        </div><!--/Tabla-->

                  </div><!--/Detalles-->
                </div>
              </div>

              <div class="row">
                <div class="table-responsive col-sm-offset-1 col-sm-10">
                  <table id="usuarios" class="display" width="100%">
                  <!-- Fila a clonar!
                    <tr>
                      <td></td>
                      <td></td>
                      <td></td>
                      <td></td>
                      <td></td>
                      <td></td>
                      <td></td>
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
            <li><a href="wm_adduser.html" data-toggle="tooltip" data-placement="right" title="Agregar usuarios"><i class="fa fa-clipboard"></i><span>Agregar usuarios</span></a></li>
  		      <li><a href="wm_create.html" data-toggle="tooltip" title="Crear departamentos, categorias y detalles"><i class="fa fa-file-text"></i><span>Crear elementos</span></a></li>
          </ul>
  	    </div>
  	 </div><!--/.sidebar-menu-->

  	 <div class="clearfix"> </div>
   </div><!--/.page-container-->
  <!--scrolling js-->
  <script src="${js}jquery.nicescroll.js"></script>
	<script src="${js}scripts.js"></script>
  <script src="${js}bootstrap.min.js"></script>
  <script src="${js}jquery.dataTables.min.js"></script>
  
  	<!--Función que permite buscar, ordenar y paginar la tabla-->
 	<script type="text/javascript">
    $(document).ready(function() {
        $('#depto').DataTable( {
            columns: [
                { title: "Departamento" },
                { title: "Descripción" }
            ]
        } );
        $('#categoria').DataTable( {
            columns: [
                { title: "Departamento" },
                { title: "Categoria" },
                { title: "Descripción" }
            ]
        } );
        $('#detalle').DataTable( {
            columns: [
                { title: "Departamento" },
                { title: "Categoria" },
                { title: "Detalle" },
                { title: "Valores" }
            ]
        } );
    } );
  </script>
  </body>

</html>