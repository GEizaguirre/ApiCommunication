/************************************************/
/* Estructura de tema con frases sobre el mismo.
/* Práctica de comunicación de aplicaciones.
/* Redes de Datos - Grado en Informática - URV - Curso 2018-2019
/* Autores: Íñigo Arriazu, Bernat Boscá, German Eizaguirre
/************************************************/

package ED;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/* Clase de estructura "tema". Contiene un tema y sus frases asociadas */
public class Tema {

    private String nombre_tema;
    private String frase_pregunta, frase_exclamacion, frase_opinion, frase_respuesta;
    private List <String> frases_random;

    /* Constructor. Recibe el nombre de tema, cuatro frases de carácter predefinido y una lista de frases aleatorias
     */
    public Tema(String nombre_tema, String frase_pregunta, String frase_exclamacion, String frase_opinion, String frase_respuesta,
    List<String> frases_out) {
        frases_random=new LinkedList<String>();
        /* cuatro frases definidas*/
        this.nombre_tema = nombre_tema;
        this.frase_pregunta = frase_pregunta;
        this.frase_exclamacion = frase_exclamacion;
        this.frase_opinion = frase_opinion;
        this.frase_respuesta = frase_respuesta;
        /* cuatro frases random */
        this.frases_random.addAll(frases_out);
    }


    /* Devuelve una frase de las registradas según un índice pasado como parámetro.
     */
    public String getFrase (int numero){
        switch (numero){
            case 1:
                return frase_pregunta;
            case 2:
                return frase_exclamacion;
            case 3:
                return frase_opinion;
            case 4:

                return frase_respuesta;

            default:
                //get random

                return getFraseRandom ();
        }
    }

    /* Devuelve una frase aleatoria de la lista de frases aleatorias.
     */
    String getFraseRandom (){

        Random rand = new Random();
        int  indice = rand.nextInt(AI.NUM_FRASES_RANDOM()) + 1;
        indice--;
        return frases_random.get(indice);
    }

    public String getNombre_tema() {
        return nombre_tema;
    }
}