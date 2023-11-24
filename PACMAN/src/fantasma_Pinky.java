/* CLASE hija de fantasma.java fantasma_Pinky.java
 * Creado por: Javier Rodríguez Soler.
 * 1º DAM FLORIDA.
 * 19/06/2014
 * ver: 1
 */

import javax.swing.ImageIcon;


public class fantasma_Pinky extends fantasma {

	public fantasma_Pinky() {
		ImageIcon ii = new ImageIcon(this.getClass().getResource(
				"/ghost_pink.png"));
		imagen = ii.getImage();
		x = 320;
		y = 290;
		width = imagen.getWidth(null);
		height = imagen.getHeight(null);
		direccion = 2;
		comestible = false;
		activo = true;
	}

	public void setReiniciar()
	{
		ImageIcon ii = new ImageIcon(this.getClass().getResource(
				"/ghost_pink.png"));
		imagen = ii.getImage();
		x = 320;
		y = 290;
		width = imagen.getWidth(null);
		height = imagen.getHeight(null);
		direccion = 2;
		comestible = false;
		activo = true;
		
	}
	
	
	//cambiar la dirección cuando se trata de la colisión con otro fantasma.
	
	
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
}
