package tarea;

import java.io.File;
import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.*;
import org.newdawn.slick.Input.*;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.tiled.*;
import java.util.Random;

public class Tarea extends BasicGame {

    // Tilemap
    private TiledMap mapa;
    private int mapaAncho, mapaAlto;

    // Estado del jugador
    private float jugadorX, jugadorY;
    private SpriteSheet cuadros;
    private SpriteSheet cuadrosRobot;
    private Animation jugador;
    private Animation jugadorArriba;
    private Animation jugadorDerecha;
    private Animation jugadorAbajo;
    private Animation jugadorIzquierda;
    private boolean jugadorVivo;

    // Estado de los robots
    private Animation[] robot;
    private Animation robotArriba;
    private Animation robotDerecha;
    private Animation robotAbajo;
    private Animation robotIzquierda;
    private float[] robotX, robotY;
    private boolean[] robotVivo;
    private int numeroRobotsVivos;

    // Velocidad de los robots
    private float[] robotVelocidad;

    // Fuente
    private UnicodeFont fuente;

    // Tiempo y puntos
    private long tiempo;
    private int puntos;

    // Generador aleatorio
    private Random random;

    // Variables de control de fin de partida
    private boolean finDePartida;
    private boolean reiniciarPartida;
    private Sound explosionSound;
    private Music backgroundMusic;
    private Image explosionImagen;
    private float explosionX, explosionY;
    private boolean explosionActiva;
    private int explosionTiempo;

    public Tarea(String name) {
        super(name);
        System.out.println("Juego creado: " + name);
    }

