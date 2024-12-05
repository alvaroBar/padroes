package br.edu.utfpr.td.tsi.padroes.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.math.MathContext;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import calculator.Calculadora;
import calculator.CalculadoraAlagoas;
import model.Producao;
import model.Produto;

public class CalculadoraAlagoasTest {

	@DisplayName("Teste sem redução ou sobretaxa")
	@Test
	public void calcularImpostoSemReducaoOuSobretaxa() {
		// Given
		Calculadora calculadora = new CalculadoraAlagoas();
		Produto produto = new Produto("Arroz", new BigDecimal("10.00"), new BigDecimal("5.0"), false, false);
		Producao producao = new Producao();
		producao.setProduto(produto);
		producao.setValorUnitario(new BigDecimal("50.00"));
		producao.setQuantidade(5); // Peso total: 25.0 (não excede 89)

		// Act
		BigDecimal imposto = calculadora.calcularImposto(producao);

		// Assert
		BigDecimal esperado = new BigDecimal("24.75"); // 50.00 * 5 / 10.10
		assertEquals(esperado.setScale(2, BigDecimal.ROUND_HALF_UP), imposto.setScale(2, BigDecimal.ROUND_HALF_UP));
	}

	@DisplayName("Teste com redução por agricultura familiar")
	@Test
	public void calcularImpostoComReducaoAgriculturaFamiliar() {
		// Arrange
		CalculadoraAlagoas calculadora = new CalculadoraAlagoas();
		Produto produto = new Produto();
		produto.setAgriculturaFamiliar(true);
		produto.setPeso(new BigDecimal("20"));

		Producao producao = new Producao();
		producao.setProduto(produto);
		producao.setValorUnitario(new BigDecimal("100"));
		producao.setQuantidade(5);

		BigDecimal valorTotal = producao.getValorUnitario().multiply(new BigDecimal(producao.getQuantidade()));
		BigDecimal impostoBase = valorTotal.divide(new BigDecimal("10.10"), MathContext.DECIMAL128);
		BigDecimal impostoComReducao = impostoBase.multiply(new BigDecimal("0.75"));
		BigDecimal taxaExtra = new BigDecimal("1.25").multiply(new BigDecimal(producao.getQuantidade()));
		BigDecimal impostoEsperado = impostoComReducao.add(taxaExtra).setScale(2, BigDecimal.ROUND_HALF_UP);

		// Act
		BigDecimal impostoCalculado = calculadora.calcularImposto(producao);

		// Assert
		assertEquals(impostoEsperado, impostoCalculado,
				"O imposto calculado não corresponde ao esperado com a redução de 25% e a taxa extra");
	}

	@DisplayName("Teste com sobretaxa por excesso de peso")
	@Test
	public void calcularImpostoComSobretaxaPorExcessoDePeso() {
		// Given
		Calculadora calculadora = new CalculadoraAlagoas();
		Produto produto = new Produto("Trigo", new BigDecimal("8.91"), new BigDecimal("10.00"), false, false);
		Producao producao = new Producao();
		producao.setProduto(produto);
		producao.setValorUnitario(new BigDecimal("80.00"));
		producao.setQuantidade(10); // Peso total: 100.0 (excede 89)

		// Act
		BigDecimal imposto = calculadora.calcularImposto(producao);

		// Assert
		BigDecimal impostoOriginal = new BigDecimal("79.21"); // 80.00 * 10 / 10.10
		BigDecimal taxaExtra = new BigDecimal("12.50"); // 1.25 * 10
		BigDecimal esperado = impostoOriginal.add(taxaExtra); // Imposto + Sobretaxa
		assertEquals(esperado.setScale(2, BigDecimal.ROUND_HALF_UP), imposto.setScale(2, BigDecimal.ROUND_HALF_UP));
	}

	@DisplayName("Teste com redução e sobretaxa")
	@Test
	public void calcularImpostoComReducaoESobretaxa() {
		// Given
		Calculadora calculadora = new CalculadoraAlagoas();
		Produto produto = new Produto("Milho", new BigDecimal("30.00"), new BigDecimal("12.0"), false, true);
		Producao producao = new Producao();
		producao.setProduto(produto);
		producao.setValorUnitario(new BigDecimal("90.00"));
		producao.setQuantidade(10);

		// Act
		BigDecimal imposto = calculadora.calcularImposto(producao);

		// Assert
		BigDecimal esperado = new BigDecimal("89.11").multiply(new BigDecimal("0.75")) // Aplica a redução
				.add(new BigDecimal("12.50")); // Adiciona a sobretaxa
		assertEquals(esperado.setScale(2, BigDecimal.ROUND_HALF_UP), imposto.setScale(2, BigDecimal.ROUND_HALF_UP));
	}
}
