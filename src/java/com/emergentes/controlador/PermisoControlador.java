package com.emergentes.controlador;

import com.emergentes.dao.PermisoDAO;
import com.emergentes.dao.PermisoDAOimpl;
import com.emergentes.dao.RolDAO;
import com.emergentes.dao.RolDAOimpl;
import com.emergentes.dao.UsuarioDAO;
import com.emergentes.dao.UsuarioDAOimpl;
import com.emergentes.modelo.Permiso;
import com.emergentes.modelo.Rol;
import com.emergentes.modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PermisoControlador", urlPatterns = {"/PermisoControlador"})
public class PermisoControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            PermisoDAO dao = new PermisoDAOimpl();
            UsuarioDAO daoUsuario = new UsuarioDAOimpl();
            RolDAO daoRol = new RolDAOimpl();
            int id;
            
            List<Usuario> lista_usuarios = null;
            List<Rol> lista_roles = null;
            
            Permiso per = new Permiso();
            
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
            System.out.println("Opcion = "+ action);
            switch(action){
                case "add":
                    lista_usuarios = daoUsuario.getAll();
                    lista_roles = daoRol.getAll();
                    request.setAttribute("lista_usuarios", lista_usuarios);
                    request.setAttribute("lista_roles", lista_roles);
                    request.setAttribute("permiso",per);
                    request.getRequestDispatcher("frmpermisos.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    per = dao.getById(id);
                    lista_usuarios = daoUsuario.getAll();
                    lista_roles = daoRol.getAll();
                    request.setAttribute("lista_usuarios", lista_usuarios);
                    request.setAttribute("lista_roles", lista_roles);
                    request.setAttribute("permiso",per);
                    request.getRequestDispatcher("frmpermisos.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect("PermisoControlador");
                    break;
                case "view":
                    List<Permiso> lista = dao.getAll();
                    request.setAttribute("permisos", lista);
                    request.getRequestDispatcher("permisos.jsp").forward(request, response);
                    break;
            }
        }catch(Exception ex){
            System.out.println("Error fatal " + ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
        int id_rol = Integer.parseInt(request.getParameter("id_rol"));
        
        Permiso per = new Permiso();
        
        per.setId(id);
        per.setId_usuario(id_usuario);
        per.setId_rol(id_rol);
        
        if(id == 0){
            // Nuevo
            PermisoDAO dao = new PermisoDAOimpl();
            try {
                dao.insert(per);
                response.sendRedirect("PermisoControlador");
            } catch (Exception ex) {
                Logger.getLogger(PermisoControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            //Editar
            PermisoDAO dao = new PermisoDAOimpl();
            try {
                dao.update(per);
                response.sendRedirect("PermisoControlador");
            } catch (Exception ex) {
                Logger.getLogger(PermisoControlador.class.getName()).log(Level.SEVERE, null, ex);
            } 
    }
    }
}
    
