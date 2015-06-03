<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tienda Virtual</title>
<link href="static/css/bootstrap.css" rel='stylesheet' type='text/css' />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="static/js/jquery.min.js"></script>
<!-- Custom Theme files -->
<link href="static/css/style.css" rel='stylesheet' type='text/css' />
<link href="static/css/component.css" rel='stylesheet' type='text/css' />
<!-- Custom Theme files -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!--webfont-->
<link href='http://fonts.googleapis.com/css?family=Roboto:100,200,300,400,500,600,700,800,900' rel='stylesheet' type='text/css'>
<script src="static/js/jquery.easydropdown.js"></script>
<!-- Add fancyBox main JS and CSS files -->
<script src="static/js/jquery.magnific-popup.js" type="text/javascript"></script>
<link href="static/css/magnific-popup.css" rel="stylesheet" type="text/css">
		<script>
			$(document).ready(function() {
				$('.popup-with-zoom-anim').magnificPopup({
					type: 'inline',
					fixedContentPos: false,
					fixedBgPos: true,
					overflowY: 'auto',
					closeBtnInside: true,
					preloader: false,
					midClick: true,
					removalDelay: 300,
					mainClass: 'my-mfp-zoom-in'
			});
				$("#registroButton").click(function(){						
					var login=$("#login").val().trim();
					var clave=$("#clave").val().trim();
					var nombre=$("#nombre").val().trim();
					var apellido=$("#apellido").val().trim();
					var email=$("#email").val().trim();
					
					
					$.ajax({
							url : '../TiendaVirtual/UsuarioController',
							data : {
								"login" : login,
								"clave" : clave,
								"nombre" : nombre,
								"apellido" : apellido,
								"email" : email,
								"tipoConsulta" : "registro"
							},
							type : 'POST',
							datatype : 'json',
							success : function(data) {								
								if (data.usuarioCreado == "1") {
									alert("Welcome!");
									$("#registroButton").fadeOut();
									window.location = "index.jsp";
								}else{
									alert("Login Failed!");
									}
							}
						});	//Fin Ajax				
				}); //Fin LoginButton

		});
		</script>