    public static void main(String[] args) {
        System.setProperty("org.lwjgl.librarypath", new File(new File(System.getProperty("user.dir"), "native"),
                LWJGLUtil.getPlatformName()).getAbsolutePath());
        System.setProperty("net.java.games.input.librarypath", System.getProperty("org.lwjgl.librarypath"));

        try {
            System.out.println("Iniciando el juego...");
            AppGameContainer container = new AppGameContainer(new Tarea("PMDM06 - Tarea"));
            container.setDisplayMode(800, 600, true); // Ventana de 800x600
            container.setMaximumLogicUpdateInterval(144);
            container.setTargetFrameRate(0);
            container.setVSync(true);
            container.setShowFPS(false);
            container.setUpdateOnlyWhenVisible(false);
            container.start();
        } catch (SlickException e) {
            System.out.println("Error al iniciar el juego: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        System.out.println("Inicializando el juego...");
        explosionImagen = new SpriteSheet("data/Explosion.png",8,8);
        explosionActiva = false;
        explosionTiempo = 0;
        // Cargar mapa
        try {
            mapa = new TiledMap("data/mapa.tmx", "data");
            mapaAncho = mapa.getWidth() * mapa.getTileWidth();
            mapaAlto = mapa.getHeight() * mapa.getTileHeight();
            System.out.println("Mapa cargado correctamente.");
        } catch (Exception e) {
            System.out.println("Error cargando el mapa: " + e.getMessage());
            e.printStackTrace();
        }

        // Cargar spritesheets
        try {
            cuadros = new SpriteSheet("data/heroe.png", 24, 32);
            cuadrosRobot = new SpriteSheet("data/robot.png", 24, 32);
            System.out.println("Spritesheets cargados correctamente.");
        } catch (Exception e) {
            System.out.println("Error cargando spritesheets: " + e.getMessage());
            e.printStackTrace();
        }

        // Animaciones del jugador
        jugadorArriba = new Animation(cuadros, 0, 0, 2, 0, true, 150, false);
        jugadorDerecha = new Animation(cuadros, 0, 1, 2, 1, true, 150, false);
        jugadorAbajo = new Animation(cuadros, 0, 2, 2, 2, true, 150, false);
        jugadorIzquierda = new Animation(cuadros, 0, 3, 2, 3, true, 150, false);
        jugador = jugadorAbajo;
        System.out.println("Animaciones del jugador cargadas.");

        // Animaciones del robot
        robotArriba = new Animation(cuadrosRobot, 0, 0, 2, 0, true, 150, true);
        robotDerecha = new Animation(cuadrosRobot, 0, 1, 2, 1, true, 150, true);
        robotAbajo = new Animation(cuadrosRobot, 0, 2, 2, 2, true, 150, true);
        robotIzquierda = new Animation(cuadrosRobot, 0, 3, 2, 3, true, 150, true);
        System.out.println("Animaciones de los robots cargadas.");

        // Estado inicial del jugador
        jugadorX = 320;
        jugadorY = 240;
        jugadorVivo = true;
        finDePartida = false;
        reiniciarPartida = false;
        System.out.println("Estado inicial del jugador configurado.");

        // Estado inicial de los robots
        robot = new Animation[4];
        robotX = new float[4];
        robotY = new float[4];
        robotVivo = new boolean[4];
        robotVelocidad = new float[4];
        numeroRobotsVivos = 4;

        robot[0] = robotDerecha;
        robot[1] = robotIzquierda;
        robot[2] = robotDerecha;
        robot[3] = robotIzquierda;

        robotX[0] = 20;
        robotX[1] = 596;
        robotX[2] = 20;
        robotX[3] = 596;

        robotY[0] = 84;
        robotY[1] = 84;
        robotY[2] = 428;
        robotY[3] = 428;

        for (int i = 0; i < 4; i++) {
            robotVivo[i] = true;
            robotVelocidad[i] = 0.1f; // Robots inician un poco más lentos
        }
        System.out.println("Estado inicial de los robots configurado.");

        // Cargar fuente
        try {
            fuente = new UnicodeFont("data/tuffy.ttf", 28, false, false);
            fuente.addAsciiGlyphs();
            fuente.addGlyphs("áéíóúÁÉÍÓÚñÑ¡¿");
            fuente.getEffects().add(new ColorEffect(new java.awt.Color(255, 255, 255)));
            fuente.loadGlyphs();
            System.out.println("Fuente cargada correctamente.");
        } catch (Exception e) {
            System.out.println("Error cargando la fuente: " + e.getMessage());
            e.printStackTrace();
        }

        // Inicialización de tiempo, puntos y aleatorio
        tiempo = System.currentTimeMillis();
        puntos = 0;
        random = new Random();
        backgroundMusic = new Music("data/MatrixMusic.ogg");
        backgroundMusic.loop();
        System.out.println("Inicialización completa.");
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        Input entrada = gc.getInput();

        if (finDePartida) {
            // Opción de reiniciar (Y) o salir (N) al finalizar la partida
            if (entrada.isKeyDown(Input.KEY_Y)) {
                reiniciarPartida = true;
                finDePartida = false;
                init(gc);
            } else if (entrada.isKeyDown(Input.KEY_N)) {
                gc.exit();
            }
            return;
        }

        if (!jugadorVivo) {
            finDePartida = true;
            return;
        }

        // Movimiento del jugador
        if (entrada.isKeyDown(Input.KEY_DOWN)) {
            jugadorY += delta * 0.2f;
            if (jugadorY > mapaAlto - 32) {
                jugadorY = mapaAlto - 32;
            }
            jugador = jugadorAbajo;
        }
        if (entrada.isKeyDown(Input.KEY_UP)) {
            jugadorY -= delta * 0.2f;
            if (jugadorY < 0) {
                jugadorY = 0;
            }
            jugador = jugadorArriba;
        }
        if (entrada.isKeyDown(Input.KEY_RIGHT)) {
            jugadorX += delta * 0.2f;
            if (jugadorX > mapaAncho - 24) {
                jugadorX = mapaAncho - 24;
            }
            jugador = jugadorDerecha;
        }
        if (entrada.isKeyDown(Input.KEY_LEFT)) {
            jugadorX -= delta * 0.2f;
            if (jugadorX < 0) {
                jugadorX = 0;
            }
            jugador = jugadorIzquierda;
        }

        // Mover robots y detectar colisión con el jugador
        for (int i = 0; i < 4; i++) {
            if (robotVivo[i]) {
                moverRobot(i, delta);
                if (Math.abs(jugadorX - robotX[i]) < 16 && Math.abs(jugadorY - robotY[i]) < 16) {
                    // El jugador es alcanzado: fin de partida
                    jugadorVivo = false;
                    System.out.println("Matrix has you...");
                    break;
                }
            }
        }

        // Comprobar colisiones entre robots
        // Si dos robots chocan, ambos desaparecen y se generan 4 nuevos robots en posiciones aleatorias
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                if (robotVivo[i] && robotVivo[j]
                        && Math.abs(robotX[i] - robotX[j]) < 16 && Math.abs(robotY[i] - robotY[j]) < 16) {
                    robotVivo[i] = false;
                    robotVivo[j] = false;
                    SoundEffect.playExplosionSound();

                    puntos += 30;
                    System.out.println("¡Robots chocaron! Puntos: " + puntos);
                    explosionX = (robotX[i] + robotX[j]) / 2; // Centro de la colisión
                    explosionY = (robotY[i] + robotY[j]) / 2;
                    explosionActiva = true;
                    explosionTiempo = 1000; // Duración de la explosión (en milisegundos)

                    // Generar 4 nuevos robots en posiciones aleatorias
                    for (int n = 0; n < 4; n++) {
                        robotX[n] = random.nextInt(mapaAncho - 24);
                        robotY[n] = random.nextInt(mapaAlto - 32);
                        robotVelocidad[n] = 0.1f;
                        robotVivo[n] = true;
                    }
                    break;
                }
            }
        }

        // Actualizar puntos por tiempo (por ejemplo, 1 punto por segundo)
        if (System.currentTimeMillis() - tiempo >= 1000) {
            puntos++;
            tiempo = System.currentTimeMillis();
        }
    }

