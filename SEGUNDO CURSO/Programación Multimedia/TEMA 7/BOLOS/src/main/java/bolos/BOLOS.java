package bolos;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.TextureKey;
import com.jme3.audio.AudioNode;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Cylinder;
import com.jme3.scene.shape.Sphere;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;

public class BOLOS extends SimpleApplication {

    public static void main(String[] args) {
        BOLOS app = new BOLOS();
        app.start();
    }

    private BulletAppState bulletAppState;
    private Material bola_mat, piedras_mat;

    // Variables para controlar la fuerza
    private boolean isSpacePressed = false; // Para detectar si la barra espaciadora está presionada
    private float forceAmount = 0; // Variable para controlar la cantidad de fuerza
    private float maxForce = 20f; // La fuerza máxima que puede alcanzar la bola
    private Vector3f initialVelocity = new Vector3f(0, 0, 0); // Velocidad inicial de la bola
    private RigidBodyControl bola_fis;
    private AudioNode musicaFondo;
    private AudioNode sonidoTiro;

    @Override
    public void simpleInitApp() {
        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);
        iniciarMusica();
cargarSonidos();
        crearMateriales();
        crearSuelo();
        crearPared();
        crearLuz();
        crearBolos();  // Aquí se crea la disposición de los bolos
        cam.setLocation(new Vector3f(0, 4f, 6f));
        cam.lookAt(new Vector3f(0, 2, 0), Vector3f.UNIT_Y);
        viewPort.setBackgroundColor(new ColorRGBA(0f, 0f, 0.2f, 0));

        hazBola();

        // Configuración de la detección de teclas
        inputManager.addMapping("Space", new KeyTrigger(KeyInput.KEY_SPACE));
inputManager.addMapping("Disparar", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addListener(actionListener, "Space","Disparar");
    }

   private ActionListener actionListener = new ActionListener() {
    @Override
    public void onAction(String name, boolean isPressed, float tpf) {
        if (name.equals("Space")) {
            if (isPressed) {
                // La barra espaciadora ha sido presionada
                if (!isSpacePressed) {
                    isSpacePressed = true; // Iniciar carga de fuerza
                }
            } else {
                // La barra espaciadora ha sido soltada
                if (isSpacePressed) {
                    if (sonidoTiro != null) { // Evita NullPointerException
                        sonidoTiro.playInstance();
                    } else {
                        System.err.println("¡Error! sonidoTiro es null.");
                    }
                    lanzarBola();
                    isSpacePressed = false; // Resetear la presión
                }
            }
        }
        
        // Aquí agregamos la lógica para el botón izquierdo del ratón
        if (name.equals("Disparar")) {
            if (isPressed) {
                // Botón izquierdo del ratón presionado
                if (!isSpacePressed) { // Asegurarse de que la bola no se haya disparado antes
                    isSpacePressed = true; // Iniciar carga de fuerza
                }
            } else {
                // Botón izquierdo del ratón liberado
                if (isSpacePressed) {
                    if (sonidoTiro != null) { // Evita NullPointerException
                        sonidoTiro.playInstance();
                    } else {
                        System.err.println("¡Error! sonidoTiro es null.");
                    }
                    lanzarBola();
                    isSpacePressed = false; // Resetear la presión
                }
            }
        }
    }
};


    private void lanzarBola() {
        if (forceAmount > 0) {
            Vector3f direction = cam.getDirection().normalize(); // Dirección hacia la que está mirando la cámara
            Vector3f velocity = direction.mult(forceAmount); // Aplicar la fuerza en esa dirección
            bola_fis.setLinearVelocity(velocity); // Asigna la velocidad a la bola
            forceAmount = 0; // Reinicia la fuerza una vez se ha lanzado la bola
        }
    }

    private void crearSuelo() {
        Box suelo = new Box(5f, 0.1f, 10f);
        suelo.scaleTextureCoordinates(new Vector2f(6, 3));

        Geometry suelo_geo = new Geometry("Piso", suelo);
        suelo_geo.setMaterial(piedras_mat);
        suelo_geo.setLocalTranslation(0, -0.1f, 0);
        rootNode.attachChild(suelo_geo);

        RigidBodyControl suelo_fis = new RigidBodyControl(0.0f);
        suelo_geo.addControl(suelo_fis);
        bulletAppState.getPhysicsSpace().add(suelo_fis);
    }

    private void crearPared() {
        Box pared = new Box(5f, 0.1f, 10f);
        pared.scaleTextureCoordinates(new Vector2f(6, 3));

        Geometry pared_geo = new Geometry("Pared", pared);
        pared_geo.setMaterial(piedras_mat);
        pared_geo.setLocalTranslation(0, -0.1f, -10);
        pared_geo.rotate((float) Math.PI / 2.0f, 0f, 0);
        rootNode.attachChild(pared_geo);

        RigidBodyControl pared_fis = new RigidBodyControl(0.0f);
        pared_geo.addControl(pared_fis);
        bulletAppState.getPhysicsSpace().add(pared_fis);
    }

    private void crearLuz() {
        DirectionalLight luz = new DirectionalLight();
        luz.setColor(ColorRGBA.White.mult(0.8f));
        luz.setDirection(new Vector3f(-1, -1, -1).normalizeLocal());
        rootNode.addLight(luz);
    }

