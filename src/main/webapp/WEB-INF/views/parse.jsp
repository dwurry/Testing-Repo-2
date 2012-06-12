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
<P>  The string to parse is ${parseString}.   </P>
<P>  There are ${wordCount} words in this string. </P>
<P>  Please NOTE there is a question in the spec on the following line:  </P>
<P>  The first character that does not repeat is in position ${FirstNonRepeatingChar}. </P>
<P>  Repeating sequences found: ${sequenceCount}. </P>
  <c:if test = "${not empty sequences}">
    <c:forEach var = "sequence" items="${sequences}">
     <P>        ${sequence}</P>
    </c:forEach>
  </c:if>
</body>
</html>
