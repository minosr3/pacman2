/* CLASE hija de fantasma.java fantasma_Blinky.java
 * Creado por: Javier Rodríguez Soler.
 * 1º DAM FLORIDA.
 * 19/06/2014
 * ver: 1
 */
import javax.swing.ImageIcon;


public class fantasma_Blinky extends fantasma {

	private int velocidad;
	
		
	public fantasma_Blinky() {
		ImageIcon ii = new ImageIcon(this.getClass().getResource(
				"/ghost_red.png"));
		imagen = ii.getImage();
		x = 380;
		y = 240;
		width = imagen.getWidth(null);
		height = imagen.getHeight(null);
		direccion = 3;
		comestible = false;
		activo = true;
		velocidad =1;
	}
	public void setReiniciar()
	{
		ImageIcon ii = new ImageIcon(this.getClass().getResource(
				"/ghost_red.png"));
		imagen = ii.getImage();
		x = 380;
		y = 240;
		width = imagen.getWidth(null);
		height = imagen.getHeight(null);
		direccion = 3;
		comestible = false;
		activo = true;
		velocidad =1;
	}
	// Siempre se moverá dentro de sus posibilidades Verticalmente y posteriormente horizontalmente, cuando llegue al 
		// final de su camino.
		public void cambiarDireccion(int posPacX, int posPacY, int direccionAntigua)
		{
			
			
			
			switch(direccionAntigua)
			{
			
			case 1:
				if (posPacY < y)
				{
					direccion = 2; 
				} else if (posPacY > y)
					{
						direccion = 4;
					}
				break;
			case 2:
				
			{
				if (posPacX < x)
				{
					direccion = 1;
				}else if (posPacX>x)
				{
					direccion = 3;
				}
			
				break;
			}
			case 3:
			{
				if (posPacY < y)
				{
					direccion = 2; 
				} else
					if (posPacY> y)
					{
						direccion = 4;
					}
				break;
			}
			case 4:
			{
				if (posPacX < x)
				{
					direccion = 1;
				}else if (posPacX>x)
				{
					direccion = 3;
				}
				break;
			}
		  
			}
		
		}
		
		@Override 
		public void moverDerecha() {
			x = x + velocidad;
		}

		@Override
		public void moverIzquierda() {
			x = x - velocidad;
		}
		@Override
		public void moverAbajo() {
			y = y + velocidad;

		}

		@Override
		public void moverArriba() {
			y = y - velocidad;
		}

		public void comprobar_Velocidad(int pacmanpuntos)
		{
			if (pacmanpuntos > 100)
			{
				velocidad = 2;
			} else if (pacmanpuntos>200)
			{
				velocidad = 3;
			}else if (pacmanpuntos>300)
			{
				velocidad = 4;
			}
		}


}
