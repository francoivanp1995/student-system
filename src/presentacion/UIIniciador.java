package presentacion;

import datos.Excepcion.PanelException;

public class UIIniciador {
    private PanelManager panelManager;
    private FrameInicio frameInicio;
    private ControladorLogin controladorLogin;
    private PanelInicio panelInicio;

    public UIIniciador() {
        this.panelManager = new PanelManager();
        this.frameInicio = new FrameInicio(panelManager);
    }

    public void iniciarFrame() throws PanelException {

        System.out.println("Aca llego en iniciarFrame antes de panelManager.iniciar");
        panelManager.iniciar();
        panelInicio = panelManager.getPanelInicio();
        controladorLogin = new ControladorLogin(panelInicio,panelManager);
        System.out.println("Aca NO LLEGO en UI iniciador");
        panelInicio.setListener(controladorLogin);
        panelManager.showFrame();
    }
}
