package uqbar.videoclub.arena;

import org.uqbar.arena.windows.WindowOwner;

import tadp.blocbaster.entidades.Socio;

/**
 * @author npasserini
 */
@SuppressWarnings("serial")
public class ModificarSocioDialog extends AbstractSocioDialog {

	public ModificarSocioDialog(WindowOwner owner, Socio socio) {
		super(owner, socio);
	}

	@Override
	protected void executeTask() {
		super.executeTask();
		this.getHome().update(this.getModelObject());
	}
	
}
