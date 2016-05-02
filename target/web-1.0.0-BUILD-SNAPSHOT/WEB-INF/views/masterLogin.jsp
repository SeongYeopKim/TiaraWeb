<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <html lang="en">
    <head>
    <meta charset="utf-8">
    <title>관리자로그인</title>
    <meta name="description" content="Flat Design Mini Portfolio">
    <meta name="keywords" content="responsive, bootstrap, flat design, flat ui, portfolio">
    <meta name="author" content="Dzyngiri">
    <!-- styles -->
    <link href="/resources/etc/css/bootstrap.css" rel="stylesheet">
    <link href="/resources/etc/css/bootstrap-responsive.css" rel="stylesheet">
    <link href="/resources/etc/css/style.css" rel="stylesheet">
    <link href="/resources/etc/font/css/fontello.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700' rel='stylesheet' type='text/css'>
    </head>
    <body>
    
    <div class="navbar">
      <div class="navbar-inner">
        <div class="container">  <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </a> <a class="brand" href="home.jsp"><img src="/resources/etc/img/user.jpg"/></a>
       
          <!-- Everything you want hidden at 940px or less, place within here -->
          <div class="nav-collapse collapse">
            <!-- .nav, .navbar-search, .navbar-form, etc -->
          </div>
        </div>
      </div>
    </div>
    <!--Profile container-->
    <div class="container profile">
      <div class="span3"> <img src="/resources/etc/img/T2.jpg"> </div>
      <div class="span5">
        <h1>관리자 화면</h1>
        <form action="firstlogin.tiara" method="post">
        <table>
                    <tr>
                        <td>ID</td>
                        <td><input name="id" type="text"/></td>
                    </tr>
                    <tr>
                        <td>PASSWORD</td>
                        <td><input name="pw" type="password"/></td>
                    </tr>   
                </table>
                
            <br>
            <input type="checkbox"/><a>&nbsp;&nbsp;자동로그인</a>
            <br/>
            <input type="submit" value="확인" class="G"/>
            </form>
    </div>
    <!--END: Profile container-->
    <!-- Social Icons -->
  
    <!-- END: Social Icons -->
    <!-- Footer -->
    <div class="footer">
      
    </div>
    <!-- Contact form in Modal -->
    <!-- Modal -->
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
    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="/resources/etc/js/bootstrap.min.js"></script>
    <script>
                $('#myModal').modal('hidden')
        </script>
    </body>
    </html>
