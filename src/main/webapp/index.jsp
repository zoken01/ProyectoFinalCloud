<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="java.util.List" %>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <title>Funglish</title>

    <!-- load stylesheets -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400">  
    <!-- Google web font "Open Sans" -->
    <link rel="stylesheet" href="font-awesome-4.5.0/css/font-awesome.min.css">                
    <!-- Font Awesome -->
    <link rel="stylesheet" href="css/bootstrap.min.css">                                      
    <!-- Bootstrap style -->
    <link rel="stylesheet" href="css/hero-slider-style.css">                              
    <!-- Hero slider style (https://codyhouse.co/gem/hero-slider/) -->
    <link rel="stylesheet" href="css/magnific-popup.css">                                 
    <!-- Magnific popup style (http://dimsemenov.com/plugins/magnific-popup/) -->
    <link rel="stylesheet" href="css/tooplate-style.css">                                   

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
          <![endif]-->
</head>

    <body>
        
        <!-- Content -->
        <div class="cd-hero">

            <!-- Navigation -->        
            <div class="cd-slider-nav">
                <nav class="navbar">
                    <div class="tm-navbar-bg">
                        
                        <a class="navbar-brand text-uppercase" href="#"><i class="fa fa-gears tm-brand-icon"></i>Funglish</a>

                        <button class="navbar-toggler hidden-lg-up" type="button" data-toggle="collapse" data-target="#tmNavbar">
                            &#9776;
                        </button>
                        <div class="collapse navbar-toggleable-md text-xs-center text-uppercase tm-navbar" id="tmNavbar">
                            <ul class="nav navbar-nav">
                                <li class="nav-item active selected">
                                    <a class="nav-link" href="#0" data-no="1">Page One <span class="sr-only">(current)</span></a>
                                </li>                                
                                
                            </ul>
                        </div>                        
                    </div>

                </nav>
            </div> 

            <ul class="cd-hero-slider">
            


                <!-- Page 1 Gallery One -->
                <li class="selected">                    
                    <div class="cd-full-width">
                        <div class="container-fluid js-tm-page-content" data-page-no="1" data-page-type="gallery">
                            <div class="tm-img-gallery-container">
                                <div class="tm-img-gallery gallery-one">
                                <!-- Gallery One pop up connected with JS code below -->                                    
                                    <div class="tm-img-gallery-info-container">                                    
                                        <h2 class="tm-text-title tm-gallery-title tm-white"><span class="tm-white">Aprende inglés de manera iteractiva y divertida.</span></h2>
									</div>
									
									<div class="tm-img-gallery-info-container">
                                        <form action="URLHandler" method="post">
	                                        <div class="form-group">
											  <label style="font-size: 18px;" stylefor="usr">¡Introduce la URL de la imagen con la que quieres aprender inglés!</label>
											  <input type="text" class="form-control" name="url" placeholder="URL de la imagen">
											</div>
											
											<button style="height:40px; font-size:16px" type="submit" class="btn btn-primary">Buscar</button>
										</form>
										<% if(request.getAttribute("error") != null){
										%>
										<div>
											<span><%=request.getAttribute("error") %></span>
										</div>
										<%}else if(request.getAttribute("url") != null){%>
											<span>¡Haz clic en la imagen para escucharla en inglés!</span>
											<form action="ControllerURL" method="post">
		                                        <div class="tm-img-gallery-info-container">
		                                        	<input type="hidden" name="urlName" value=<%=request.getAttribute("url")%>>
		                                            <figure class="effect-bubba">
		                                                <input type="image" src=<%=request.getAttribute("url")%> alt="Image" class="img-fluid tm-img" name="imagenClick"/>          
		                                            </figure>
		                                        </div>
		                                    </form>
											<%
										}%>
									</div>
										
									<div class="tm-img-gallery-info-container">
										<form action="ListImages" method="post">
											<button style="height:40px; font-size:16px" type="submit" class="btn btn-primary">Descubre lo que se ha buscado anteriormente</button>
										</form>
										
										<% if(request.getAttribute("urls")!=null){
											for(String url:(List<String>)request.getAttribute("urls")){
												System.out.println("url de la imagen" + url);%>
													<div class="grid-item">
														<form action="ControllerURL" method="post">
														<input type="hidden" name="urlName" value=<%=url%>>
				                                        <figure class="effect-bubba">
				                                            <input type="image" src=<%=url %> alt="Image" class="img-fluid tm-img"/>
				                                        </figure>
				                                        </form>
				                                    </div>
												<%
											}
										}%>
									</div>	
										
									<div class="tm-img-gallery-info-container">
									
									<%
                                   	if (request.getAttribute("mp3streamURL") != null) {
                                       %>
                                       <div>
                                           <audio controls autoplay>
                                             <source src="mp3/temp.mp3" id="mp3" type="audio/mp3">
                                           </audio>
                                       </div>
                                       <%
                                         }
                                     %>
										
										
                                        <h2 style="display:flex" class="tm-text-title tm-gallery-title"><span class="tm-white">
                                        Además, puedes aprender 
                                        inglés de forma sencilla con las imágenes
                                        de prueba mostradas a continuación:
                                        </span></h2>
                                    </div>
                                    
                       				<%
                                   	if (request.getAttribute("mp3stream") != null) {
                                       %>
                                       <div>
                                           <audio controls autoplay>
                                             <source src="mp3/temp.mp3" id="mp3" type="audio/mp3">
                                           </audio>
                                       </div>
                                       <%
                                         }
                                     %>
                       				
                       				<form action="Controller" method="post">
                                        <div class="grid-item">
                                            <input type="hidden" name="textoOculto" value="reloj">
                                            <figure class="effect-bubba">
                                                <input type="image" src="img/reloj.jpg" alt="Image" class="img-fluid tm-img" name="imagenClick"/>          
                                            </figure>
                                        </div>
                                    </form>
                                                                    
                                    <form action="Controller" method="post">
                                        <div class="grid-item">
                                            <input type="hidden" name="textoOculto" value="platano">
                                            <figure class="effect-bubba">
                                                <input type="image" src="img/platano.jpg" alt="Image" class="img-fluid tm-img" name="imagenClick"/>          
                                            </figure>
                                        </div>
                                    </form>
                                    
                                    <form action="Controller" method="post">
                                        <div class="grid-item">
                                            <input type="hidden" name="textoOculto" value="taza">
                                            <figure class="effect-bubba">
                                                <input type="image" src="img/taza.jpg" alt="Image" class="img-fluid tm-img" name="imagenClick"/>          
                                            </figure>
                                        </div>
                                    </form>
                                    
                                    <form action="Controller" method="post">
                                        <div class="grid-item">
                                            <input type="hidden" name="textoOculto" value="bol">
                                            <figure class="effect-bubba">
                                                <input type="image" src="img/bol.jpg" alt="Image" class="img-fluid tm-img" name="imagenClick"/>          
                                            </figure>
                                        </div>
                                    </form>
                                      
                                    <form action="Controller" method="post">
                                        <div class="grid-item">
                                            <input type="hidden" name="textoOculto" value="planta">
                                            <figure class="effect-bubba">
                                                <input type="image" src="img/planta.jpg" alt="Image" class="img-fluid tm-img" name="imagenClick"/>          
                                            </figure>
                                        </div>
                                    </form>
                                    
                                    <form action="Controller" method="post">
                                        <div class="grid-item">
                                            <input type="hidden" name="textoOculto" value="tiburon">
                                            <figure class="effect-bubba">
                                                <input type="image" src="img/tiburon.jpg" alt="Image" class="img-fluid tm-img" name="imagenClick"/>          
                                            </figure>
                                        </div>
                                    </form>
                                    
                                    <form action="Controller" method="post">
                                        <div class="grid-item">
                                            <input type="hidden" name="textoOculto" value="bicho">
                                            <figure class="effect-bubba">
                                                <input type="image" src="img/bicho.jpg" alt="Image" class="img-fluid tm-img" name="imagenClick"/>          
                                            </figure>
                                        </div>
                                    </form>
                                    
                                    <form action="Controller" method="post">
                                        <div class="grid-item">
                                            <input type="hidden" name="textoOculto" value="perro">
                                            <figure class="effect-bubba">
                                                <input type="image" src="img/perro.jpg" alt="Image" class="img-fluid tm-img" name="imagenClick"/>          
                                            </figure>
                                        </div>
                                    </form>
                                       
                                    <form action="Controller" method="post">
                                        <div class="grid-item">
                                            <input type="hidden" name="textoOculto" value="carro">
                                            <figure class="effect-bubba">
                                                <input type="image" src="img/carro.jpg" alt="Image" class="img-fluid tm-img" name="imagenClick"/>          
                                            </figure>
                                        </div>
                                    </form>
                                    
                                    <form action="Controller" method="post">
                                        <div class="grid-item">
                                            <input type="hidden" name="textoOculto" value="llama">
                                            <figure class="effect-bubba">
                                                <input type="image" src="img/llama.jpg" alt="Image" class="img-fluid tm-img" name="imagenClick"/>          
                                            </figure>
                                        </div>
                                    </form>
                                    
                                    <form action="Controller" method="post">
                                        <div class="grid-item">
                                            <input type="hidden" name="textoOculto" value="coche">
                                            <figure class="effect-bubba">
                                                <input type="image" src="img/coche.jpg" alt="Image" class="img-fluid tm-img" name="imagenClick"/>          
                                            </figure>
                                        </div>
                                    </form>
                                    
                                    <form action="Controller" method="post">
                                        <div class="grid-item">
                                            <input type="hidden" name="textoOculto" value="casa">
                                            <figure class="effect-bubba">
                                                <input type="image" src="img/casa.jpg" alt="Image" class="img-fluid tm-img" name="imagenClick"/>          
                                            </figure>
                                        </div>
                                    </form>
                                    
                                    <form action="Controller" method="post">
                                        <div class="grid-item">
                                            <input type="hidden" name="textoOculto" value="paloma">
                                            <figure class="effect-bubba">
                                                <input type="image" src="img/paloma.jpg" alt="Image" class="img-fluid tm-img" name="imagenClick"/>          
                                            </figure>
                                        </div>
                                    </form>
                                    
                                    <form action="Controller" method="post">
                                        <div class="grid-item">
                                            <input type="hidden" name="textoOculto" value="zapatos">
                                            <figure class="effect-bubba">
                                                <input type="image" src="img/zapatos.jpg" alt="Image" class="img-fluid tm-img" name="imagenClick"/>          
                                            </figure>
                                        </div>
                                    </form>
                                    
                                    <form action="Controller" method="post">
                                        <div class="grid-item">
                                            <input type="hidden" name="textoOculto" value="tijeras">
                                            <figure class="effect-bubba">
                                                <input type="image" src="img/tijeras.jpg" alt="Image" class="img-fluid tm-img" name="imagenClick"/>          
                                            </figure>
                                        </div>
                                    </form>
                                    
                                    <form action="Controller" method="post">
                                        <div class="grid-item">
                                            <input type="hidden" name="textoOculto" value="silla">
                                            <figure class="effect-bubba">
                                                <input type="image" src="img/silla.jpg" alt="Image" class="img-fluid tm-img" name="imagenClick"/>          
                                            </figure>
                                        </div>
                                    </form>                                                                      
                                </div>                                 
                            </div>
                        </div>                                                    
                    </div>                    
                </li>
			</ul>
                        
            <footer class="tm-footer">
            
                <div class="tm-social-icons-container text-xs-center">
                    <a class="tm-social-link"><i class="fa fa-facebook"></i></a>
                    <a class="tm-social-link"><i class="fa fa-google-plus"></i></a>
                    <a class="tm-social-link"><i class="fa fa-twitter"></i></a>
                    <a class="tm-social-link"><i class="fa fa-behance"></i></a>
                    <br>
                    <br>
					<form action="Dbop" method="post">
						<button name="op" type="submit" value="empty_db" class="tm-social-link"><i class="fa fa-linkedin"></i></button>
                    </form>
                </div>
                
                <p class="tm-copyright-text">Designed by Victor Guizien &
                Álvaro Barolomé at Silicon Valley.</p>

            </footer>
                    
        </div> <!-- .cd-hero -->
        

        <!-- Preloader, https://ihatetomatoes.net/create-custom-preloading-screen/ -->
        <div id="loader-wrapper">
            
            <div id="loader"></div>
            <div class="loader-section section-left"></div>
            <div class="loader-section section-right"></div>

        </div>
        
        <!-- load JS files -->
        <script src="js/jquery-1.11.3.min.js"></script>         <!-- jQuery (https://jquery.com/download/) -->
        <script src="https://www.atlasestateagents.co.uk/javascript/tether.min.js"></script> <!-- Tether for Bootstrap (http://stackoverflow.com/questions/34567939/how-to-fix-the-error-error-bootstrap-tooltips-require-tether-http-github-h) --> 
        <script src="js/bootstrap.min.js"></script>             <!-- Bootstrap js (v4-alpha.getbootstrap.com/) -->
        <script src="js/hero-slider-main.js"></script>          <!-- Hero slider (https://codyhouse.co/gem/hero-slider/) -->
        <script src="js/jquery.magnific-popup.min.js"></script> <!-- Magnific popup (http://dimsemenov.com/plugins/magnific-popup/) -->
        <script src="js/play.js"></script>
        
        <script>

            function adjustHeightOfPage(pageNo) {

                var offset = 80;
                var pageContentHeight = 0;

                var pageType = $('div[data-page-no="' + pageNo + '"]').data("page-type");

                if( pageType != undefined && pageType == "gallery") {
                    pageContentHeight = $(".cd-hero-slider li:nth-of-type(" + pageNo + ") .tm-img-gallery-container").height();
                }
                else {
                    pageContentHeight = $(".cd-hero-slider li:nth-of-type(" + pageNo + ") .js-tm-page-content").height() + 20;
                }

                if($(window).width() >= 992) { offset = 120; }
                else if($(window).width() < 480) { offset = 40; }
               
                // Get the page height
                var totalPageHeight = $('.cd-slider-nav').height()
                                        + pageContentHeight + offset
                                        + $('.tm-footer').height();

                // Adjust layout based on page height and window height
                if(totalPageHeight > $(window).height()) 
                {
                    $('.cd-hero-slider').addClass('small-screen');
                    $('.cd-hero-slider li:nth-of-type(' + pageNo + ')').css("min-height", totalPageHeight + "px");
                }
                else 
                {
                    $('.cd-hero-slider').removeClass('small-screen');
                    $('.cd-hero-slider li:nth-of-type(' + pageNo + ')').css("min-height", "100%");
                }
            }

            /*
                Everything is loaded including images.
            */
            $(window).load(function(){

                adjustHeightOfPage(1); // Adjust page height

                /* Gallery One pop up
                -----------------------------------------*/
                $('.gallery-one').magnificPopup({
                    delegate: 'a', // child items selector, by clicking on it popup will open
                    type: 'image',
                    gallery:{enabled:true}                
                });
				
				/* Gallery Two pop up
                -----------------------------------------*/
				$('.gallery-two').magnificPopup({
                    delegate: 'a',
                    type: 'image',
                    gallery:{enabled:true}                
                });

                /* Gallery Three pop up
                -----------------------------------------*/
                $('.gallery-three').magnificPopup({
                    delegate: 'a',
                    type: 'image',
                    gallery:{enabled:true}                
                });

                /* Collapse menu after click 
                -----------------------------------------*/
                $('#tmNavbar a').click(function(){
                    $('#tmNavbar').collapse('hide');

                    adjustHeightOfPage($(this).data("no")); // Adjust page height       
                });

                /* Browser resized 
                -----------------------------------------*/
                $( window ).resize(function() {
                    var currentPageNo = $(".cd-hero-slider li.selected .js-tm-page-content").data("page-no");
                    
                    // wait 3 seconds
                    setTimeout(function() {
                        adjustHeightOfPage( currentPageNo );
                    }, 1000);
                    
                });
        
                // Remove preloader (https://ihatetomatoes.net/create-custom-preloading-screen/)
                $('body').addClass('loaded');
                           
            });

            /* Google map
            ------------------------------------------------*/
            var map = '';
            var center;

            function initialize() {
                var mapOptions = {
                    zoom: 14,
                    center: new google.maps.LatLng(37.769725, -122.462154),
                    scrollwheel: false
                };
            
                map = new google.maps.Map(document.getElementById('google-map'),  mapOptions);

                google.maps.event.addDomListener(map, 'idle', function() {
                  calculateCenter();
                });
            
                google.maps.event.addDomListener(window, 'resize', function() {
                  map.setCenter(center);
                });
            }

            function calculateCenter() {
                center = map.getCenter();
            }

            function loadGoogleMap(){
                var script = document.createElement('script');
                script.type = 'text/javascript';
                script.src = 'https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&' + 'callback=initialize';
                document.body.appendChild(script);
            }
        
            // DOM is ready
            $(function() {                
                loadGoogleMap(); // Google Map
            });

        </script>            

</body>
</html>