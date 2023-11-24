/* CLASE muro_Obj derivada de la clase padre objetosJuego
 * Creado por: Javier Rodríguez Soler.
 * 1º DAM FLORIDA.
 * 09/06/2014
 * ver: 1
 */
import javax.swing.ImageIcon;


public class muro_Obj extends objetosJuego{

	public muro_Obj(int x, int y) {
		
		ImageIcon ii= new ImageIcon(this.getClass().getResource("/Suelo.png")); //Capturemos el recurso de la imagen.
		imagen= ii.getImage(); //Retornamos la imagen.
		this.x = x;// Colocamos la posición X
		this.y= y; // Colocamos la posición Y.
		width = imagen.getWidth(null); //Cogemos la Anchura de la imagen.
		height = imagen.getHeight(null); // Cogemos la Altura de la imagen.
		clase= 1;
		activo=true; // Pasamos el objeto como activo dentro de la pantalla en Construcción.
		
		
	}

}
