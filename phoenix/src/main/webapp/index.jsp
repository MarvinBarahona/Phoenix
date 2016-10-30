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
    
    <c:set var="css">resources/css/</c:set>
	<c:set var="js" >resources/js/vistas/</c:set>
	<c:set var="jsControl">resources/js/control/</c:set>
	<c:set var="img">resources/img/</c:set>
    
    <!--  Favicon -->
    <link rel="icon" type="image/png" href="${img}favicon.png" />
    
    <link href="${css}bootstrap.min.css" rel="stylesheet">
    <link href="${css}magnific-popup.css" rel="stylesheet">
    <link href="${css}creative.min.css" rel="stylesheet">

    <script src="${js}jquery.min.js"></script>
    <script src="${js}bootstrap.min.js"></script>
    
    <!-- Plugin JavaScript -->
    <script src="${js}jquery.easing.min.js"></script>
    <script src="${js}scrollreveal.min.js"></script>
    <script src="${js}jquery.magnific-popup.min.js"></script>

    <!-- Theme JavaScript -->
    <script src="${js}creative.min.js"></script>
    
    <script src="${jsControl}login.js"></script>
</head>

<body id="page-top">

    <nav id="mainNav" class="navbar navbar-default navbar-fixed-top">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span> Menú <i class="fa fa-bars"></i>
                </button>
                <a class="navbar-brand page-scroll" href="#page-top"><span class="glyphicon glyphicon-fire"></span></a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a class="page-scroll op" href="#">Sobre Phoenix</a>
                    </li>
                    <li>
                        <a class="page-scroll op" href="#contact">Contáctanos</a>
                    </li>
                    <li>
                        <a class="page-scroll op btn" id="modal-957470" href="#modal-container-957470" role="button" data-toggle="modal"><span class="glyphicon glyphicon-log-in"></span></a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>

    <header>
        <div class="header-content" >
            <div class="header-content-inner">
              <div class="col-lg-9 col-lg-offset-3 empresas">
                <div class="col-lg-3">
                  <a class="" href="#">
                    <img class="img-responsive base-orange" src="http://placehold.it/200x200" alt="">
                  </a>
                </div>
                <div class="col-lg-3">
                  <a class="" href="#">
                    <img class="img-responsive base-orange" src="http://placehold.it/200x200" alt="">
                  </a>
                </div>
                <div class="col-lg-3 ">
                  <a class="" href="#">
                    <img class="img-responsive base-orange" src="http://placehold.it/200x200" alt="">
                  </a>
                </div>
              </div>
              <h4 class="tituloE">Ingresa a la empresa de tu preferencia</h4>
            </div>
        </div>
        <div class="footer">
          <p>
            A
          </p>
        </div>
    </header>

    <section class="bg-primary" id="phoenix">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 text-center">
                    <h2 class="section-heading">Phoenix</h2>
                    <hr class="light">
                    <p class="text-faded">
                      Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                    </p>
                </div>
            </div>
        </div>
    </section>

    <section id="contact">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 text-center">
                    <h2 class="section-heading">Contáctanos</h2>
                    <hr class="primary">
                    <p>¿Listo para unirte? Dejanos un mensaje y te responderemos tan pronto como sea posible.</p>
                </div>

                <div class="col-lg-4 col-lg-offset-2 text-center">
                  <form class="form-horizontal formularios">
                    <label class="control-label col-sm-2" for="nombre">Nombre:</label>
                    <input type="text" class="form-control" id="nombre" placeholder="">

                    <label class="control-label col-sm-2" for="apellido">Apellido:</label>
                    <input type="text" class="form-control" id="apellido" placeholder="">

                    <div class="form-group">
                      <label class="control-label col-sm-2" for="asunto">Asunto:</label>
                      <select name="puesto">
                        <option value="solicitud">Solicitar cuenta</option>
                        <option value="opinion">Opinión</option>
                        <option value="consulta">Consulta</option>
                      </select>
                    </div>

                    <label class="control-label col-sm-2" for="e-mail">Correo:</label>
                    <input type="email" class="form-control" id="email" placeholder="">

                    <div class="form-group">
                      <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">Enviar</button>
                      </div>
                    </div>
                  </form>
                </div>
            </div>
        </div>
    </section>

    <aside class="bg-dark"></aside>

    <footer>
      <hr class="primary">
      <p>
        Logo <a href="https://www.instagram.com/lbtheonly/" target="_blank">@lbtheonly</a>
      </p>

      <p id="copy">NightHawks 2016 ©</p>
    </footer>

    <!--  Contenedor modal-->
    <div class="modal fade" id="modal-container-957470" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">

							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
								×
							</button>
							<h4 class="modal-title" id="myModalLabel">
								Entrar
							</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal formularios">
                <label class="control-label" for="e-mail">Correo:</label>
                <input type="email" class="form-control" id="email" placeholder="">

                <label class="control-label" for="password">Contraseña:</label>
                <input type="password" name="name" value="" class="form-control">
              </form>
              <p><a href="#">¿Olvidó su contraseña?</a></p>

              <p>¿No tiene una cuenta? <a href="#"> Cree una</a></p>

						</div>

						<div class="modal-footer">							
							<button type="button" class="btn btn-default" data-dismiss="modal" onclick="login()">
								Entrar
							</button>
						</div>
					</div>

				</div>

			</div>

    

</body>
</html>