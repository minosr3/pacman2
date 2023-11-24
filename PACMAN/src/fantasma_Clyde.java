/* CLASE hija de fantasma.java fantasma_Clyde.java
 * Creado por: Javier Rodríguez Soler.
 * 1º DAM FLORIDA.
 * 19/06/2014
 * ver: 1
 */


import javax.swing.ImageIcon;


public class fantasma_Clyde extends fantasma{

	public fantasma_Clyde() {
		ImageIcon ii = new ImageIcon(this.getClass().getResource(
				"/ghost_orange.png"));
		imagen = ii.getImage();
		x = 340;
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
				"/ghost_orange.png"));
		imagen = ii.getImage();
		x = 340;
		y = 290;
		width = imagen.getWidth(null);
		height = imagen.getHeight(null);
		direccion = 2;
		comestible = false;
		activo = true;
		
	}
	
	// Siempre se moverá dentro de sus posibilidades Verticalmente y posteriormente horizontalmente, cuando llegue al 
				// final de su camino.
				public void cambiarDireccion()
				{
					int numeroAleatorio;
					do {
					//Definimos el número Aleatorio  ya que el movimiento tiene ue ser aleatorio.
					numeroAleatorio = (int) (Math.random()*4+1);
					}while (direccion == numeroAleatorio);
						
	  			switch(numeroAleatorio)
					{
					case 1:{
						direccion = 3;
						break;
					}
					case 2:
					{
						direccion = 4;
						break;
					}
					case 3:
					{
						direccion =1;
						break;
					}
					case 4:
					{
						direccion =2;
						break;
					}
					}
				
				}

}
