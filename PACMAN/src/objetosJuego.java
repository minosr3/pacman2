/* CLASE PADRE DE LA QUE DERIVARÁN LOS OBJETOS DEL TABLERO
 * DEFINIMOS ATRIBUTOS BÁSICOS A TODOS LOS OBJETOS.
 * Creado por: Javier Rodríguez Soler.
 * 1º DAM FLORIDA.
 * 06/06/2014
 */

import java.awt.Image;
import java.awt.Rectangle;


public class objetosJuego {

	
	protected Image imagen;  //Imagen del objeto.
	protected int x,y;  // Posición x e y del objeto en pantalla.
	protected boolean activo; //Si el objeto se encuentra activo o no.
	protected int width, height;// Anchura y altura de la imagen del objeto
	protected int clase ; //1 muro 2 punto
	
	
	// Devuelve la tipología del objeto.
	
	public int getClase ()
	{
		return clase;
	}
	//Devuelve la imagen del objeto.
	public Image getImagen()
	{
		
		return imagen;
		
	}
	
	//Método para colocar en pantalla en la posición X
	public void setX(int x)
	{
		this.x = x;
	}
	
	//Método para colocar en pantalla en la posición y.
	public void setY(int y)
	{
		this.y = y;
	}
	
	//Método para que nos retorne la posición X almacenado en el objeto.
	public int getX()
	{
		return x;
	}
	
	//Metodo para que nos rectorne la posición y almacenado en el objeto.
	public int getY()
	{
		return y;
	}
	
	// Metodo para variar el estado del objeto si está activo o no.
	public void setActivo(boolean activo)
	{
		this.activo= activo;
	}
	
	
	// Método para que nos retorne la situación del objeto si es activo o no.
	public boolean getActivo()
	{
		return activo;
	}
	
	//Método para determinar la posición del objeto para la detección de colisiones.
	public Rectangle getBounds(){
		return new Rectangle(this.x,this.y,this.width,this.height);
	}
	
	
}
