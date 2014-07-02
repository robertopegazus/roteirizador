package br.com.roteirizador.algoritmo;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author roberley
 * 
 * Classe responsavel por efetuar o calculo utilizando o algoritmo dijkstra.
 *
 */
public class Dijkstra {

	/**
	 * metodo para calcular o caminho de cada node.
	 * 
	 * @param node
	 */
	public void calcularCaminho(Node node) {
		node.distanciaMinima = 0.;
		PriorityQueue<Node> lstNode = new PriorityQueue<Node>();
		lstNode.add(node);
		
		//percorendo a lista de nodes
		while (!lstNode.isEmpty()) {
			Node u = lstNode.poll();

			// percorrendo as adjacentes de cada vertice
			for (Malhas e : u.adjacentes) {
				Node v = e.caminho;
				double custo = e.custo;
				double distanciaACalcular = u.distanciaMinima + custo;
				if (distanciaACalcular < v.distanciaMinima) {
					lstNode.remove(v);
					v.distanciaMinima = distanciaACalcular;
					v.anterior = u;
					lstNode.add(v);
				}
			}
		}
	}
	/**
	 * Metodo responsavel por buscar o caminho mais curto de um determinado node que for recebido
	 * @param node
	 * @return List<Node>
	 */
	public List<Node> buscarCaminhoCurtoPara(Node node) {
		List<Node> lstCaminhoNode = new ArrayList<Node>();
		for (Node vertice = node; vertice != null; vertice = vertice.anterior)
			lstCaminhoNode.add(vertice);
		Collections.reverse(lstCaminhoNode);
		return lstCaminhoNode;
	}
}