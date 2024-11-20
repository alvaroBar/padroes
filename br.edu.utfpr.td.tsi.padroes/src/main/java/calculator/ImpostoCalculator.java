package calculator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ImpostoCalculator {

	private static final Map<String, BigDecimal> taxasImposto = new HashMap<>();

	static {
		taxasImposto.put("AMAZONAS", BigDecimal.valueOf(9.99));
		taxasImposto.put("ALAGOAS", BigDecimal.valueOf(10.10));
		taxasImposto.put("ACRE", BigDecimal.valueOf(11.11));
		taxasImposto.put("AMAPA", BigDecimal.valueOf(12.12));
		taxasImposto.put("BAHIA", BigDecimal.valueOf(13.13));
		taxasImposto.put("PARA", BigDecimal.valueOf(14.14));
		taxasImposto.put("MATO_GROSSO", BigDecimal.valueOf(15.15));
		taxasImposto.put("MINAS_GERAIS", BigDecimal.valueOf(16.16));
		taxasImposto.put("MATO_GROSSO_DO_SUL", BigDecimal.valueOf(17.17));
		taxasImposto.put("GOIAS", BigDecimal.valueOf(18.18));
		taxasImposto.put("MARANHAO", BigDecimal.valueOf(19.19));
		taxasImposto.put("RIO_GRANDE_DO_SUL", BigDecimal.valueOf(20.20));
		taxasImposto.put("TOCANTINS", BigDecimal.valueOf(21.21));
		taxasImposto.put("PIAUI", BigDecimal.valueOf(22.22));
		taxasImposto.put("SAO_PAULO", BigDecimal.valueOf(23.23));
		taxasImposto.put("RONDONIA", BigDecimal.valueOf(24.24));
		taxasImposto.put("RORAIMA", BigDecimal.valueOf(25.25));
		taxasImposto.put("PARANA", BigDecimal.valueOf(26.26));
		taxasImposto.put("CEARA", BigDecimal.valueOf(27.27));
		taxasImposto.put("PERNAMBUCO", BigDecimal.valueOf(28.28));
		taxasImposto.put("SANTA_CATARINA", BigDecimal.valueOf(29.29));
		taxasImposto.put("PARAIBA", BigDecimal.valueOf(30.30));
		taxasImposto.put("RIO_GRANDE_DO_NORTE", BigDecimal.valueOf(31.31));
		taxasImposto.put("ESPIRITO_SANTO", BigDecimal.valueOf(32.32));
		taxasImposto.put("RIO_DE_JANEIRO", BigDecimal.valueOf(33.33));
		taxasImposto.put("SERGIPE", BigDecimal.valueOf(34.34));
		taxasImposto.put("DISTRITO_FEDERAL", BigDecimal.valueOf(35.35));
	}

	public static BigDecimal calcularImposto(String estado, BigDecimal valorTotal) {
		BigDecimal taxa = taxasImposto.get(estado.toUpperCase());
		if (taxa != null) {
			return valorTotal.divide(taxa, BigDecimal.ROUND_HALF_UP);
		} else {
			throw new IllegalArgumentException("Estado não encontrado: " + estado);
		}
	}
}
