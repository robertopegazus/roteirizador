package br.com.roteirizador;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("calcularRota")
public class calcularRota {
		
	private ControlarRoteiro controleRoteiro = new ControlarRoteiro();
		
	@GET
	@Path("{estado}/{origem}/{destino}/{autonomia}/{valorcombustivel}")
	@Produces("text/html")
	public String consultaRota(@PathParam("estado") String estado, @PathParam("origem") String origem, @PathParam("destino") String destino, @PathParam("autonomia") double autonomia, @PathParam("valorcombustivel") double valorcombustivel) {		
		String resultado = controleRoteiro.calcular(origem, destino, autonomia, valorcombustivel);		
		return resultado;
	}
	
	@GET
	@Path("{estado}/{origem}/{destino}/{custo}")
	@Produces("text/html")
	public String adicionarMalha(@PathParam("estado") String estado, @PathParam("origem") String origem, @PathParam("destino") String destino, @PathParam("custo") double custo){
			
		return controleRoteiro.cadastrarPontos(origem, destino, custo);
	}
	
}
