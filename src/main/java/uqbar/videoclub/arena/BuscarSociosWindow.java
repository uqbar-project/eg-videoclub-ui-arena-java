package uqbar.videoclub.arena;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Control;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.commons.model.SearchByExample;

import tadp.blocbaster.daos.Videoclub;
import tadp.blocbaster.entidades.Socio;


/**
 * Representa la ventana de busqueda de socios del videoclub.
 * 
 * @author npasserini
 */
@SuppressWarnings("serial")
public class BuscarSociosWindow extends SearchWindow<Socio, SearchByExample<Socio>> {
	
	public BuscarSociosWindow(WindowOwner owner) {
		super(owner, new SearchByExample<Socio>(Videoclub.getInstance().getHome(Socio.class)));
	}

	@Override
	protected void createMainTemplate(Panel formBuilder) {
		this.setTitle("Buscador de Socios");
		this.setTaskDescription("Ingrese los parámetros de búsqueda");
		super.createMainTemplate(formBuilder);
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel searchFormPanel = new Panel(mainPanel);
		searchFormPanel.bindContentsToProperty(SearchByExample.EXAMPLE);
		searchFormPanel.setLayout(new ColumnLayout(2));

		// Field nombre
		Label nombreLabel = new Label(searchFormPanel);
		nombreLabel.setText("Nombre");

		Control nombre = new TextBox(searchFormPanel);
		nombre.setWidth(200);
		nombre.bindValueToProperty(Socio.NOMBRE);

		// Field direccion
		Label direccionLabel = new Label(searchFormPanel);
		direccionLabel.setText("Direccion");

		Control direccion = new TextBox(searchFormPanel);
		direccion.setWidth(200);
		direccion.bindValueToProperty(Socio.DIRECCION);
		
		new Label(searchFormPanel).setText("Estado");
		new Selector(searchFormPanel).setContents(Arrays.asList(Socio.Estado.values()), "nombre").bindValueToProperty("estado");
	}

	@Override
	protected void describeResultsGrid(Table<Socio> table) {
		Column<Socio> nombreColumn = new Column<Socio>(table);
		nombreColumn.setTitle("Nombre");
		nombreColumn.setFixedSize(200);
		nombreColumn.bindContentsToProperty(Socio.NOMBRE);
		// table.add(column);

		Column<Socio> ingresoColumn = new Column<Socio>(table);
		ingresoColumn.setTitle("Fecha de ingreso");
		ingresoColumn.setFixedSize(200);
		ingresoColumn.bindContentsToProperty("fecha").setTransformer(fecha -> new SimpleDateFormat("dd/MM/yyyy").format(fecha));

		Column<Socio> direccionColumn = new Column<Socio>(table);
		direccionColumn.setTitle("Direccion");
		direccionColumn.setFixedSize(200);
		direccionColumn.bindContentsToProperty(Socio.DIRECCION);
		
		table.setHeight(300);
		table.setWidth(600);
	}

	@Override
	protected void addActions(Panel actionsPanel) {
		Button nuevoSocio = new Button(actionsPanel);
		nuevoSocio.setCaption("Nuevo Socio");
		nuevoSocio.onClick(this::crearSocio);
		
		super.addActions(actionsPanel);
	}

	// ********************************************************
	// ** Acciones
	// ********************************************************

	public void crearSocio() {
		Dialog<?> crearSocio = new CrearSocioDialog(this);
		crearSocio.onAccept(getModelObject()::search);
		crearSocio.open();
	}

	@Override
	protected Dialog<?> createEditor(Socio selected) {
		return new ModificarSocioDialog(this, selected);
	}
}
