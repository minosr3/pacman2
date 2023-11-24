/* CLASE PADRE fantasma.java heredaran las clases hijas fantasmas.
 * Creado por: Javier Rodríguez Soler.
 * 1º DAM FLORIDA.
 * 16/06/2014
 * ver: 2
 */
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.Timer;



public class fantasma {

	protected int x, y; // Variables donde se alamcena la posición del fantasma
						// x,y
	protected Image imagen; //Varaible donde almacena la imagen del fantasma.
	protected int width; // Ancho.
	protected int height; // Alto.
	protected int direccion; // 1 izquierda 2 Arriba 3 Derecha 4 Abajo.
	protected boolean activo; // Para determinar si se encuentra activo.
	protected boolean comestible; // Para determinar si el fantasma es comestible.

	public Image getImagen() {
		return imagen;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean getComestible() {
		return comestible;
	}
	
	
	// Método para indicar si el fantasma es comestible
	public void setComestible( boolean activo){
		final Image imagenanterior = imagen;
		
		
		// MÉTODO  Y objeto timer para determinar el tiempo de conversión de los fantasmas.
		Timer timer = new Timer (9000, new ActionListener(){
			public void actionPerformed (ActionEvent e)
			{
				
				comestible = false;
				imagen = imagenanterior;
							
				
			}
		});
		
		
		if (activo==true){
			comestible = true;
		    ImageIcon ii = new ImageIcon(this.getClass().getResource(
				"/ghost_teal.png"));
		    imagen = ii.getImage();
		    
			timer.restart();
			//timer.start();
			activo =false;
			
			
		
		}else{
		
		timer.stop();
		timer.removeActionListener(null);
		}
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public boolean getActivo() {
		return activo;
	}

	
	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}

	public int getDireccion() {
		return direccion;
	}

	public void moverDerecha() {
		x = x + 1;
	}

	public void moverIzquierda() {
		x = x - 1;
	}

	public void moverAbajo() {
		y = y + 1;

	}

	public void moverArriba() {
		y = y - 1;
	}

	//cambiar la dirección cuando se trata de la colisión con otros fantasmas.
	
		public void cambiarDireccion(int direccionAntigua)
		{
			switch(direccionAntigua)
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
	
	// Método para determinar la posición de los bordes de gráficos en la
	// posición que se le pasa.
	public Rectangle getBounds(int x, int y) {
		return new Rectangle(x, y, width, height);
	}

}
