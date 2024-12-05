package br.edu.utfpr.td.tsi.padroes.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import calculator.Calculadora;
import calculator.CalculadoraAcre;
import model.Producao;
import model.Produto;

public class CalculadoraAcreTest {

	@DisplayName("Sem redução de impostos")
	@Test
	public void calcularImpostoSemAgriculturaFamiliar() {
		// Given
		Calculadora calculadora = new CalculadoraAcre();
		Produto produto = new Produto("Arroz", new BigDecimal("50.00"), new BigDecimal("1.0"), false, false);
		Producao producao = new Producao();
		producao.setProduto(produto);
		producao.setValorUnitario(new BigDecimal("100.00"));
		producao.setQuantidade(10);

		// Act
		BigDecimal imposto = calculadora.calcularImposto(producao);

		// Assert
		BigDecimal esperado = new BigDecimal("90.01");
		assertEquals(esperado.setScale(2, BigDecimal.ROUND_HALF_UP), imposto.setScale(2, BigDecimal.ROUND_HALF_UP));
	}

	@DisplayName("Redução de imposto se o produto for da agricultura familiar")
	@Test
	public void calcularImpostoComAgriculturaFamiliar() {
		// Given
		Calculadora calculadora = new CalculadoraAcre();
		Produto produto = new Produto("Feijão", new BigDecimal("50.00"), new BigDecimal("1.0"), false, true);
		Producao producao = new Producao();
		producao.setProduto(produto);
		producao.setValorUnitario(new BigDecimal("100.00"));
		producao.setQuantidade(10);

		// Act
		BigDecimal imposto = calculadora.calcularImposto(producao);

		// Assert
		BigDecimal esperado = new BigDecimal("67.51");
		assertEquals(esperado.setScale(2, BigDecimal.ROUND_HALF_UP), imposto.setScale(2, BigDecimal.ROUND_HALF_UP));
	}
}