<body>
<div class="single">
	<div class="container">
		<div class="header-top">
      		 <div class="logo">
				<a href="index.jsp"><img src="static/images/logo.png" alt=""/></a>
			 </div>
		   <div class="header_right">
			 <ul class="social">
				<li><a href=""> <i class="fb"> </i> </a></li>
				<li><a href=""><i class="tw"> </i> </a></li>
				<li><a href=""><i class="utube"> </i> </a></li>
				<li><a href=""><i class="pin"> </i> </a></li>
				<li><a href=""><i class="instagram"> </i> </a></li>
			 </ul>
		    <div class="lang_list">
			  <select tabindex="4" class="dropdown">
				<option value="" class="label" value="">En</option>
				<option value="1">English</option>
				<option value="2">French</option>
				<option value="3">German</option>
			  </select>
   			</div>
			<div class="clearfix"></div>
          </div>
          <div class="clearfix"></div>
		 </div>  
		 <div class="apparel_box">
			<ul class="login">
				<li class="login_text"><a href="login.jsp">Login</a></li>
				<li class="wish"><a href="checkout.jsp">Wish List</a></li>
				<div class='clearfix'></div>
		    </ul>
		    <div class="cart_bg">
			  <ul class="cart">
				<i class="cart_icon"></i><p class="cart_desc">$1459.50<br><span class="yellow">2 items</span></p>
			    <div class='clearfix'></div>
			  </ul>
			  <ul class="product_control_buttons">
				 <li><a href="#"><img src="static/images/close.png" alt=""/></a></li>
				 <li><a href="#">Edit</a></li>
			  </ul>
		      <div class='clearfix'></div>
			 </div>
			 <ul class="quick_access">
				<li class="view_cart"><a href="checkout.jsp">View Cart</a></li>
				<li class="check"><a href="checkout.jsp">Checkout</a></li>
				<div class='clearfix'></div>
		     </ul>
			<div class="search">
			   <input type="text" value="Search" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Search';}">
			   <input type="submit" value="">
			</div>
		  </div>
		</div>
    </div>
    <div class="main">
	   <div class="container">
		  <div class="register">
		  	  <form> 
				 <div class="register-top-grid">
					<h3>PERSONAL INFORMATION</h3>
					 <div>
						<span>First Name<label>*</label></span>
						<input type="text" id ="nombre"> 
					 </div>
					 <div>
						<span>Last Name<label>*</label></span>
						<input type="text" id ="apellido"> 
					 </div>
					 <div>
						 <span>Email Address<label>*</label></span>
						 <input type="text" id ="email"> 
					 </div>
					 <div class="clearfix"> </div>
					   <a class="news-letter" href="#">
						 <label class="checkbox"><input type="checkbox" name="checkbox" checked=""><i> </i>Sign Up for Newsletter</label>
					   </a>
					 </div>
				     <div class="register-bottom-grid">
						    <h3>LOGIN INFORMATION</h3>
						    <div>
								<span>USERNAME<label>*</label></span>
								<input type="text" id ="login">
							 </div>
							 <div>
								<span>Password<label>*</label></span>
								<input type="password" id ="clave">
							 </div>
							 <div>
								<span>Confirm Password<label>*</label></span>
								<input type="password">
							 </div>
					 </div>
				</form>
				<div class="clearfix"> </div>
				<div class="register-but">
				   <form>
				   	   <button type="button" class="btn btn-primary" id="registroButton">SUBMIT</button>
					   <div class="clearfix"> </div>
				   </form>
				</div>
		   </div>
	     </div>
	    </div>
		<div class="container">
		  <div class="brands">
			 <ul class="brand_icons">
				<li><img src='static/images/icon1.jpg' class="img-responsive" alt=""/></li>
				<li><img src='static/images/icon2.jpg' class="img-responsive" alt=""/></li>
				<li><img src='static/images/icon3.jpg' class="img-responsive" alt=""/></li>
				<li><img src='static/images/icon4.jpg' class="img-responsive" alt=""/></li>
				<li><img src='static/images/icon5.jpg' class="img-responsive" alt=""/></li>
				<li><img src='static/images/icon6.jpg' class="img-responsive" alt=""/></li>
				<li class="last"><img src='static/images/icon7.jpg' class="img-responsive" alt=""/></li>
			 </ul>
		   </div>
	    </div>
	    <div class="container">
	      <div class="instagram_top">
	      	<div class="instagram text-center">
				<h3><i class="insta_icon"> </i> Instagram feed:&nbsp;<span class="small">#Surfhouse</span></h3>
			</div>
	        <ul class="instagram_grid">
			  <li><a class="popup-with-zoom-anim" href="#small-dialog1"><img src="static/images/i1.jpg" class="img-responsive"alt=""/></a></li>
			  <li><a class="popup-with-zoom-anim" href="#small-dialog1"><img src="static/images/i2.jpg" class="img-responsive" alt=""/></a></li>
			  <li><a class="popup-with-zoom-anim" href="#small-dialog1"><img src="static/images/i3.jpg" class="img-responsive" alt=""/></a></li>
			  <li><a class="popup-with-zoom-anim" href="#small-dialog1"><img src="static/images/i4.jpg" class="img-responsive" alt=""/></a></li>
			  <li><a class="popup-with-zoom-anim" href="#small-dialog1"><img src="static/images/i5.jpg" class="img-responsive" alt=""/></a></li>
			  <li class="last_instagram"><a class="popup-with-zoom-anim" href="#small-dialog1"><img src="static/images/i6.jpg" class="img-responsive" alt=""/></a></li>
			  <div class="clearfix"></div>
			  <div id="small-dialog1" class="mfp-hide">
				<div class="pop_up">
					<h4>A Sample Photo Stream</h4>
					<img src="static/images/i_zoom.jpg" class="img-responsive" alt=""/>
				</div>
			  </div>
			</ul>
		  </div>
	      <ul class="footer_social">
			<li><a href="#"> <i class="fb"> </i> </a></li>
			<li><a href="#"><i class="tw"> </i> </a></li>
			<li><a href="#"><i class="pin"> </i> </a></li>
			<div class="clearfix"></div>
		   </ul>
	    </div>
        <div class="footer">
			<div class="container">
				<div class="footer-grid">
					<h3>Category</h3>
					<ul class="list1">
					  <li><a href="#">Home</a></li>
					  <li><a href="#">About us</a></li>
					  <li><a href="#">Eshop</a></li>
					  <li><a href="#">Features</a></li>
					  <li><a href="#">New Collections</a></li>
					  <li><a href="#">Blog</a></li>
					  <li><a href="#">Contact</a></li>
				    </ul>
				</div>
				<div class="footer-grid">
					<h3>Our Account</h3>
				    <ul class="list1">
					  <li><a href="#">Your Account</a></li>
					  <li><a href="#">Personal information</a></li>
					  <li><a href="#">Addresses</a></li>
					  <li><a href="#">Discount</a></li>
					  <li><a href="#">Orders history</a></li>
					  <li><a href="#">Addresses</a></li>
					  <li><a href="#">Search Terms</a></li>
				    </ul>
				</div>
				<div class="footer-grid">
					<h3>Our Support</h3>
					<ul class="list1">
					  <li><a href="#">Site Map</a></li>
					  <li><a href="#">Search Terms</a></li>
					  <li><a href="#">Advanced Search</a></li>
					  <li><a href="#">Mobile</a></li>
					  <li><a href="#">Contact Us</a></li>
					  <li><a href="#">Mobile</a></li>
					  <li><a href="#">Addresses</a></li>
				    </ul>
				  </div>
				  <div class="footer-grid">
					<h3>Newsletter</h3>
					<p class="footer_desc">Nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat</p>
					<div class="search_footer">
			          <input type="text" class="text" value="Insert Email" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Insert Email';}">
			          <input type="submit" value="Submit">
			        </div>
			        <img src="static/images/payment.png" class="img-responsive" alt=""/>
				 </div>
				 <div class="footer-grid footer-grid_last">
					<h3>About Us</h3>
					<p class="footer_desc">Diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam,.</p>
                    <p class="f_text">Phone:  &nbsp;&nbsp;&nbsp;00-250-2131</p>
                    <p class="email">Email: &nbsp;&nbsp;&nbsp;<span>info(at)Surfhouse.com</span></p>	
                 </div>
				 <div class="clearfix"> </div>
			</div>
		</div>
        <div class="footer_bottom">
        	<div class="container">
        		<div class="copy">
				   <p>&copy; 2014 Template by <a href="http://w3layouts.com" target="_blank"> w3layouts</a></p>
			    </div>
        	</div>
        </div>
</body>
</html>