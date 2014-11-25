package uqbar.videoclub.arena;

import java.util.Arrays;

import org.uqbar.arena.aop.windows.TransactionalDialog;
import org.uqbar.arena.bindings.DateAdapter;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.commons.model.Home;

import tadp.blocbaster.daos.Videoclub;
import tadp.blocbaster.entidades.Ciudad;
import tadp.blocbaster.entidades.Socio;

/**
 * @author npasserini
 */
@SuppressWarnings("serial")
public abstract class AbstractSocioDialog extends TransactionalDialog<Socio> {
	private Home<Socio> home;

	public AbstractSocioDialog(WindowOwner owner, Socio model) {
		super(owner, model);
		this.home = Videoclub.getInstance().getHome(Socio.class);
	}

	@Override
	protected void createMainTemplate(Panel mainPanel) {
		this.setTitle("ABM de Socios");
		super.createMainTemplate(mainPanel);
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel form = new Panel(mainPanel);
		form.setLayout(new ColumnLayout(2));

		new Label(form).setText("Nombre");
		TextBox txtNombre = new TextBox(form);
		txtNombre.setWidth(200);
		txtNombre.bindValueToProperty(Socio.NOMBRE);

		new Label(form).setText("Direccion");
		TextBox txtDireccion = new TextBox(form);
		txtDireccion.setWidth(300);
		txtDireccion.bindValueToProperty(Socio.DIRECCION);

		new Label(form).setText("Fecha de Ingreso");
		TextBox txtFechaIngreso = new TextBox(form);
		txtFechaIngreso.bindValueToProperty(Socio.FECHA_INGRESO).setTransformer(new DateAdapter());
		
		// combo al enum de estado
		new Label(form).setText("Estado");
		new Selector(form).setContents(Arrays.asList(Socio.Estado.values()), "nombre").bindValueToProperty("estado");
		
		new Label(form).setText("Ciudad");
		new Selector(form).setContents(Videoclub.getInstance().getHome(Ciudad.class).allInstances(), "nombre").bindValueToProperty("ciudad");
	}
	
	@Override
	protected void addActions(Panel actions) {
		new Button(actions)
			.setCaption("Aceptar")
			.onClick(this::accept)
			.setAsDefault()
			.disableOnError();

		new Button(actions) //
			.setCaption("Cancelar")
			.onClick(this::cancel);
	}
	
	public Home<Socio> getHome() {
		return this.home;
	}

}