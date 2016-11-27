$(document).ready(function() {
	$("#owl-demo").owlCarousel({
	  autoPlay: 3000, //Set AutoPlay to 3 seconds
	  items :4,
	  itemsDesktop : [640,5],
	  itemsDesktopSmall : [480,2],
	  navigation : true

	});

  var defaults = {
		containerID: 'toTop', // fading element id
		containerHoverID: 'toTopHover', // fading element hover id
		scrollSpeed: 1200,
		easingType: 'linear'
	};
  $().UItoTop({ easingType: 'easeOutQuart' });
});
