<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Proyecto ASR new...</title>
</head>
<body>
<h1>PROYECTO</h1>
<hr />
<p>Opciones sobre la base de datos Cloudant versión 2019:</p>
<ul>
<li><a href="listar">Listar</a></li>
<li><a href="insertar?palabra=hola">Insertar</a></li>

<form action="Controller" method="post">
  <div>
  	Palabra que se va a reproducir<br>
  	<div><input type="text" name="t2speech" /> <br></div>
	<div><input type="submit" value="Submit" /> </div>
  </div>
  
  <% System.out.println("INDEX JSP FINAL DEL FORM"); %>
</form>


</ul>
</body>
</html>