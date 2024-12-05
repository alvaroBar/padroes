package calculator;

import java.math.BigDecimal;
import java.math.MathContext;

import model.Producao;

public class CalculadoraMato_Grosso implements Calculadora {

	@Override
	public BigDecimal calcularImposto(Producao producao) {
		BigDecimal imposto = producao.getValorUnitario().multiply(new BigDecimal(producao.getQuantidade()))
				.divide(new BigDecimal("15.15"), MathContext.DECIMAL128);
		return ajustarImposto(producao, imposto);
	}

	public BigDecimal ajustarImposto(Producao producao, BigDecimal imposto) {
		imposto = reduzirImpostoSeAgriculturaFamiliar(producao, imposto);
		imposto = reduzirImpostoSeOrganico(producao, imposto);
		return imposto;
	}

	private BigDecimal reduzirImpostoSeOrganico(Producao producao, BigDecimal imposto) {
		if (!producao.getProduto().isOrganico()) {
			imposto = imposto.multiply(new BigDecimal("1.35"));
		}
		return imposto;
	}

	private BigDecimal reduzirImpostoSeAgriculturaFamiliar(Producao producao, BigDecimal imposto) {
		if (producao.getProduto().isAgriculturaFamiliar()) {
			imposto = imposto.multiply(new BigDecimal("0.75"));
		}
		return imposto;
	}
}
