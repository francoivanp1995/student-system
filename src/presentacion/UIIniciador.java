package presentacion;

import datos.Excepcion.PanelException;

public class UIIniciador {
    private PanelManager panelManager;
    private FramePrincipal frameInicio;
    private ControladorPanelInicio controladorPanelInicio;
    private PanelInicio panelInicio;

    public UIIniciador() {
        this.panelManager = new PanelManager();
        this.frameInicio = new FramePrincipal(panelManager);
    }

    public void iniciarFrame() throws PanelException {

        System.out.println("Aca llego en iniciarFrame antes de panelManager.iniciar");
        panelManager.iniciar();
        panelInicio = panelManager.getPanelInicio();
        controladorPanelInicio = new ControladorPanelInicio(panelInicio,panelManager);
        System.out.println("Aca NO LLEGO en UI iniciador");
        panelInicio.setListener(controladorPanelInicio);
        panelManager.showFrame();
    }
}