    private void moverRobot(int i, int delta) {
        // Movimiento simple: el robot se mueve hacia el jugador
        if (robotX[i] < jugadorX) {
            robotX[i] += delta * robotVelocidad[i];
            robot[i] = robotDerecha;
        } else if (robotX[i] > jugadorX) {
            robotX[i] -= delta * robotVelocidad[i];
            robot[i] = robotIzquierda;
        }
        if (robotY[i] < jugadorY) {
            robotY[i] += delta * robotVelocidad[i];
            robot[i] = robotAbajo;
        } else if (robotY[i] > jugadorY) {
            robotY[i] -= delta * robotVelocidad[i];
            robot[i] = robotArriba;
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        // Dibujar fondo (mapa)
        mapa.render(0, 0);

        // Dibujar robots
        for (int i = 0; i < 4; i++) {
            if (robotVivo[i]) {
                robot[i].draw(robotX[i], robotY[i]);
            }
        }

        // Dibujar jugador
        if (jugadorVivo) {
            jugador.draw(jugadorX, jugadorY);
        }

        // Mostrar puntuación
        g.setFont(fuente);
        g.setColor(org.newdawn.slick.Color.white);
        g.drawString("Puntos: " + puntos, 10, 10);

        // Si el jugador ha sido alcanzado, mostrar mensaje en rectángulo (estilo Matrix)
        if (!jugadorVivo) {
            int rectX = 200, rectY = 250, rectAncho = 400, rectAlto = 100;
            // Fondo negro
            g.setColor(org.newdawn.slick.Color.black);
            g.fillRect(rectX, rectY, rectAncho, rectAlto);
            // Borde verde
            g.setColor(new org.newdawn.slick.Color(0, 255, 0));
            g.drawRect(rectX, rectY, rectAncho, rectAlto);
            // Texto en verde
            g.drawString("Matrix has you...", rectX + 50, rectY + 30);
            g.drawString("Presiona 'Y' para reiniciar o 'N' para salir", rectX + 20, rectY + 60);
        }
    }
}
