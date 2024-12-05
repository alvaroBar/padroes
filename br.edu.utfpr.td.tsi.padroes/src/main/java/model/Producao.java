package model;

import java.math.BigDecimal;

import com.google.gson.annotations.SerializedName;

import enumeracao.UnidadeFederacao;

public class Producao {
	private BigDecimal valorUnitario;
	private int quantidade;
	private Produto produto;

	@SerializedName("localProducao")
	private UnidadeFederacao uf;

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public UnidadeFederacao getUf() {
		return uf;
	}

	public void setUf(UnidadeFederacao uf) {
		this.uf = uf;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
