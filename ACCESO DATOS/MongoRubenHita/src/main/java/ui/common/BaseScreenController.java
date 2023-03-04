package ui.common;

import jakarta.xml.bind.JAXBException;
import ui.screen.principal.PrincipalController;

import java.io.IOException;

public class BaseScreenController {
    private PrincipalController principalController;

    public PrincipalController getPrincipalController() {
        return principalController;
    }

    public void setPrincipalController(PrincipalController principalController) {
        this.principalController = principalController;
    }

    public void principalCargado() throws IOException, JAXBException {
    }
}
