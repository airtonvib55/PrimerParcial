
package com.mycompany.examen;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MainServlet", urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {

    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        String op = request.getParameter("op");
        int id, posicion;
        Estudiante objEst = new Estudiante();
        
        HttpSession sesion = request.getSession();
        
        ArrayList<Estudiante> lista = (ArrayList<Estudiante>) sesion.getAttribute("listaE");
        
        switch (op){
            
            case "nuevo":
                request.setAttribute("objetoE", objEst);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
                
            case "editar":
                id = Integer.parseInt(request.getParameter("id"));
                posicion = buscarObjeto(request, id);
                objEst = lista.get(posicion);
                
                request.setAttribute("objetoE", objEst);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
                
            case "eliminar":
                id = Integer.parseInt(request.getParameter("id"));
                posicion = buscarObjeto(request, id);
                
                if(posicion >= 0){
                    lista.remove(posicion);
                }
                request.setAttribute("listaE", lista);
                response.sendRedirect("index.jsp");
                break;
                
            
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession sesion = request.getSession();
        ArrayList<Estudiante> lista = (ArrayList<Estudiante>) sesion.getAttribute("listaE");
        
        Estudiante objEst = new Estudiante();
        
        objEst.setId(id);
        objEst.setNombre(request.getParameter("nombre"));
        objEst.setP1(Integer.parseInt(request.getParameter("p1")));
        objEst.setP2(Integer.parseInt(request.getParameter("p2")));
        objEst.setEf(Integer.parseInt(request.getParameter("ef")));
        
        if(id == 0){
            int idNuevo = obtenerId(request);
            objEst.setId(idNuevo);
            lista.add(objEst);
        }else{
            int pos = buscarObjeto(request, id);
            lista.set(pos, objEst);
        }
        
        request.setAttribute("listaE", lista);
        response.sendRedirect(request.getContextPath()+"/index.jsp");
    }
    
    
    public int buscarObjeto(HttpServletRequest request, int id){
        HttpSession sesion = request.getSession();
        
        ArrayList<Estudiante> lista = (ArrayList<Estudiante>) sesion.getAttribute("listaE");
        int posicion = -1;
        
        if(lista != null){
            
            for(Estudiante item:lista){
                posicion++;
                if(item.getId() == id){
                    break;
                }
            }
        }
        return posicion;
        
    }
    
    public int obtenerId(HttpServletRequest request){
        HttpSession sesion = request.getSession();
        
        ArrayList<Estudiante> lista = (ArrayList<Estudiante>) sesion.getAttribute("listaE");
        
        int idNum = 0;
        
        for(Estudiante item: lista){
            idNum = item.getId();
        }
        return idNum + 1;
        
    }

 
}