    private void crearMateriales() {
        piedras_mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Texture textura = assetManager.loadTexture("Textures/Terrain/Pond/Pond.jpg");
        textura.setMinFilter(Texture.MinFilter.BilinearNearestMipMap); // Usamos este filtro para habilitar mipmaps
        textura.setMagFilter(Texture.MagFilter.Bilinear); // Configuración de filtro de magnificación
        textura.setWrap(WrapMode.Repeat);
        piedras_mat.setTexture("ColorMap", textura);

        bola_mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        bola_mat.setColor("Diffuse", ColorRGBA.Cyan);  // Cambiar 'm_Diffuse' a 'Diffuse'
        bola_mat.setColor("Ambient", ColorRGBA.Cyan);  // Cambiar 'm_Ambient' a 'Ambient'
        bola_mat.setColor("Specular", ColorRGBA.White); // Cambiar 'm_Specular' a 'Specular'
        bola_mat.setFloat("Shininess", 1);  // 'Shininess' sigue siendo válido
    }

    public void hazBola() {
        Sphere esfera = new Sphere(32, 32, 0.4f);
        Geometry bola_geo = new Geometry("bola", esfera);
bola_geo.setShadowMode(RenderQueue.ShadowMode.CastAndReceive);
        bola_geo.setMaterial(bola_mat);
        rootNode.attachChild(bola_geo);
        bola_geo.setLocalTranslation(cam.getLocation());

        bola_fis = new RigidBodyControl(1f);
        bola_geo.addControl(bola_fis);
        bulletAppState.getPhysicsSpace().add(bola_fis);
        bola_fis.setLinearVelocity(cam.getDirection().mult(8));
        bola_fis.setLinearVelocity(Vector3f.ZERO);
    }

    @Override
    public void simpleUpdate(float tpf) {
        // Actualizamos la fuerza si la barra espaciadora está presionada
        if (isSpacePressed) {
            // Aumentar la fuerza mientras la barra espaciadora esté presionada
            forceAmount = Math.min(forceAmount + tpf * 10, maxForce); // Aumenta la fuerza a una velocidad constante, limitada por maxForce
        }
    }

    private void crearBolos() {
        Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        mat.setColor("Diffuse", ColorRGBA.White);
        mat.setColor("Specular", ColorRGBA.White);
        mat.setBoolean("UseMaterialColors", true);

        float radioBase = 0.3f;
        float altura = 1.2f;
        int filas = 4;
        int bolosPorFila = 1;
        float espaciado = 0.6f; // Espacio entre los bolos
        float zInicial = -4f; // Posición inicial de la última fila

        for (int fila = 0; fila < filas; fila++) {
            for (int i = 0; i < bolosPorFila; i++) {
                // Crear el cilindro para el bolo, pero rotarlo desde el principio
                Cylinder mesh = new Cylinder(16, 16, radioBase, altura, true);

                // Crear geometría para el bolo
                Geometry bolo = new Geometry("Bolo", mesh);
bolo.setShadowMode(RenderQueue.ShadowMode.CastAndReceive);
                bolo.setMaterial(mat);

                // Posicionar el bolo en la escena
                float x = (i - (bolosPorFila - 1) / 2.0f) * espaciado;
                float z = zInicial - fila * espaciado;
                bolo.setLocalTranslation(new Vector3f(x, altura / 2.0f, z)); // En el suelo

                // Añadir control físico para el bolo
                RigidBodyControl boloFis = new RigidBodyControl(1f);
                bolo.addControl(boloFis);
                bulletAppState.getPhysicsSpace().add(boloFis);

                // Fijar la ubicación física en el suelo
                boloFis.setPhysicsLocation(new Vector3f(x, altura / 2.0f, z));
                boloFis.setAngularVelocity(Vector3f.ZERO);
                boloFis.setLinearVelocity(Vector3f.ZERO);

                // Rotar el bolo para estar completamente de pie
                bolo.setLocalRotation(new Quaternion().fromAngleAxis((float) Math.PI / 2, Vector3f.UNIT_X)); // Rotar 90 grados sobre el eje X

                rootNode.attachChild(bolo);
            }
            bolosPorFila++;
        }
    }

    private void iniciarMusica() {
        musicaFondo = new AudioNode(assetManager, "Sounds/FONDO.ogg", false);
        musicaFondo.setLooping(true); // Para que se reproduzca en bucle
        musicaFondo.setPositional(false); // No depende de la posición del jugador
        musicaFondo.setVolume(0.5f); // Ajusta el volumen (0.0 a 1.0)
        rootNode.attachChild(musicaFondo);
        musicaFondo.play();
    }

   private void cargarSonidos() {
    try {
        sonidoTiro = new AudioNode(assetManager, "Sounds/Tiro.ogg", false);
        sonidoTiro.setPositional(false);
        sonidoTiro.setVolume(1.0f);
        rootNode.attachChild(sonidoTiro);
    } catch (Exception e) {
        System.err.println("Error cargando el sonido: " + e.getMessage());
    }
}
    @Override
    public void simpleRender(RenderManager rm) {
        // Se puede agregar cualquier renderizado adicional aquí
    }
}
