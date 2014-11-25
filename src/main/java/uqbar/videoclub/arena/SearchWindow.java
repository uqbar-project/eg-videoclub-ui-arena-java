package uqbar.videoclub.arena;

import org.uqbar.arena.bindings.NotNullObservable;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.commons.model.Entity;
import org.uqbar.commons.model.Search;

public abstract class SearchWindow<E extends Entity, T extends Search<E>> extends SimpleWindow<T> {
	private static final long serialVersionUID = 1L;

	public SearchWindow(WindowOwner owner, T model) {
		super(owner, model);
	}

	@Override
	protected void createMainTemplate(Panel mainPanel) {
		super.createMainTemplate(mainPanel);
		this.createResultsGrid(mainPanel);
		this.createGridActions(mainPanel);
	}

	// ***********************************************************
	// ** Grid
	// ***********************************************************

	protected void createResultsGrid(Panel mainPanel) {
		Table<E> table = new Table<E>(mainPanel, this.getModelObject().getEntityType());

		table.bindItemsToProperty(Search.RESULTS);
		table.bindSelectionToProperty(Search.SELECTED);

		this.describeResultsGrid(table);
	}

	protected abstract void describeResultsGrid(Table<E> builder);

	// ***********************************************************
	// ** Actions
	// ***********************************************************

	@Override
	protected void addActions(Panel actionsPanel) {
		Button buscar = new Button(actionsPanel);
		buscar.setCaption("Buscar");
		buscar.onClick(getModelObject()::search);
		buscar.setAsDefault();

		// TODO Ver si agregamos la acción de limpiar:
		// new Button(actions).setCaption("Limpiar").onClick(new MessageSend(this.getModel(), "clear"));
	}

	protected void createGridActions(Panel mainPanel) {
		Panel actionsPanel = new Panel(mainPanel);
		actionsPanel.setLayout(new HorizontalLayout());

		NotNullObservable elementSelected = new NotNullObservable(Search.SELECTED);

		Button edit = new Button(actionsPanel);
		edit.setCaption("Edit");
		edit.bindEnabled(elementSelected);
		edit.onClick(this::startEdition);

		Button remove = new Button(actionsPanel);
		remove.setCaption("Remove");
		remove.bindEnabled(new NotNullObservable(Search.SELECTED));
		remove.onClick(getModelObject()::removeSelected);
	}

	// ********************************************************
	// ** Actions
	// ********************************************************

	public void startEdition() {
		Dialog<?> editor = this.createEditor(this.getModelObject().getSelected());
		editor.onAccept(getModelObject()::search);
		editor.open();
	}

	protected abstract Dialog<?> createEditor(E selected);

}
