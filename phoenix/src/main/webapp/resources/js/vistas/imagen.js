if( $('#checkImg').prop('checked') ) {
    $('#file_url').css('display', 'block');
}else{
    $('#file_url').css('display', 'none');
}

$(document).ready(function(){
	$("#checkImg").click(function(){
		if($("#checkImg").is(':checked')){
			$('#file_url').css('display', 'block');
		}else{
			$('#file_url').css('display', 'none');
		}
	});
});

function mostrarImagen(input) {
 if (input.files && input.files[0]) {
  var reader = new FileReader();
  reader.onload = function (e) {
   $('#img_destino').attr('src', e.target.result);
  }
  reader.readAsDataURL(input.files[0]);
 }
}

$("#file_url").change(function(){
 mostrarImagen(this);
});
