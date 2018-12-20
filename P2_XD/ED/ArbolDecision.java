/************************************************/
/* Estructura de árbol de decisión.
/* Práctica de comunicación de aplicaciones.
/* Redes de Datos - Grado en Informática - URV - Curso 2018-2019
/* Autores: Íñigo Arriazu, Bernat Boscá, German Eizaguirre
/************************************************/

package ED;

/* Clase de gestión del árbol. Mediante ella se accede directamente al nodo
raíz del árbol.
 */
public class ArbolDecision  {

	/* nodo inicial del árbol de decisión.
	 */
	private NodoDecision raiz;
	
	public ArbolDecision(){
		raiz=null;
	}
	
	public ArbolDecision(NodoDecision nodo){
		raiz=nodo;
	}

	public void setRaiz(NodoDecision raiz){
		this.raiz=raiz;
	}

	public NodoDecision getRaiz(){
		return raiz;
	}

	public NodoDecision getHijoIzquierdo () {
		if (raiz!=null) return raiz.getFe();
		else return null;
	}

	public NodoDecision getHijoDerecho() {
		if (raiz!=null) return raiz.getFd();
		else return null;
	}

	public boolean estaVacio() {
		return (raiz==null);
	}

	public int filtrar (String frase){
		return raiz.filtrar(frase);
	}

	public String toString(){
		return raiz.toString();
	}

	
}
