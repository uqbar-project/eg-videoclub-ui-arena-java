package uqbar.videoclub.arena;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;

/**
 * Punto de entrada para ejecutar la aplicacion del videoclub con el framework
 * Arena + eclipse jface.
 * 
 * @author npasserini
 */
public class VideoclubApplication extends Application {
	
	public static void main(String[] args) {
		new VideoclubApplication().start();
	}

	@Override
	protected Window<?> createMainWindow() {
		return new BuscarSociosWindow(this);
	}
}
