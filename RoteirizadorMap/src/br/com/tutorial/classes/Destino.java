package br.com.tutorial.classes;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author roberley
 *
 */
@Entity
@Table(name = "destino")
public class Destino implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Origem id_origem;
	
	@Column(name = "nome", length = 60)
	private String nome;
	
	@Column(name = "custo")
	private double custo;
	
	public Destino(){		
	}
	
	public Destino(String destino, double custo){
		this.nome = destino;
		this.custo = custo;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Origem getId_origem() {
		return id_origem;
	}

	public void setId_origem(Origem id_origem) {
		this.id_origem = id_origem;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getCusto() {
		return custo;
	}

	public void setCusto(double custo) {
		this.custo = custo;
	}
	
	public String ToString(){
		return this.getNome();
	}
}
