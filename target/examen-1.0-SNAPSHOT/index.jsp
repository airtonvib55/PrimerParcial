<%@page import="java.util.ArrayList"%>
<%@page import="com.mycompany.examen.Estudiante"%>
<%
    if(session.getAttribute("listaE") == null){
        ArrayList<Estudiante> listaAux = new ArrayList<Estudiante>();
        
        session.setAttribute("listaE", listaAux);
    }
    ArrayList<Estudiante> lista = (ArrayList<Estudiante>) session.getAttribute("listaE");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        
        <a href="MainServlet?op=nuevo">Nuevo</a>
        
        <table border="1px">
            <tr>
                <th>Id</th>
                <th>Nombre</th>
                <th>P1(30)</th>
                <th>P2(30)</th>
                <th>EF(40)</th>
                <th>Nota</th>
            </tr>
            
            <%
                if(lista != null){
                    for(Estudiante item: lista){
                    int nota = item.getP1()+item.getP2()+item.getEf();
                    
            %>
            
            <tr>
                <td> <%= item.getId() %> </td>
                <td> <%= item.getNombre() %></td>
                <td> <%= item.getP1() %> </td>
                <td> <%= item.getP2() %> </td>
                <td> <%= item.getEf() %> </td>
                
                <td> <%= nota  %> </td>
                
                <td> <a href="MainServlet?op=editar&id=<%= item.getId() %>">Editar</a> </td>
                <td> <a href="MainServlet?op=eliminar&id=<%= item.getId() %>" onclick="return(confirm('Seguro de Eliminar'))">Eliminar</a> </td>
                
                
            </tr>
            
            <%
                    }
                }
            %>
            
        </table>
            </center>
    </body>
</html>
