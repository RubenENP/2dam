package ui.common;

import ui.principal.PrincipalController;

import java.io.IOException;

public class BasePantallaController {
    private PrincipalController principalController;

    public PrincipalController getPrincipalController() {
        return principalController;
    }

    public void setPrincipalController(PrincipalController principalController) {
        this.principalController = principalController;
    }


    public void principalCargado() throws IOException {
        //Para llamar cuando cargue
    }
}
