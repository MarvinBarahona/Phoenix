<!--header-->
<div class="header-main">
  <div class="header-left">
    <div class="logo-name">
     <a href="">
      <img id="logo" src="${imagenEmpresa}" alt="Logo ${nombreEmpresa}" style="height:50px;"/>
     </a>
    </div>
    	<p id="headerNombreEmpresa">${nombreEmpresa}</p>
    <div class="clearfix"> </div>
  </div>
  <div class="header-right">
    <div class="profile_details">
      <ul>
        <li class="dropdown profile_details_drop">
          <a href="" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
            <div class="profile_img">
              <div class="user-name">
                <p>${nombreEmpleado}</p>
                <span>${tipoEmpleado}</span>
              </div>
              <i class="fa fa-angle-down lnr"></i>
              <i class="fa fa-angle-up lnr"></i>
              <div class="clearfix"></div>
           </div>
          </a>
          <ul class="dropdown-menu drp-mnu">
           <li> <a href="#" id="btnLogout"><i class="fa fa-sign-out"></i>Salir</a> </li>
          </ul>
        </li>
      </ul>
    </div>
    <div class="clearfix"> </div>
  </div>
  <div class="clearfix"> </div>
</div><!--/header-->