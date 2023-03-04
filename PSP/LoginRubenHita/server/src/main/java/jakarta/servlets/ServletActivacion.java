package jakarta.servlets;

import domain.model.Activacion;
import domain.model.Usuario;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.ws.rs.core.Context;
import servicios.ServiciosLogin;

import java.io.IOException;

@WebServlet(name = "ServletActivacion", value = "/activacion")
public class ServletActivacion extends HttpServlet {

    private final ServiciosLogin serviciosLogin;

    @Inject
    public ServletActivacion(ServiciosLogin serviciosLogin) {
        this.serviciosLogin = serviciosLogin;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codigo = request.getParameter("codigo");
        Either<String, Activacion> activacion = serviciosLogin.activarUsuario(codigo);

        if (activacion.isLeft()){
            response.getWriter().println(activacion.getLeft());
        } else {
            response.getWriter().println("El usuario "+activacion.get().getUser()+" esta activado.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
