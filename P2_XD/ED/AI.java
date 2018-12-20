/************************************************/
/* Clase de encapsulación de inteligencia artificial.
/* Práctica de comunicación de aplicaciones.
/* Redes de Datos - Grado en Informática - URV - Curso 2018-2019
/* Autores: Íñigo Arriazu, Bernat Boscá, German Eizaguirre
/************************************************/
package ED;

import java.text.Normalizer;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class AI {


	private static int NUM_FRASES_RANDOM=6;
	private static int NUM_NOMBRES=7;
	private String []  nombres = {"Antonio", "Siri", "Santiago", "Maria", "Luis", "Bernardo", "Jose"};
    private String nombre;
    private  List<Tema> frases;
    private  ArbolDecision arbol;
    private List <Integer> last_decision;

    /* constructor de la inletigencia artificial, le asigna un nombre aleatorio
     */
    public AI(){
        nombre=getNombreRandom();
        frases= new LinkedList<Tema>();
        arbol= new ArbolDecision();
        last_decision= new LinkedList<Integer>();
    }

    /* Método para inicializar la lista de temas con frases y el árbol de decisión mediante
    el méotodo de construcción del árbol
     */
    public void inicializar_sistema(){

    	List<String> aux= new LinkedList<String>();

    	/* MATES */
    	aux.add("Aupa Aristoteles.");
		aux.add("Tu expresion es aurea.");
		aux.add("Aqui esta aumentando la temperatura.");
		aux.add("Disfrutemos de los operandos.");
		aux.add("Me gustan los polinomios, pero solo hasta cierto grado.");
		aux.add("2P2A+A2y+K2=KK");
        frases.add(new Tema("Matematicas",
                "Eres mas de pares o impares?",
                "Multiplicate por cero.",
                "Creo que Fibonnacci estaba sobrevalorado.",
                "La raiz cuadrada de pi al cuadrado.",
                aux));

        /* BIOLOGIA */
        aux.clear();
		aux.add("Viva la taxonomia.");
		aux.add("Creo que podemos crear unicornios.");
		aux.add("Vamos a buscar amebas.");
		aux.add("Diseñe un liquen.");
		aux.add("Cual es el animal que tiene entre 3 y 4 ojos? El piojo.");
		aux.add("Puedo ser un perro papa?");
        frases.add(new Tema("Biologia",
                "Quieres aprender algo sobre mitocondrias?",
                "Insertando virus.",
                "Me parece que la biparticion es un exito evolutivo.",
                "El citoesqueleto modela la estructura de la celula eucariota.",
                aux));
    
    	/* LENGUA */
    	aux.clear();
		aux.add("Libros para todos.");
		aux.add("Paginas perdidas.");
		aux.add("Historias ya contadas.");
		aux.add("Ser o no ser, ser o ser, ser siendo... it is very dificult todo esto.");
		aux.add("Quieres que te prepare un día mi famosa lengua en salsa?");
		aux.add("Tenemos que llegar a un acuerdo ya. O hablamos con seriedad o me voy.");
    	frases.add(new Tema("Lengua",
    			"Que idiomas puedes hablar?",
    			"Tienes una lengua bifida.",
    			"Los cafes literarios son magnificos.",
    			"Un buen lenguaje es una muestra de un elevado intlecto.",
    			aux));
    	
    	/* HISTORIA */
    	aux.clear();
		aux.add("A las armas.");
		aux.add("Flechas rojas y escudos rotos.");
		aux.add("A buenas horas mangas verdes.");
		aux.add("A firmar tratados.");
		aux.add("No veía a alguien tan tonto desde 1904.");
		aux.add("Dicen que ese tal Napoleón conquistó Bonaparte de Europa");

    	frases.add(new Tema("Historia",
    			"Quieres hablar sobre las invasiones barbaras?",
    			"A la hogera.",
    			"Conocer nuestro pasado, nuestra historia resulta en un mejor conocimiento del futuro.",
    			"La historia la escribe el bando vencedor.",
    			aux));


    	/* FILOSOFIA */
    	aux.clear();
		aux.add("Dios ha muerto.");
		aux.add("Que moral de esclavo.");
		aux.add("Nihilismo para desayunar.");
		aux.add("Lo llamare Phill o Sophia.");
		aux.add("Si tan comunista eres quítale la contraseña al WiFi");
		aux.add("Vente al Pastafarismo!");
    	frases.add(new Tema("Filosofia",
    			"¿Como seria hoy el mundo sin los griegos?",
    			"Toma cicuta.",
    			"Los filosofos mas vigentes e incomprendidos de la segunda mitad del siglo XX fueron Derrida y Foucault.",
    			"El hacer preguntas derroto a los sofistas.",
    			aux));
    	
    	/* DEPORTE */
    	aux.clear();
		aux.add("Serie al fallo.");
		aux.add("Touche.");
		aux.add("Ups creo que suficiente por hoy.");
		aux.add("Mmmm... Salida antes de tiempo.");
		aux.add("Soy informático. No he hecho deporte en mi vida hulio.");
		aux.add("Después nos vamos a hacer footing al Francolí");
    	frases.add(new Tema("Deporte",
    			"Cual te parece el deporte mas peligroso?",
    			"A jugar.",
    			"Creo que el mejor deportista de todos los tiempos es Michael Phelps.",
    			"El entrenamiento anaerobico es la base de los deportes intensos.",
    			aux));
    	
    	/* ACTUALIDAD */
    	aux.clear();
		aux.add("Plot twist.");
		aux.add("Quiero diamantes.");
		aux.add("Amo a Beberly.");
		aux.add("Apoyos a Selena.");
		aux.add("Lleguemos a un trato. Aquí no hablaremos de OT, vale?");
		aux.add("No vengas pensando que etso es España Directo.");
    	frases.add(new Tema("Actualidad",
    			"Sigues de cerca la vida de las kardashian?",
    			"Que poca clase.",
    			"Para la fiesta de halloween iremos a Madison Square Garden.",
    			"Actualmente hay preocupacion tras la ultima accion de kanye west.",
    			aux));
    	
    	/* INVENCIONES */
    	aux.clear();
		aux.add("Eureca.");
		aux.add("Elemental querido Watson.");
		aux.add("y no se como, pero exploto.");
		aux.add("Perdon por el retraso.");
		aux.add("Venga va, invéntate otra cosa");
		aux.add("No me dejes aún. tengo cosas que ofrecerte!");
    	frases.add(new Tema("Invenciones",
    			"Quisieras profundizar sobre los nuevos descubrimientos acerca de la cura del VIH?",
    			"Creo que por aqui faltan luces.",
    			"El mejor momento para redescubrir cosas es por la mañana.",
    			"Actualmente las nuevas lineas de investigacion se centran en refinar y combinar ideas ya existentes en lugar de crear nuevas.",
    			aux));

    	// finalmente genera el árbol de decisión
                generar_filtrador();
    }

    /* Método para generar el árbol de decisión: le asigna los nodos necesarios
    según el esquema de decisión que hemos especificado
     */
    public void generar_filtrador(){

        arbol.setRaiz(new NodoDecision("Detectar interrogacion", (x -> (x.charAt(x.length()-1)=='?'))));
        arbol.getRaiz().setFe(new NodoDecision("Detectar exclamación", (x -> (x.charAt(x.length()-1)=='!'))));
        arbol.getRaiz().getFe().setFe(new NodoDecision("Final: no pregunta no exclamación", 1));
        arbol.getRaiz().getFe().setFd(new NodoDecision("Final: tiene exclamación", 2));
        arbol.getRaiz().setFd(new NodoDecision("Detectar pregunta", (x ->
                (((x.startsWith("¿cual"))||(x.startsWith("cual"))||(x.startsWith("¿que"))||(x.startsWith("¿como"))||
                        (x.startsWith("como"))||(x.startsWith("¿cuando"))||(x.startsWith("cuando"))||(x.startsWith("que")))
                        &&(!((x.contains("crees"))||(x.contains("opinas"))||(x.contains("piensas"))||(x.contains("dices"))))))));
        arbol.getRaiz().getFd().setFd(new NodoDecision("Final: pregunta", 4));
        arbol.getRaiz().getFd().setFe(new NodoDecision("Detectar opinión", (x ->
                ((x.contains("crees"))||(x.contains("opinas")))||(x.contains("piensas"))||(x.contains("dices")))));
        arbol.getRaiz().getFd().getFe().setFd(new NodoDecision("Final: opinión", 3));
        arbol.getRaiz().getFd().getFe().setFe(new NodoDecision("Final: random", 5));
    }

	/* Función para detectar si el registro de temas contiene un tema pasado como parámetro.
	Si lo tiene, devuelve su posición en la lista de "temas".
	 */
    public int contiene_tema(String tema){
        Boolean found=false;
        int counter=0;
        String tema_guardado="";
        while ((found==false)&&(counter<frases.size())){
        	tema_guardado=normalizar_string(frases.get(counter).getNombre_tema());
            if (tema_guardado.equals(tema)) found=true;
            else counter++;
        }
        if (found==true) {
            return counter;
        }
        else return -1;
    }

    /* método que devuelve el tema en la posición de la lista indicada por parámetro
     */
    public Tema getTema(int opcion){
        return frases.get(opcion);
    }

    public String normalizar_string(String frase){
        frase=frase.toLowerCase();
        frase = Normalizer.normalize(frase, Normalizer.Form.NFD);
        frase = frase.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return frase;
    }

    /* Método para realizar el filtrado mediante el árbol de decisión. Devuelve el índice de la siguiente
    frase que devoolverá el sistema.
     */
    public int filtrar(String frase){
        int resultado= arbol.filtrar(frase);
        /* comprueba que la última frase no sea la misma que la actual */
        if (last_decision.contains(resultado)){
        	resultado=5;
		}
		else
			if (resultado!=5) last_decision.add(resultado);
        return resultado;
    }

    /* Limpia el índice de la última frase devuelta.
     */
    public void limpiar_registro(){
    	last_decision.clear();
	}

	/* Devuelve el nombre de la isntancia actual de la IA.
	 */
	public String getNombre() {
		return nombre;
	}

	public  static int NUM_FRASES_RANDOM(){
    	return NUM_FRASES_RANDOM;
	}

	/* Método para obtener una nombre aleatorio de la lista de nombres guardados para la IA.
	 */
	public String getNombreRandom(){
		Random rand= new Random();
		String resultado=nombres[rand.nextInt(NUM_NOMBRES)];
		return resultado;
	}
}