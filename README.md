# Spring Boot 3.0 with Spring Security 6.0

<p>
	This repository contains Spring Security 6.0 implementation with Spring boot 3.0 
</p>


<p>
	Prerequisites before starting Spring boot app - 
	1. Open MySQL Workbench and create a schema by name - SumitJWTDB
</p>


<p>
	Hit below url to load ADMIN and USER entries in DB-
	http://localhost:8081/user/populateDB
	   
	   It will insert below kind of data in the DB -

    
		# id, 	   name,		email, 			username, 		password,                                                       roles
		1, 	   Lokesh Mittal,	lokesh@gmail.com, 	lokesh,			$2a$10$VJD5wLO/IU1nDG.Iq/xhQ.g.6KySfH/tZnmUI.BglsRDFcMfk.x7C, 	ROLE_ADMIN
		2, 	   Sumit Mittal,	sumit@gmail.com, 	sumit,		 	$2a$10$dS65viM/oNjCBI5kkEOJvegRau7z6jSfC4cRS.mj.v2Ds9fIB6dRu, 	ROLE_USER
</p>


<p>
	Hit below URL to load all the products - 
	http://localhost:8081/product/list
		
	Only Admin has access of this method.		username=lokesh, password=pwd1
</p>


<p>
	Hit below URL to load product by Id -
	http://localhost:8081/product/1
		
	Only User has access of this method.		username=sumit, password=pwd2
</p>


