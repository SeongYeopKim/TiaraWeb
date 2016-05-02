<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html lang="en">
    <head>
    <meta charset="utf-8">
    <title>WORK | Flat Design Mini Portfolio</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Flat Design Mini Portfolio">
    <meta name="keywords" content="responsive, bootstrap, flat design, flat ui, portfolio">
    <meta name="author" content="Dzyngiri">
    <meta name="description" content="This is a responsive flat design mini portfolio for creative folks who want to showcase their work online.">
    <!-- styles -->
    <link href="/css/bootstrap.css" rel="stylesheet">
    <link href="/css/bootstrap-responsive.css" rel="stylesheet">
    <link href="/css/style.css" rel="stylesheet">
    <link href="/font/css/fontello.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700' rel='stylesheet' type='text/css'>
    <!-- scripts -->
    <!-- Add jQuery library -->
    <script type="text/javascript" src="js/jquery-1.10.1.min.js"></script>
    <!-- Add fancyBox main JS and CSS files -->
    <script type="text/javascript" src="js/jquery.fancybox.js?v=2.1.5"></script>
    <link rel="stylesheet" type="text/css" href="css/jquery.fancybox.css?v=2.1.5" media="screen" />
    <script>
            $(document).ready(function() {
        $(".fancybox-thumb").fancybox({
            helpers	: {
                title	: {
                    type: 'inside'
                },
                overlay : {
                            css : {
                                'background' : 'rgba(1,1,1,0.65)'
                            }
                        }
            }
        });
    });
        </script>
        <script type= "text/javascript" src= "http://code.jquery.com/jquery.js"> </script>
	<script>
		$(document).ready(function(e){
			
			$("#aa").click(function(){
				window.location.href = "Upload.tiara"
			});
			$("#bb").click(function(){
				window.location.href = "ViewImage.tiara"
			});
			ViewImage
			
			
		});
	</script>
    </head>
    <body>
    <div class="navbar">
      <div class="navbar-inner">
        <div class="container"> <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </a> <a class="brand" href="/webapp/WEB-INF/views/notice/home.jsp"><img src="/resources/etc/img/user.jpg"/></a>
          <ul class="nav nav-collapse pull-right">
            <li><a href="index.html"><i class="icon-user"></i> Profile</a></li>
            <li><a href="skills.html"><i class="icon-trophy"></i> Skills</a></li>
            <li><a href="work.html" class="active"><i class="icon-picture"></i> Work</a></li>
            <li><a href="resume.html"><i class="icon-doc-text"></i> Resume</a></li>
          </ul>
          <!-- Everything you want hidden at 940px or less, place within here -->
          <div class="nav-collapse collapse">
            <!-- .nav, .navbar-search, .navbar-form, etc -->
          </div>
        </div>
      </div>
    </div>
    <!-- Works container -->
    <div class="container work">
      <h2>상품소개</h2><button id ="aa">업로드</button>
      <button id ="bb">json</button>
      <ul class="work-images">
        <li>
          <div><a class="fancybox-thumb" rel="fancybox-thumb" href="/resources/etc/img/1.jpg" title="Image 01"><img src="/resources/etc/img/1-thumb.jpg" /></a></div>
        </li>
        <li>
          <div><a class="fancybox-thumb" rel="fancybox-thumb" href="/resources/etc/img/2.jpg"><img src="/resources/etc/img/2-thumb.jpg" /></a></div>
        </li>
        <li>
          <div><a class="fancybox-thumb" rel="fancybox-thumb" href="/resources/etc/img/3.jpg"><img src="/resources/etc/img/3-thumb.jpg" /></a></div>
        </li>
      </ul>
      <ul class="work-images">
        <li>
          <div><a class="fancybox-thumb" rel="fancybox-thumb" href="/resources/etc/img/4.jpg"><img src="/resources/etc/img/4-thumb.jpg" /></a></div>
        </li>
        <li>
          <div><a class="fancybox-thumb" rel="fancybox-thumb" href="/resources/etc/img/5.jpg"><img src="/resources/etc/img/5-thumb.jpg" /></a></div>
        </li>
        <li>
          <div><a class="fancybox-thumb" rel="fancybox-thumb" href="/resources/etc/img/6.jpg"><img src="/resources/etc/img/6-thumb.jpg" /></a></div>
        </li>
      </ul>
      <!--Dummy images by The Fox And King :: http://dribbble.com/snootyfox-->
    </div>
    <!--END: Work container-->
    <!-- Social Icons -->
  
    <!-- END: Social Icons -->
   
    <!-- Contact form in Modal -->
    <div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="myModalLabel"><i class="icon-mail"></i> Contact Me</h3>
      </div>
      <div class="modal-body">
        <form>
          <input type="text" placeholder="Yopur Name">
          <input type="text" placeholder="Your Email">
          <input type="text" placeholder="Website (Optional)">
          <textarea rows="3" style="width:80%"></textarea>
          <br/>
          <button type="submit" class="btn btn-large"><i class="icon-paper-plane"></i> SUBMIT</button>
        </form>
      </div>
    </div>
    <!-- Scripts -->
    <script src="/resources/etc/js/bootstrap.min.js"></script>
    <script>
            $('#myModal').modal('hidden')
    </script>
    </body>
    </html>