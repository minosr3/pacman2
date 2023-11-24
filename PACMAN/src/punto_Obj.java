/* CLASE punto_Obj derivada de la clase padre objetosJuego
 * Creado por: Javier Rodríguez Soler.
 * 1º DAM FLORIDA.
 * 09/06/2014
 * ver: 1
 */

import javax.swing.ImageIcon;


public class punto_Obj extends objetosJuego{

	public punto_Obj(int x, int y ) {
		
		{
			ImageIcon ii= new ImageIcon(this.getClass().getResource("/pill.png"));
			imagen= ii.getImage();
			this.x = x;
			this.y= y;
			this.width = imagen.getWidth(null);
			this.height = imagen.getHeight(null);
			clase =2;
			activo = true;
			
		}
	}

}
