package calculator;

import java.math.BigDecimal;
import java.math.MathContext;

import model.Producao;

public class CalculadoraMaranhao implements Calculadora {

	@Override
	public BigDecimal calcularImposto(Producao producao) {
		BigDecimal imposto = producao.getValorUnitario().multiply(new BigDecimal(producao.getQuantidade()))
				.divide(new BigDecimal("19.19"), MathContext.DECIMAL128);
		return ajustarImposto(producao, imposto);
	}

	public BigDecimal ajustarImposto(Producao producao, BigDecimal imposto) {
		imposto = reduzirImpostoAgriculturaFamiliar(producao, imposto);
		imposto = calcularPesoEAplicarImpostoSeExceder(producao, imposto);
		return imposto;
	}

	private BigDecimal calcularPesoEAplicarImpostoSeExceder(Producao producao, BigDecimal imposto) {
		BigDecimal pesoTotal = producao.getProduto().getPeso().multiply(new BigDecimal(producao.getQuantidade()));
		if (pesoTotal.compareTo(new BigDecimal("89")) > 0) {
			BigDecimal taxaExtra = new BigDecimal("1.25").multiply(new BigDecimal(producao.getQuantidade()));
			imposto = imposto.add(taxaExtra);
		}
		return imposto;
	}

	private BigDecimal reduzirImpostoAgriculturaFamiliar(Producao producao, BigDecimal imposto) {
		if (producao.getProduto().isAgriculturaFamiliar()) {
			imposto = imposto.multiply(new BigDecimal("0.75"));
		}
		return imposto;
	}
}
