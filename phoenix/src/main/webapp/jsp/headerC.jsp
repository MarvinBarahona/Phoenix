<!-- header -->
<header class="header">
  <!--header-one-->
  <div class="w3ls-header">
    <div class="w3ls-header-right">
      <ul>
        <li class="dropdown head-dpdn">
          <a href="" class="dropdown-toggle dep" data-toggle="dropdown">
            <i class="fa fa-user" aria-hidden="true"></i><span id="txtCuenta">${cuenta}</span>
            <span class="caret"></span>
          </a>
          <ul class="dropdown-menu" id="clientOptions">
            
          </ul>
        </li>
        <li class="dropdown head-dpdn">
          <a href="help.html" class="dropdown-toggle"><i class="fa fa-question-circle" aria-hidden="true"></i> Ayuda</a>
        </li>
      </ul>
    </div>
    <div class="clearfix"> </div>
  </div>
  <!--//header-one-->

  <!-- header-two -->
  <div class="header-two">
    <div class="container">
      <!-- Static navbar -->
      <nav class="navbar navbar-default">
        <div class="header-logo">
          <img src="${imgEmpresa}" alt="Logo 'empresa'" class="logoEmpresa"/>
        </div>
        <div class="container-fluid">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
          </div>
          <div id="navbar" class="navbar-collapse collapse">
          <a href="searchResults.html">
          	<ul class="nav navbar-nav">
              <li class="dropdown nav1">
                Catálogo de productos
              </li>
            </ul>
          </a>            
            <a href="shoppingCart.html">
              <ul class="nav navbar-nav navbar-right">
                <li class="fa fa-shopping-cart fa-2x"></li>
                <li class="preSub">
                  <p id="headerCant">Artículos: ${cantidadArt}</p>
                  <p id="headerTotal">$ ${total}</p>
                </li>
              </ul>
            </a>

          </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
      </nav>
      <div class="clearfix"> </div>
    </div>
  </div>
  <!-- //header-two -->
</header>
<!-- //header -->
