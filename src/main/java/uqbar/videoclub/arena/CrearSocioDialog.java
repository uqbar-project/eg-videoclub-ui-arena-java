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
	public void accept() {
		// WORKAROUND momentáneo, porque el executeTask() no se llama desde un TransactionalDialog
		getHome().create(this.getModelObject());
		super.accept();
	}

}
