<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="es">
  <head>
    <title>Phoenix</title>

    <c:set var="css">../resources/css/</c:set>
	<c:set var="js" >../resources/js/vistas/</c:set>
	<c:set var="jsControl">../resources/js/control/</c:set>
	<c:set var="img">../resources/img/</c:set>

    <meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html" charset="utf-8" />

	<!--  Favicon -->
	<link rel="icon" type="image/png" href="${img}favicon.png" />

	<!--  Fuentes  -->
	<link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">

	<!-- Custom Theme files -->
	<link href="${css}bootstrap.css" rel="stylesheet" type="text/css" media="all" />
	<link href="${css}style.css" rel="stylesheet" type="text/css" media="all" />
	<link href="${css}ken-burns.css" rel="stylesheet" type="text/css" media="all" /> <!-- banner slider -->
	<link href="${css}font-awesome.css" rel="stylesheet"><!-- font-awesome icons -->
	<link href="${css}jPages.css" rel="stylesheet" type="text/css" media="all"><!-- pagination-->

  </head>
  <body>

  	<jsp:include page="headerC.jsp"></jsp:include>

    <div class="container-fluid">
      <!--Sidebar-->
      <div id="MainMenu" class="col-md-3">
        <div class="list-group panel">
          <!--Depto1-->
          <a href="#depto1" class="list-group-item strong lvl1" data-toggle="collapse" data-parent="#MainMenu">Item 1 <i class="fa fa-angle-down"></i></a>
          <div class="collapse" id="depto1">
            <a href="#SubMenu1" class="list-group-item strong lvl2" data-toggle="collapse" data-parent="#SubMenu1">Subitem 1 <i class="fa fa-angle-down"></i></a>
            <div class="collapse list-group-submenu" id="SubMenu1">
              <a href="" class="list-group-item" data-parent="#SubMenu1">Detalle:
                <select class="cmbBox" name="">
                  <option>Todos</option>
                </select>
              </a>
              <a href="" class="list-group-item" data-parent="#SubMenu1">Subitem 2 b</a>
            </div>
            <a href="#SubMenu2" class="list-group-item strong lvl2" data-toggle="collapse" data-parent="#SubMenu2">Subitem 2 <i class="fa fa-angle-down"></i></a>
            <div class="collapse list-group-submenu" id="SubMenu2">
              <a href="" class="list-group-item" data-parent="#SubMenu2">Subitem 1 c</a>
              <a href="" class="list-group-item" data-parent="#SubMenu2">Subitem 2 d</a>
            </div>
          </div>
          <!--/Depto1-->
          <a href="#depto2" class="list-group-item strong lvl1" data-toggle="collapse" data-parent="#MainMenu">Item 2 <i class="fa fa-angle-down"></i></a>
          <div class="collapse" id="depto2">
            <a href="#SubMenu3" class="list-group-item strong lvl2" data-toggle="collapse" data-parent="#SubMenu1">Subitem 1 <i class="fa fa-angle-down"></i></a>
            <div class="collapse list-group-submenu" id="SubMenu3">
              <a href="" class="list-group-item" data-parent="#SubMenu1">Subitem 1 a</a>
              <a href="" class="list-group-item" data-parent="#SubMenu1">Subitem 2 b</a>
            </div>
            <a href="#SubMenu4" class="list-group-item strong lvl2" data-toggle="collapse" data-parent="#SubMenu2">Subitem 2 <i class="fa fa-angle-down"></i></a>
            <div class="collapse list-group-submenu" id="SubMenu4">
              <a href="" class="list-group-item" data-parent="#SubMenu2">Subitem 1 c</a>
              <a href="" class="list-group-item" data-parent="#SubMenu2">Subitem 2 d</a>
            </div>
          </div>
        </div>
      </div>
      <!--/Sidebar-->

      <!--Contenido-->
      <div class="col-md-9">
        <!-- Nº de items por página -->
        <form>
          <label>Poductos por página: </label>
          <select class="cmbBox numItem">
            <option>10</option>
            <option>20</option>
            <option>50</option>
          </select>
        </form>

        <!-- navigation holder -->
        <div class="holder"></div>

        <!-- item container -->
        <section id="itemContainer">
          <!--NOTA: Bloque a clonar-->
          <div class="col-lg-3 col-md-3 col-xs-6 thumb">
              <a class="thumbnail" href="">
                  <img class="img-responsive previaC" src="" alt="imagen del producto">
                  <div class="breve">
                    <span class="desc">-20%</span>
                    <p class="nomProducto">Lorem ipsum dolor sit amet.</p>
                    <span class="precioActual">$1200.00</span>
                    <del class="precioReal">$1500.00</del>
                  </div>
              </a>
          </div>
        </section>
      </div>

      <!--/Contenido-->
    </div>

    <script src="${js}jPages.js"></script>
    <!-- Para desplegar sidebar y paginar-->
      <script type="text/javascript">
        $(document).ready(function(){
          //Lvl1: Departamentos
           $('.menujq > ul > li > a').click(function(event) {
             var comprobar = $(this).next();
             $('.menujq > ul > li > a > span').removeClass('fa fa-angle-right');
             $('.menujq > ul > li > a > span').addClass('fa fa-angle-down');
             if((comprobar.is('ul')) && (comprobar.is(':visible'))) {
                $('.menujq > ul > li > a > span').removeClass('fa fa-angle-down');
                $('.menujq > ul > li > a > span').addClass('fa fa-angle-right');
                comprobar.slideUp('normal');
             }
             if((comprobar.is('ul')) && (!comprobar.is(':visible'))) {
                $('.menujq ul ul:visible').slideUp('normal');
                $('.menujq ul ul li > a > span').removeClass('fa fa-angle-down');
                $('.menujq > ul > li > ul > li > a > span').addClass('fa fa-angle-right');
                comprobar.slideDown('normal');
             }
          });
          //Lvl2: Categorias
           $('.menujq > ul > li > ul > li > a').click(function() {
             var comprobar = $(this).next();
             $('.menujq ul ul li > a > span').removeClass('fa fa-angle-right');
             $('.menujq ul ul li > a > span').addClass('fa fa-angle-down');
             if((comprobar.is('ul ul')) && (comprobar.is(':visible'))) {
                $('.menujq ul ul li > a > span').removeClass('fa fa-angle-down');
                $('.menujq > ul > li > ul > li > a > span').addClass('fa fa-angle-right');
                comprobar.slideUp('normal');
             }
             if((comprobar.is('ul ul')) && (!comprobar.is(':visible'))) {
                $('.menujq ul ul ul:visible').slideUp('normal');
                comprobar.slideDown('normal');
             }
          });

          /* Inicio de plugin jPages*/
          $("div.holder").jPages({
            containerID : "itemContainer",
            perPage : 10
          });

          /* on select change */
          $("select").change(function(){
            /* get new nº of items per page */
            var newPerPage = parseInt( $(this).val() );

            /* destroy jPages and initiate plugin again */
            $("div.holder").jPages("destroy").jPages({
              containerID   : "itemContainer",
              perPage       : newPerPage
            });
          });
        });
      </script>

  	<jsp:include page="footerC.jsp"></jsp:include>


    <!-- js de la vista-->
	<script src="${js}jquery.min.js"></script>
	<script src="${js}index.js"></script>
	<script src="${js}owl.carousel.js"></script>
	<script src="${js}move-top.js"></script>
	<script src="${js}easing.js"></script>
	<script src="${js}bootstrap.min.js"></script>

  </body>
</html>
