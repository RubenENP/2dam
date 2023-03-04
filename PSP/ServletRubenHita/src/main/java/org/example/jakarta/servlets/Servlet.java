package org.example.jakarta.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.jakarta.listeners.ThymeLeafListener;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.Random;

//@Log4j2
@WebServlet(name = "Juego", value = {"/"})
public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TemplateEngine templateEngine = (TemplateEngine) getServletContext().getAttribute(
                ThymeLeafListener.TEMPLATE_ENGINE_ATTR);
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);

        String template;

        if (!req.getParameter("numero").isEmpty()) {
            int numero = Integer.parseInt(req.getParameter("numero"));

            int randomNum;
            int contador = Integer.parseInt(getServletContext().getAttribute("contador").toString());

            if(contador <= 10 && contador > 0){
                contador--;
                getServletContext().setAttribute("contador", contador);
            } else if(contador == 0){
                getServletContext().setAttribute("numero", 0);
                getServletContext().setAttribute("contador", 10);
            }


            if(Integer.parseInt(getServletContext().getAttribute("numero").toString()) == 0){
                Random r = new Random();
                randomNum = r.nextInt(10);
                getServletContext().setAttribute("numero", randomNum);
            } else {
                randomNum = Integer.parseInt(getServletContext().getAttribute("numero").toString());
            }

            String mensaje;

            if(numero > randomNum){
                mensaje = "Te pasaste, ese numero es mayor";
            } else if (numero < randomNum){
                mensaje = "Piensa en grande, ese numero es mu chico";
            } else {
                mensaje = "ACERTASTE GOL GOL GOL GOOL";
            }

            context.setVariable("respuesta", mensaje);
            context.setVariable("intentos", "Te quedan "+contador+" intentos");
            template = "param";

        } else {
            context.setVariable("error", "Introduce las cosas primo");
            template = "error";
        }

        templateEngine.process(template, context, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }


}
