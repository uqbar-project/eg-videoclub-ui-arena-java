package uqbar.videoclub.arena;

import org.uqbar.arena.windows.WindowOwner;

import tadp.blocbaster.entidades.Socio;

/**
 * @author npasserini
 */
public class ModificarSocioDialog extends AbstractSocioDialog {
	private static final long serialVersionUID = -5686548988446128817L;

	public ModificarSocioDialog(WindowOwner owner, Socio socio) {
		super(owner, socio);
	}

	@Override
	protected void executeTask() {
		super.executeTask();
		this.getHome().update(this.getModelObject());
	}
	
}
