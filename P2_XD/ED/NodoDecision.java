package ED;

import java.util.function.Predicate;
/************************************************/
/* Nodo para el árbol de decisión.
/* Práctica de comunicación de aplicaciones.
/* Redes de Datos - Grado en Informática - URV - Curso 2018-2019
/* Autores: Íñigo Arriazu, Bernat Boscá, German Eizaguirre
/************************************************/
public class NodoDecision{
	
	private String descripcion;
	private Predicate <String> funcion;
	private Integer valor=-1;
	private NodoDecision fe, fd;

	/* Constructor 1: si es un nodo de decisión de tipo condición ( no es hoja del árbol ).
		Contiene un Predicate con la condición a partir de la cual pasará al nodo derecho o izquierdo.
	 */
	public NodoDecision(String descripcion, Predicate <String> funcion) {
		this.descripcion = descripcion;
		this.funcion = funcion;
		fe = null;
		fd = null;
	}

	/* Constructor 2: si es una hoja, se ha llegado al valor final de filtrado.
	 */
	public NodoDecision(String descripcion, int valor) {
		this.descripcion = descripcion;
		this.funcion = null;
		fe = null;
		fd = null;
		this.valor=valor;
	}

	public void setFe (NodoDecision fe) {
		this.fe = fe;
	}

	public void setFd (NodoDecision fd) {
		this.fd = fd;
	}

	/* Llama recursivamente a la función filtrar hasta llegar a una hoja que devuelva el valor de filtrado.
	 */
	public int filtrar (String frase) {

		if ((fe != null) && (fd != null)) {
			if (funcion.test(frase))
				return fd.filtrar(frase);
			else
				return fe.filtrar(frase);
		}
		else return valor;
	}

	public NodoDecision getFe() {
		return fe;
	}

	public NodoDecision getFd() {
		return fd;
	}

	public String toString(){
		String result=this.descripcion;
		if (this.fd!=null){
			result+="\nfd>"+fd.toString();
		}
		if (this.fe!=null){
			result+="\nfe>"+fe.toString();
		}
		return result;
	}
}
