package calculator;

import java.math.BigDecimal;
import java.math.MathContext;

import model.Producao;

public class CalculadoraAlagoas implements Calculadora {

	private static final BigDecimal ALIQOTA_IMPOSTO_BASE = new BigDecimal("10.10");
	private static final BigDecimal TAXA_EXTRA_POR_QUANTIDADE = new BigDecimal("1.25");
	private static final BigDecimal REDUCAO_AGRICULTURA_FAMILIAR = new BigDecimal("0.75");

	@Override
	public BigDecimal calcularImposto(Producao producao) {
		BigDecimal imposto = producao.getValorUnitario().multiply(new BigDecimal(producao.getQuantidade()))
				.divide(ALIQOTA_IMPOSTO_BASE, MathContext.DECIMAL128);

		imposto = ajustarImposto(producao, imposto);

		return imposto.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	private BigDecimal ajustarImposto(Producao producao, BigDecimal imposto) {
		imposto = reduzirImpostoSeAgriculturaFamiliar(producao, imposto);
		imposto = aumentarImpostoSeExcederLimitePeso(producao, imposto);
		return imposto;
	}

	private BigDecimal aumentarImpostoSeExcederLimitePeso(Producao producao, BigDecimal imposto) {
		BigDecimal pesoTotal = producao.getProduto().getPeso().multiply(new BigDecimal(producao.getQuantidade()));
		if (pesoTotal.compareTo(new BigDecimal("89")) > 0) {
			BigDecimal taxaExtra = TAXA_EXTRA_POR_QUANTIDADE.multiply(new BigDecimal(producao.getQuantidade()));
			imposto = imposto.add(taxaExtra);
		}
		return imposto;
	}

	private BigDecimal reduzirImpostoSeAgriculturaFamiliar(Producao producao, BigDecimal imposto) {
		if (producao.getProduto().isAgriculturaFamiliar()) {
			imposto = imposto.multiply(REDUCAO_AGRICULTURA_FAMILIAR);
		}
		return imposto;
	}
}
