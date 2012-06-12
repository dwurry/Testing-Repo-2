<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Skava Programing Test</title>
</head>
<body>
<h1>
	Please enter a string  
</h1>
<form action="parse" method="get">
  Enter a string to be parsed: <input type="text" name="parseString" /><br />
  <input type="submit" value="Submit" />
</form>
<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
