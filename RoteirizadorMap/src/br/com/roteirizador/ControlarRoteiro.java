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

public class ControlarRoteiro {
	
	private Dijkstra disj = new Dijkstra();
	
	private NodesDao nodeDao = new NodesDao();
	
	public StringBuilder resultado = new StringBuilder();
	
	/**
	 * 
	 * Metodo responsavel por capturar os dados em base e mapear de uma maneira que sejam processado a malha
	 *
	 * @param origem
	 * @param destino
	 * @param autonomia
	 * @param valorCombustivel
	 * @return String
	 */
	@SuppressWarnings("finally")
	public String calcular(final String origem, final String destino, final double autonomia, final double valorCombustivel){
		try{
			Hashtable<String, Node> malha = new Hashtable<String, Node>();
			
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
			
			//setando valores para a origem
			disj.calcularCaminho((Node)malha.get(origem));			
			System.out.println("Custo para " + (Node)malha.get(destino) + ": " + ((Node)malha.get(destino)).distanciaMinima);
			List<Node> path = disj.buscarCaminhoCurtoPara((Node)malha.get(destino));
			
			resultado.append("<br/>Origem : " + origem);
			resultado.append("<br/>Destino : " + destino);
			resultado.append("<br/>Caminho : " + path);
			resultado.append("<br/>Distancia Total " + (Node)malha.get(destino) + ": " + ((Node)malha.get(destino)).distanciaMinima);
			resultado.append("<br/>Valor Litro combustivel : " + valorCombustivel);
			resultado.append("<br/>Valor Total Combustivel : " + (((Node)malha.get(destino)).distanciaMinima * valorCombustivel) / autonomia);
					
			return resultado.toString();
		}catch(Exception ex){
			System.out.println("Erro ao calcular a rota mais curta : " + ex.toString());			
		}
		
		return "Nao existe pontos para alcancar esta rota";
	}
	
	@SuppressWarnings("finally")
	public String cadastrarPontos(final String origem, final String destino, final double custo){
		
		try{
			if ((!origem.isEmpty())&&(!destino.isEmpty())){
				List<Origem> lstOrigem = nodeDao.findByNome(origem);
				Destino nodeDestino = new Destino(destino, custo);
							
				if (lstOrigem.size() > 0){		
					Origem objOrigem = lstOrigem.get(0);
					
					boolean existe = false;
					for (Destino d : objOrigem.getDestinos()){
						if (d.getNome().equalsIgnoreCase(nodeDestino.getNome())){
							existe = true;
						}
					}
					
					if (!existe){
						nodeDestino.setId_origem(objOrigem);
						objOrigem.getDestinos().add(nodeDestino);
						nodeDao.update(objOrigem);
					}else{
						return "Ponto destino ja consta na base de dados para esta origem";
					}
					
				}else{
					Origem novaOrigem = new Origem(origem);				
					List<Destino> lstDestinos = new ArrayList<Destino>();
					nodeDestino.setId_origem(novaOrigem);
					lstDestinos.add(nodeDestino);
					novaOrigem.setDestinos(lstDestinos);				
					nodeDao.save(novaOrigem);
				}
				
				
				//verificar se existe destino na tabela de origem
				List<Origem> lstNodesCorrecao = nodeDao.findByNome(destino);
				if (lstNodesCorrecao.size() == 0){
					Origem novaOrigem = new Origem(destino);
					nodeDao.save(novaOrigem);
				}
			}else{
				return "É necessario preencher todos os campos";
			}
		}catch(Exception ex){
			System.out.println("Erro ao cadastrar mais nodes : " + ex.toString());			
		}
		
		return "Insercao efetuada com sucesso !!";
	}
	
	public Dijkstra getDisj() {
		return disj;
	}

	public void setDisj(Dijkstra disj) {
		this.disj = disj;
	}

	public NodesDao getNodeDao() {
		return nodeDao;
	}

	public void setNodeDao(NodesDao nodeDao) {
		this.nodeDao = nodeDao;
	}
}
