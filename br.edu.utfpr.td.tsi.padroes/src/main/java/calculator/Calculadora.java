package calculator;

import java.math.BigDecimal;

import model.Producao;

public interface Calculadora {
	public BigDecimal calcularImposto(Producao producao);

}
