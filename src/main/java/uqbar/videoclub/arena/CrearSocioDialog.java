package uqbar.videoclub.arena;

import org.uqbar.arena.windows.WindowOwner;

import tadp.blocbaster.entidades.Socio;

/**
 * 
 * @author npasserini
 */
public class CrearSocioDialog extends AbstractSocioDialog {
	
	public CrearSocioDialog(WindowOwner owner) {
		super(owner, new Socio());
	}
	
	@Override
	protected void executeTask() {
		super.executeTask();
		getHome().create(this.getModelObject());
	}
	
}
