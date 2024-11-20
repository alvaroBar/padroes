package principal;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import calculator.ImpostoCalculator;

public class Main {
	public static void main(String[] args) {
		ObjectMapper mapper = new ObjectMapper();

		Map<String, BigDecimal> valorTotalPorEstado = new HashMap<>();
		BigDecimal valorTotalProducao = BigDecimal.ZERO;
		BigDecimal valorTotalImpostos = BigDecimal.ZERO;

		try {
			ArrayNode producaoArray = (ArrayNode) mapper
					.readTree(new File("src/main/resources/relatorioProducao.json"));

			for (JsonNode item : producaoArray) {
				String estado = item.get("localProducao").asText();
				BigDecimal quantidade = BigDecimal.valueOf(item.get("quantidade").asDouble());
				BigDecimal valorUnitario = BigDecimal.valueOf(item.get("valorUnitario").asDouble());

				BigDecimal valorTotalItem = quantidade.multiply(valorUnitario);

				valorTotalProducao = valorTotalProducao.add(valorTotalItem);

				valorTotalPorEstado.merge(estado, valorTotalItem, BigDecimal::add);
			}

			for (Map.Entry<String, BigDecimal> entry : valorTotalPorEstado.entrySet()) {
				String estado = entry.getKey();
				BigDecimal valorTotal = entry.getValue();

				BigDecimal imposto = ImpostoCalculator.calcularImposto(estado, valorTotal);

				valorTotalImpostos = valorTotalImpostos.add(imposto);

				System.out.printf("Estado: %s, Valor Total: %.2f, Imposto: %.2f%n", estado, valorTotal, imposto);
			}

			System.out.println("**************************");
			System.out.println("**************************");
			System.out.printf("Valor total de impostos: %.2f%n", valorTotalImpostos);
			System.out.printf("Valor total da produção: %.2f%n", valorTotalProducao);

		} catch (IOException e) {
			System.err.println("Erro ao ler o arquivo JSON: " + e.getMessage());
		} catch (IllegalArgumentException e) {
			System.err.println("Erro: " + e.getMessage());
		}
	}
}
