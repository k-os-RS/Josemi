var slideI = 1;
function slide () {
  if (slideI < 4) {
    slideI = slideI + 1;
  } else {
    slideI = 1;
  }
}
setInterval(slide, 10000);
showSlides(slideI);

function plusSlide (x) {
  showSlides(slideI += x);
}
function currentSlide (x) {
  showSlides(slideI = x);
}
function showSlides (x) {
  var i;
  var img = document.getElementsByClassName('slider-img');
  var dot = document.getElementsByClassName('dot');

  if (x > img.length) {
    slideI = 1;
  }
  if (x < 1) {
    slideI = img.length;
  }
  for (i = 0; i < img.length; i++) {
    img[i].style.display = "none";
  }
  for (i = 0; i < dot.length; i++) {
    dot[i].className = dot[i].className.replace(" active", "");
  }
  img[slideI - 1].style.display = "block";
  dot[slideI - 1].className += " active";
}
setInterval(showSlides, 10000);
