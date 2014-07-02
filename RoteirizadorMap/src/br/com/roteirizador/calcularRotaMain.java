package br.com.roteirizador;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import br.com.roteirizador.algoritmo.Dijkstra;
import br.com.roteirizador.algoritmo.Malhas;
import br.com.roteirizador.algoritmo.Node;
import br.com.tutorial.classes.Destino;
import br.com.tutorial.classes.Origem;
import br.com.tutorial.pessoaDao.NodesDao;

public class calcularRotaMain {
	
	public static void main(String[] args) {		
		try{
			Hashtable<String, Node> malha = new Hashtable<String, Node>();
			
			NodesDao nodeDao = new NodesDao();
			List<Origem> lstOrigem = new ArrayList<Origem>();
			lstOrigem = nodeDao.findAll();
	
			for (Origem ori : lstOrigem){
				malha.put(ori.getNome(), new Node(ori.getNome()));
			}
	
			for (Origem ori : lstOrigem){
				Node node = malha.get(ori.getNome());
	
				List<Malhas> malhas = new ArrayList<Malhas>();
	
				for (Destino dest : ori.getDestinos()){
					if (malha.get(dest.getNome()) != null){
						malhas.add(new Malhas(malha.get(dest.getNome()), dest.getCusto()));
					}else{
						malhas.add(new Malhas(new Node(dest.getNome()), dest.getCusto()));
					}
				}
				node.adjacentes = malhas;
				malha.put(ori.getNome(), node);
			}
			
			String origem = "E";
			String destino = "A";
			String resultado = "";
			
			/*Dijkstra.calcularCaminho((Node)malha.get(origem));       //origem			
			System.out.println("Custo para " + (Node)malha.get(destino) + ": " + ((Node)malha.get(destino)).distanciaMinima);
			List<Node> path = Dijkstra.buscarCaminhoCurtoPara((Node)malha.get(destino));
			System.out.println("Caminho: " + path);
			resultado = "Custo Total " + (Node)malha.get(destino) + ": " + ((Node)malha.get(destino)).distanciaMinima;
			System.out.println(resultado);*/
		}catch(Exception ex){
			System.out.println(ex.toString());
		}
	}
}
