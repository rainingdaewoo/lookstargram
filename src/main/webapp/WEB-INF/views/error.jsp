<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<style type="text/css">
.image-box {
    width:400px;
    height:600px;
    overflow:hidden;
    margin:0 auto;
}

.image-thumbnail {
    width:100%;
    height:100%;
    object-fit:cover;
}

.product-title {
    text-align:center;
    display:table;
    border:1px solid #cecece;
    width:280px;
    height:250px;
}

.product-img-div {
    display:table-cell;
    vertical-align:middle;
}

.product-img {
    max-width:300x;
    max-height:400px;
}
</style>	
<title>Insert title here</title>
</head>
<body>
죄송합니다. 서비스중에 다음과 같은 문제가 발생하였습니다.<br>
${msg }


		<div class="card mb-4 shadow-sm text-center" style="width:405px;">
            <div class="image-box">
         	<img class="image-thumbnail" src="/resources/look_img/l10.jpg">
         	</div>
            <div class="card-body">
              <p class="card-text"> This content is a little bit longer.</p>
              
            </div>
         </div>
         
         <div class="product-title">
         	<div class="product-img-div">
         		<img class="product-img" src="/resources/look_img/l10.jpg">
         	</div>
         </div>
         
         
         <div class="image-box">
         	<img class="image-thumbnail" src="/resources/look_img/l10.jpg">
         </div>
         
         
         
         <svg class="bd-placeholder-img card-img-top" width="100%" height="500" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: Thumbnail"><title>Placeholder</title>
            	
         </svg>
            
	<rect width="100%" height="100%" fill="#55595c"></rect>
<!-- 	<img src="/resources/look_img/l10.jpg">
	<img src="/resources/look_img/l10.jpg" style="width:400px; height:400px;"> -->
</body>
</html>