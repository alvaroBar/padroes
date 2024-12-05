package model;

import java.math.BigDecimal;

public class Produto {
	private String nome;
	private BigDecimal peso;
	private BigDecimal volume;
	private boolean organico;
	private boolean agriculturaFamiliar;

	public Produto() {
	}

	public Produto(String nome, BigDecimal peso, BigDecimal volume, boolean organico, boolean agriculturaFamiliar) {
		super();
		this.nome = nome;
		this.peso = peso;
		this.volume = volume;
		this.organico = organico;
		this.agriculturaFamiliar = agriculturaFamiliar;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

	public boolean isOrganico() {
		return organico;
	}

	public void setOrganico(boolean organico) {
		this.organico = organico;
	}

	public boolean isAgriculturaFamiliar() {
		return agriculturaFamiliar;
	}

	public void setAgriculturaFamiliar(boolean agriculturaFamiliar) {
		this.agriculturaFamiliar = agriculturaFamiliar;
	}

}
