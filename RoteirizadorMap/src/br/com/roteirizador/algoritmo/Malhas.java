package br.com.roteirizador.algoritmo;

/**
 * @author roberley
 * 
 * Classe malhas que vai ser utilizado para preencher as vertices do node.
 *
 */
public class Malhas {
	public final Node caminho;
	public final double custo;

	public Malhas(Node varCaminho, double varCusto) {
		caminho = varCaminho;
		custo = varCusto;
	}
}
