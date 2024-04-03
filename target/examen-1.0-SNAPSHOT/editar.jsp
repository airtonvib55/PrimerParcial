<%@page import="com.mycompany.examen.Estudiante"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Estudiante registro = (Estudiante) request.getAttribute("objetoE");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@ include file="datos.jsp" %>
        
    <center>
        
    
        <h1>Registro de Calificaciones</h1>
        
        <form action="MainServlet" method="post">
            <input type="hidden" name="id" value="<%= registro.getId() %>">
            <table>
                
                <tr>
                    <th>Nombre del estudiante:</th>
                    <td><input type="text" name="nombre" value="<%= registro.getNombre()  %>"></td>
                </tr>
                
                <tr>
                    <th>Primer parcial (sobre 30pts)</th>
                    <td><input type="text" name="p1" size="2" value="<%= registro.getP1() %>"></td>
                </tr>
                
                <tr>
                    <th>Segundo parcial (sobre 30pts)</th>
                    <td><input type="text" name="p2" size="2" value="<%= registro.getP2() %>"></td>
                </tr>
                
                <tr>
                    <th>Examen final (sobre 40pts)</th>
                    <td><input type="text" name="ef" size="2" value="<%= registro.getEf() %>"></td>
                </tr>
            </table>
            
            <input type="submit">
        </form>
                
                </center>
    </body>
</html>
