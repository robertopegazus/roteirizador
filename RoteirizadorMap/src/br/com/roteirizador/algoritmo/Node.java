package br.com.roteirizador.algoritmo;

import java.util.List;

/**
 * @author roberley
 * 
 * Classe Node onde contem os atributos referentes ao Node que vai ser calculado
 *
 */
public class Node implements Comparable<Node> {
	public final String nome;
	public List<Malhas> adjacentes;
	public double distanciaMinima = Double.POSITIVE_INFINITY;
	public Node anterior;

	public Node(String argName) {
		nome = argName;
	}

	public String toString() {
		return nome;
	}

	public int compareTo(Node outro) {
		return Double.compare(distanciaMinima, outro.distanciaMinima);
	}
}
