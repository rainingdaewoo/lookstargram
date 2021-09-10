<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 글 관리</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<link rel = "stylesheet" href="resources/css/manage.css"/>
</head>
<body>
<!-- Main Content-->
<br><br><br><br><br>
<%@ include file="../inc/header.jsp" %>       
        <div id="container">
        		<br>
        		<div class="board_wrap1">
			         <div class="board_top" style="padding-left: 150px">
				        <div class="board_title">
				            <strong>내 글 관리</strong>
				        </div>
			        
			  			<div class="btnlist">
			  				<button type ="button" onclick="location.href='manageMylook.do'" 
			  					class="btn btn-secondary" id="looksBtn">looks</button>
			  				<button type ="button" onclick="location.href='manageMyboard.do'" 
			  				class="btn btn-light" id="boardBtn">게시판</button>
			  			</div>
			  		</div> <!-- board_top -->	
	  			
		  			<br>
		  			<br>
		  			<br>
		  			<br>
	  			
	  			<div id="iframe" class="horizontal">
	  				<div id="iframecontainer" class="horizontal" style="height: 70%; width: 100%;"> 				
			  			
			  			<!-- Section-->
						<section class="py-5">	
							<div class="container px-4 px-lg-5 mt-5">
			<div
				class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
				<div class="col mb-5">
					<div class="card h-100">
						<!-- Sale badge-->
						<div class="badge bg-dark text-white position-absolute"
							style="top: 0.5rem; right: 0.5rem">Hot</div>
						<!-- lookbook image-->
						<a href="lookbookInfo.do"></a>
						
					</div>
				</div>
				<div class="col mb-5">
					<div class="card h-100">
						<!-- Sale badge-->
						<div class="badge bg-dark text-white position-absolute"
							style="top: 0.5rem; right: 0.5rem">Hot</div>
						<!-- lookbook image-->
						
						
					</div>
				</div>
				<div class="col mb-5">
					<div class="card h-100">
						<!-- Sale badge-->
						<div class="badge bg-dark text-white position-absolute"
							style="top: 0.5rem; right: 0.5rem">Hot</div>
						<!-- lookbook image-->
						
						
					</div>
				</div>
				<div class="col mb-5">
					<div class="card h-100">
						<!-- lookbook image-->
						
					
					</div>
				</div>
				<div class="col mb-5">
					<div class="card h-100">
						<!-- Product image-->
						<img class="card-img-top"
							src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
					</div>
				</div>
				<div class="col mb-5">
					<div class="card h-100">
						<!-- Product image-->
						<img class="card-img-top"
							src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
					</div>
				</div>
				<div class="col mb-5">
					<div class="card h-100">
						<!-- Product image-->
						<img class="card-img-top"
							src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
					
					</div>
				</div>
				<div class="col mb-5">
					<div class="card h-100">
						<!-- Product image-->
						<img class="card-img-top"
							src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
					
					</div>
				</div>
			</div>
		</div>
						</section>
			  					  			 
					</div>		<!-- iframconatiner-->					 
	  				
		  				</div>

	  				</div> <!-- iframe -->
	  		</div> <!-- board_wrap1 -->
	  	</div> 
	  	<%@ include file="../inc/footer.jsp" %>
</body>
</html>