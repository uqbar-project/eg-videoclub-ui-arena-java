package uqbar.videoclub.arena;

import org.uqbar.arena.windows.WindowOwner;

import tadp.blocbaster.entidades.Socio;

/**
 * 
 * @author npasserini
 */
@SuppressWarnings("serial")
public class CrearSocioDialog extends AbstractSocioDialog {
	
	public CrearSocioDialog(WindowOwner owner) {
		super(owner, new Socio());
	}
	
	@Override
	protected void executeTask() {
		getHome().create(this.getModelObject());
		super.executeTask();
	}
	
}
