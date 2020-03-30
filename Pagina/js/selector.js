$(document).ready(function() {
	$('.nav li.dropdown').hover(function(){
	  $(this).addClass('open');
	}, function(){
	  $(this).removeClass('open');
	});
});

$(document).ready(function() {
	$('.dropmenu').hover(function(){
	  $('li.open > a').addClass('background-dropdown');
	}, function(){
	  $('li.open > a').removeClass('background-dropdown');
	});
});
