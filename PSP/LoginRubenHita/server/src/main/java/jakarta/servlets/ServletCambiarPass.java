package jakarta.servlets;

import domain.model.Usuario;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlets.listeners.ThymeLeafListener;
import jakarta.ws.rs.core.Context;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;
import servicios.ServiciosLogin;

import java.io.IOException;

@WebServlet(name = "cambiarContraseña", value = {"/cambiarcontraseña"})
public class ServletCambiarPass extends HttpServlet {
    private ServiciosLogin serviciosLogin;

    @Inject
    public ServletCambiarPass(ServiciosLogin serviciosLogin) {
        this.serviciosLogin = serviciosLogin;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TemplateEngine templateEngine = (TemplateEngine) getServletContext().getAttribute(
                ThymeLeafListener.TEMPLATE_ENGINE_ATTR);
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext())
                .buildExchange(request, response);
        WebContext context = new WebContext(webExchange);

        String username = request.getParameter("username");

        getServletContext().setAttribute("user", username);

        templateEngine.process("form", context, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nombreUsuario = getServletContext().getAttribute("user").toString();

        String newPassword = request.getParameter("password");

        Either<String, Usuario> cambiarPass = serviciosLogin.cambiarPassword(nombreUsuario, newPassword);

        if (cambiarPass.isLeft()){
            response.getWriter().println(cambiarPass.getLeft());
        } else {
            response.getWriter().println("Contraseña de "+cambiarPass.get().getUser()+ " cambiada");
        }
    }
}
