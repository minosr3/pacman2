/* CLASE Tablero.java Controlador del juego.
 * Creado por: Javier Rodríguez Soler.
 * 1º DAM FLORIDA.
 * 09/06/2014
 * ver: 3
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;



public class Tablero extends JPanel implements ActionListener {

	private Timer timer;
	private objetosJuego[][] mapa = new objetosJuego[29][29];
	private pacman _pacman;
	private fantasma_Blinky _fantasma_Blinky;
	private fantasma_Pinky _fantasma_Pinky;
	private fantasma_Clyde _fantasma_Clyde;
	private libreriaAudio _audio;
	private ImageIcon ii = new ImageIcon(this.getClass().getResource("/logo_uned.gif"));
	private Image imagen = ii.getImage();
	private String Estado="inicio";
	private int puntos=0;

	// DefiniciON del nivel.
	int[][] map = {
			// 0 - empty panel
			// 1 - a wall
			// 2 - regular pill
			// 3 - mighty pill
			// 4 - super pill
			// -1 - cage gate
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
					1, 1, 1, 1, 1, 1 },
			{ 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2,
					2, 2, 2, 2, 2, 1 },
			{ 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2,
					1, 1, 1, 1, 2, 1 },
			{ 1, 3, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2,
					1, 1, 1, 1, 3, 1 },
			{ 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2,
					1, 1, 1, 1, 2, 1 },
			{ 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
					2, 2, 2, 2, 2, 1 },
			{ 1, 2, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2,
					1, 1, 1, 1, 2, 1 },
			{ 1, 2, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2,
					1, 1, 1, 1, 2, 1 },
			{ 1, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 1, 1, 2,
					2, 2, 2, 2, 2, 1 },
			{ 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 2,
					1, 1, 1, 1, 1, 1 },
			{ 0, 0, 0, 0, 0, 1, 2, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 2,
					1, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 1, 2, 1, 1, 0, 1, 1, 1, -1, -1, 1, 1, 1, 0, 1, 1,
					2, 1, 0, 0, 0, 0, 0 },
			{ 1, 1, 1, 1, 1, 1, 2, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 2,
					1, 1, 1, 1, 1, 1 },
			{ 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 2,
					0, 0, 0, 0, 0, 0 }, // PORTAL
			{ 1, 1, 1, 1, 1, 1, 2, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 2,
					1, 1, 1, 1, 1, 1 },
			{ 0, 0, 0, 0, 0, 1, 2, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 2,
					1, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 1, 2, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 2,
					1, 0, 0, 0, 0, 0 },
			{ 1, 1, 1, 1, 1, 1, 2, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 2,
					1, 1, 1, 1, 1, 1 },
			{ 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2,
					2, 2, 2, 2, 2, 1 },
			{ 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2,
					1, 1, 1, 1, 2, 1 },
			{ 1, 3, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2,
					1, 1, 1, 1, 3, 1 },
			{ 1, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
					1, 1, 2, 2, 2, 1 },
			{ 1, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2,
					1, 1, 2, 1, 1, 1 },
			{ 1, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2,
					1, 1, 2, 1, 1, 1 },
			{ 1, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 1, 1, 2,
					2, 2, 2, 2, 2, 1 },
			{ 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1,
					1, 1, 1, 1, 2, 1 },
			{ 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1,
					1, 1, 1, 1, 2, 1 },
			{ 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
					2, 2, 2, 2, 2, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
					1, 1, 1, 1, 1, 1 },

	};

	private class TAdopter extends KeyAdapter {

		public void keyReleased(KeyEvent e) {
			_pacman.keyReleased(e);
		}

		public void keyPressed(KeyEvent e) {
			_pacman.keyPressed(e);

		}
	}

	
	// CONSTRUCTOR DE LA CLASE.
	public Tablero() {

		setSize(new Dimension(610, 630));

		addKeyListener(new TAdopter());
		setFocusable(true);
		setBackground(Color.black);
		setDoubleBuffered(true);
		setSize(new Dimension(610, 630));

		cargaMapa();

		_audio = new libreriaAudio();
		_pacman = new pacman();
		_fantasma_Blinky = new fantasma_Blinky();
		_fantasma_Pinky = new fantasma_Pinky();
		_fantasma_Clyde = new fantasma_Clyde();
		timer = new Timer(1, this);
		timer.start();
	}

	// CONTROL DEL MOVIMIENTO DE PACMAN EN FUNCIÓN DE SI PUEDE O NO PUEDE.
	public void actionPerformed(ActionEvent e) {

		requestFocus();
		
		if (_pacman.getVida()<1 && Estado =="jugando"){
			Estado="gameover";
			puntos=0;
			_pacman.setReiniciar();
			_fantasma_Blinky.setReiniciar();
			_fantasma_Clyde.setReiniciar();
			_fantasma_Pinky.setReiniciar();
			
					
		}
		
		if (Estado=="inicio" && _pacman.getEnter())
		{
			Estado = "jugando";
		}
		
		
		if (!_pacman.getEstadoPausa() && Estado =="jugando") { //Comprobamos si han pulsado p y si es así pausa el juego.
			
			
		
			
						switch (_pacman.getDireccion()) {
			case 1: {
				if (!verificarObjeto(_pacman.getX() - 1, _pacman.getY())) {

					_pacman.move();
				}
				break;
			}
			case 2: {
				if (!verificarObjeto(_pacman.getX(), _pacman.getY() - 1)) {
					_pacman.move();
				}
				break;

			}
			case 3: {
				if (!verificarObjeto(_pacman.getX() + 1, _pacman.getY())) {
					_pacman.move();
				}
				break;
			}
			case 4: {
				if (!verificarObjeto(_pacman.getX(), _pacman.getY() + 1)) {
					_pacman.move();
				}
				break;
			}

			}

			// Comprobamos a que velocidad debe funcionar.
			_fantasma_Blinky.comprobar_Velocidad(_pacman.getPuntuacion());

			// Indicamos la posición del fantasma Blinky  en la pantalla.
			switch (_fantasma_Blinky.getDireccion()) {
			case 1: {
				if (!verificarObjetoFantasma(_fantasma_Blinky.getBounds(
						_fantasma_Blinky.getX() - 1, _fantasma_Blinky.getY()))) {
					_fantasma_Blinky.moverIzquierda();

				} else {
					_fantasma_Blinky.cambiarDireccion(_pacman.getX(),
							_pacman.getY(), 1);
				}
				break;
			}
			case 2: {
				if (!verificarObjetoFantasma(_fantasma_Blinky.getBounds(
						_fantasma_Blinky.getX(), _fantasma_Blinky.getY() - 1))) {
					_fantasma_Blinky.moverArriba();

				} else {
					_fantasma_Blinky.cambiarDireccion(_pacman.getX(),
							_pacman.getY(), 2);
				}

				break;

			}
			case 3: {
				if (!verificarObjetoFantasma(_fantasma_Blinky.getBounds(
						_fantasma_Blinky.getX() + 1, _fantasma_Blinky.getY()))) {
					_fantasma_Blinky.moverDerecha();

				} else {
					_fantasma_Blinky.cambiarDireccion(_pacman.getX(),
							_pacman.getY(), 3);
				}
				break;
			}
			case 4: {
				if (!verificarObjetoFantasma(_fantasma_Blinky.getBounds(
						_fantasma_Blinky.getX(), _fantasma_Blinky.getY() + 1))) {
					_fantasma_Blinky.moverAbajo();

				} else {
					_fantasma_Blinky.cambiarDireccion(_pacman.getX(),
							_pacman.getY(), 4);
				}
				break;

			}

			}
			
			// Indicamos la posición del fantasma Pinky en la pantalla.
						switch (_fantasma_Pinky.getDireccion()) {
						case 1: {
							if (!verificarObjetoFantasma(_fantasma_Pinky.getBounds(
									_fantasma_Pinky.getX() - 1, _fantasma_Pinky.getY()))) {
								_fantasma_Pinky.moverIzquierda();

							} else {
								_fantasma_Pinky.cambiarDireccion(_pacman.getX(),
										_pacman.getY(), 1);
							}
							break;
						}
						case 2: {
							if (!verificarObjetoFantasma(_fantasma_Pinky.getBounds(
									_fantasma_Pinky.getX(), _fantasma_Pinky.getY() - 1))) {
								_fantasma_Pinky.moverArriba();

							} else {
								_fantasma_Pinky.cambiarDireccion(_pacman.getX(),
										_pacman.getY(), 2);
							}

							break;

						}
						case 3: {
							if (!verificarObjetoFantasma(_fantasma_Pinky.getBounds(
									_fantasma_Pinky.getX() + 1, _fantasma_Pinky.getY()))) {
								_fantasma_Pinky.moverDerecha();

							} else {
								_fantasma_Pinky.cambiarDireccion(_pacman.getX(),
										_pacman.getY(), 3);
							}
							break;
						}
						case 4: {
							if (!verificarObjetoFantasma(_fantasma_Pinky.getBounds(
									_fantasma_Pinky.getX(), _fantasma_Pinky.getY() + 1))) {
								_fantasma_Pinky.moverAbajo();

							} else {
								_fantasma_Pinky.cambiarDireccion(_pacman.getX(),
										_pacman.getY(), 4);
							}
							break;

						}

						}
						
						// Indicamos la posición del fantasma Clyde en la pantalla.
						switch (_fantasma_Clyde.getDireccion()) {
						case 1: {
							if (!verificarObjetoFantasma(_fantasma_Clyde.getBounds(
									_fantasma_Clyde.getX() - 1, _fantasma_Clyde.getY()))) {
								_fantasma_Clyde.moverIzquierda();

							} else {
								_fantasma_Clyde.cambiarDireccion();
							}
							break;
						}
						case 2: {
							if (!verificarObjetoFantasma(_fantasma_Clyde.getBounds(
									_fantasma_Clyde.getX(), _fantasma_Clyde.getY() - 1))) {
								_fantasma_Clyde.moverArriba();

							} else {
								_fantasma_Clyde.cambiarDireccion();
							}

							break;

						}
						case 3: {
							if (!verificarObjetoFantasma(_fantasma_Clyde.getBounds(
									_fantasma_Clyde.getX() + 1, _fantasma_Clyde.getY()))) {
								_fantasma_Clyde.moverDerecha();

							} else {
								_fantasma_Clyde.cambiarDireccion();
							}
							break;
						}
						case 4: {
							if (!verificarObjetoFantasma(_fantasma_Clyde.getBounds(
									_fantasma_Clyde.getX(), _fantasma_Clyde.getY() + 1))) {
								_fantasma_Clyde.moverAbajo();

							} else {
								_fantasma_Clyde.cambiarDireccion();
							}
							break;

						}

						}

			colisionPersonajes();

			

		}

		if (Estado=="gameover" && _pacman.getEnter())
		{
			Estado ="inicio";
			_pacman.setVida(3);
			_pacman.setEnter(false);
			 puntos=0;
			_pacman.setReiniciar();
			_fantasma_Blinky.setReiniciar();
			_fantasma_Clyde.setReiniciar();
			_fantasma_Pinky.setReiniciar();
			cargaMapa();
			
			
		}
		
		
		repaint();		
		
	}

	// MÉTODO PRINCIPAL DE DIBUJO EN PANTALLA.
	public void paint(Graphics g) {
		super.paint(g); // SOBRESCRIBIMOS EL CONTEXTO GRÁFICO.

		Graphics2D g2d = (Graphics2D) g; // LO CONVERTIMOS EN TIPO 2D.
		Toolkit.getDefaultToolkit().sync(); // MÉTODO PARA SINCRONIZAR.

		
		switch (Estado){
		
		case "inicio":
		{
			g2d.setColor(Color.WHITE);
			g2d.setFont(new Font("Verdana", Font.BOLD, 60));
			g2d.drawString("Pac-Man en Java",150, 200);
			g2d.setFont(new Font("Verdana", Font.BOLD, 30));
			g2d.drawString("Pulse [Intro] para continuar", 190, 310);
			g2d.setFont(new Font("Verdana", Font.BOLD, 15));
			g2d.drawString("Alumno: Javier Rodríguez Soler", 10, 600);
			g2d.drawString("Asignatura: Programación Orientada a Objetos", 10, 630);
			g2d.drawString("Curso: 2013/2014", 10, 660);
			g2d.drawImage(imagen, 350, 400,this);
			break;
		}
		case"jugando":
		{
			try {
				for (int columna = 0; columna < mapa.length; columna++) {
					for (int fila = 0; fila < mapa.length - 1; fila++) {

						if (mapa[columna][fila].clase != 0
								&& mapa[columna][fila].getActivo() == true) {

							g2d.drawImage(mapa[columna][fila].getImagen(),
									fila * 24, columna * 24, this);
						}

					}
				}
			} catch (NullPointerException ex) {
				ex.printStackTrace();
			}

			// DIBUJAMOS A PACMAN EN EL TABLERO.

			if (_pacman.getActivo() == true) {
				g2d.drawImage(_pacman.getImage(), _pacman.getX(),
						_pacman.getY(), this);
			}

			// COMPROBAMOS SI LOS FANTASMAS SE ENCUENTRA ACTIVO Y SE DIBUJAN EN PANTALLA.
			if (_fantasma_Blinky.getActivo() == true) {
				// DIBUJAMOS A FANTASMA EN EL TABLERO.
				g2d.drawImage(_fantasma_Blinky.getImagen(),
						_fantasma_Blinky.getX(), _fantasma_Blinky.getY(), this);
			}
			if (_fantasma_Pinky.getActivo() == true) {
				// DIBUJAMOS A FANTASMA EN EL TABLERO.
				g2d.drawImage(_fantasma_Pinky.getImagen(),
						_fantasma_Pinky.getX(), _fantasma_Pinky.getY(), this);
			}
			if (_fantasma_Clyde.getActivo() == true) {
				// DIBUJAMOS A FANTASMA EN EL TABLERO.
				g2d.drawImage(_fantasma_Clyde.getImagen(),
						_fantasma_Clyde.getX(), _fantasma_Clyde.getY(), this);
			}

			// SECCIÓN PARA DIBUJAR LA PUNTUACIÓN DE PACMAN EN PANTALLA.
			g2d.setColor(Color.BLUE);
			g2d.setFont(new Font("Verdana", Font.BOLD, 20));
			g2d.drawString("PUNTUACIÓN", 680, 50);

			g2d.setColor(Color.WHITE);
			g2d.setFont(new Font("Verdana", Font.BOLD, 30));
			g2d.drawString(String.valueOf(_pacman.getPuntuacion()), 740, 80);

			// SECCIÓN PARA DIBUJAR LAS VIDAS DE PACMAN.
			g2d.setColor(Color.BLUE);
			g2d.setFont(new Font("Verdana", Font.BOLD, 20));
			g2d.drawString("VIDAS", 720, 130);

			for (int i = 0; i < _pacman.getVida(); i++) {
				g2d.drawImage(_pacman.getImage(), 710 + i * 30, 150, this);
				
				
							
			}
			
			
			//SECCIÓN PARA DIBUJAR EL LOGO D
			g2d.drawImage(imagen, 720, 430,this);
			
			//SECCIÓN PARA DIBUJAR LA PAUSA.
			if (_pacman.getEstadoPausa()){
			g2d.fillRect(720,550, 100, 100);
			g2d.setColor(Color.WHITE);
			g2d.setFont(new Font("Verdana", Font.BOLD, 20));
			g2d.drawString("PAUSA", 730, 600);
			}
			break;
		}
		case "gameover":
		{
			
			g2d.setColor(Color.WHITE);
			g2d.setFont(new Font("Verdana", Font.BOLD, 60));
			g2d.drawString("Game Over", 250, 250);
			g2d.setFont(new Font("Verdana", Font.BOLD, 30));
			g2d.drawString("Pulse [Intro] para continuar", 190, 310);
			
			if (puntos>236)
			{
				g2d.setFont(new Font("Verdana", Font.BOLD, 80));
				g2d.drawString("FELICIDADES", 200, 420);
			}
			break;
		}
		}
	}

	// MÉTODO PARA COMPROBAR SI PACMAN SE PUEDE MOVER.
	public boolean verificarObjeto(int x, int y) {
		Rectangle r1 = _pacman.getBounds(x, y);

		for (int columna = 0; columna < mapa.length; columna++) {
			for (int fila = 0; fila < mapa.length - 1; fila++) {
				Rectangle r2 = mapa[columna][fila].getBounds();

				// verificamos que es pared.
				if (r1.intersects(r2) && mapa[columna][fila].getClase() == 1) {
					return true;
				}
				// verificamos que es punto y si es así se lo come.
				if (r1.intersects(r2) && mapa[columna][fila].getClase() == 2
						&& mapa[columna][fila].getActivo() == true) {
					mapa[columna][fila].setActivo(false);
					_pacman.setPuntuacion(10); // Sumamos los puntos del
												// puntito.
					_audio.BALL.play(); // Mostramos Auido para la comida.
					puntos ++;
					if (puntos>236){
						Estado ="gameover";
						
					}

				}

				// Verificamos que se trata de un super punto y si es así se lo
				// come.
				if (r1.intersects(r2) && mapa[columna][fila].getClase() == 3
						&& mapa[columna][fila].getActivo() == true) {
					mapa[columna][fila].setActivo(false);
					_pacman.setPuntuacion(10); // Sumamos los puntos del
												// puntito.
					_audio.BALL.play();// Mostramos Auido para la comida.
					_fantasma_Blinky.setComestible(true);
					_fantasma_Pinky.setComestible(true);
					_fantasma_Clyde.setComestible(true);
					_audio.convierteAzulFantasma.play();
					_fantasma_Blinky.setComestible(false);
					_fantasma_Pinky.setComestible(false);
					_fantasma_Clyde.setComestible(false);
					
				}
			}
		}

		return false;

	}

	// MÉTODO PARA COMPROBAR SI LOS FANTASMAS SE PUEDEN MOVER.
	public boolean verificarObjetoFantasma(Rectangle fantasma) {
		for (int columna = 0; columna < mapa.length; columna++) {
			for (int fila = 0; fila < mapa.length - 1; fila++) {
				Rectangle r2 = mapa[columna][fila].getBounds();

				// verificamos que es pared.
				if (fantasma.intersects(r2)
						&& mapa[columna][fila].getClase() == 1) {
					return true;
				}
			}
		}

		return false;
	}

	// Método para determinar si colisionan los personajes.

	public void colisionPersonajes() {
		Rectangle r1 = _pacman.getBounds(_pacman.getX(), _pacman.getY());
		Rectangle r2 = _fantasma_Blinky.getBounds(_fantasma_Blinky.getX(),
				_fantasma_Blinky.getY());
		Rectangle r3 = _fantasma_Pinky.getBounds(_fantasma_Pinky.getX(), _fantasma_Pinky.getY());
		Rectangle r4 = _fantasma_Clyde.getBounds(_fantasma_Clyde.getX(), _fantasma_Clyde.getY());
		
		
		if (r1.intersects(r2) && _fantasma_Blinky.getComestible() == true) {
			_audio.comeFantasma.play();
			_fantasma_Blinky.setActivo(false);
			_pacman.setPuntuacion(100);
			_fantasma_Blinky.setReiniciar();
			
		} else if (r1.intersects(r2)
				&& _fantasma_Blinky.getComestible() == false) {
			int vidas = _pacman.getVida();
			vidas = vidas - 1;
			_pacman.setVida(vidas);
			_audio.muerePacman.play();

			_pacman.setActivo(false);
			_pacman.setReiniciar();
			_fantasma_Blinky.setReiniciar();

		}
		
		
		if (r1.intersects(r3) && _fantasma_Pinky.getComestible() == true) {
			_fantasma_Pinky.setActivo(false);
			_pacman.setPuntuacion(100);
			_fantasma_Pinky.setReiniciar();
			
		} else if (r1.intersects(r3)
				&& _fantasma_Pinky.getComestible() == false) {
			int vidas = _pacman.getVida();
			vidas = vidas - 1;
			_pacman.setVida(vidas);
			_audio.muerePacman.play();

			_pacman.setActivo(false);
			_pacman.setReiniciar();
			_fantasma_Pinky.setReiniciar();

		}
		
		if (r1.intersects(r4) && _fantasma_Clyde.getComestible() == true) {
			_fantasma_Clyde.setActivo(false);
			_pacman.setPuntuacion(100);
			_fantasma_Clyde.setReiniciar();
			
		} else if (r1.intersects(r4)
				&& _fantasma_Clyde.getComestible() == false) {
			int vidas = _pacman.getVida();
			vidas = vidas - 1;
			_pacman.setVida(vidas);
			_audio.muerePacman.play();

			_pacman.setActivo(false);
			_pacman.setReiniciar();
			_fantasma_Clyde.setReiniciar();

		}
		
		
		//En el caso de que los fantasmas choque entre si.
		
		if (r2.intersects(r3))
		{
			_fantasma_Pinky.cambiarDireccion(_fantasma_Pinky.getDireccion());
			_fantasma_Blinky.cambiarDireccion(_fantasma_Blinky.getDireccion());
			
		}
		if (r2.intersects(r4))
		{
			_fantasma_Blinky.cambiarDireccion(_fantasma_Blinky.getDireccion());
			_fantasma_Clyde.cambiarDireccion(_fantasma_Clyde.getDireccion());
			
		}
		
		if (r3.intersects(r4))
		{
			_fantasma_Pinky.cambiarDireccion(_fantasma_Pinky.getDireccion());
			_fantasma_Clyde.cambiarDireccion(_fantasma_Clyde.getDireccion());
			
		}

	}

	// MÉTODO PARA LA CARGA DEL MAPA.
	public void cargaMapa() {
		
		try {
			for (int columna = 0; columna < map.length; columna++) {
				for (int fila = 0; fila < map.length - 1; fila++) {

					if (map[columna][fila] == 0 || map[columna][fila] == -1) {
						mapa[columna][fila] = new objetosJuego();
					} else if (map[columna][fila] == 1) {
						mapa[columna][fila] = new muro_Obj(fila * 24,
								columna * 24);
					} else if (map[columna][fila] == 2) {
						mapa[columna][fila] = new punto_Obj(fila * 24,
								columna * 24);
						
					} else if (map[columna][fila] == 3) {
						mapa[columna][fila] = new superPunto_Obj(fila * 24,
								columna * 24);
					}

				}
			}
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}
	}

}
