package principal;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import enumeracao.UnidadeFederacao;
import model.Producao;

public class Main {

	public static void main(String[] args) {

		ArrayList<Producao> listaProducao = lerArquivoProducao();

		BigDecimal valorTotalProducao = new BigDecimal("0");
		BigDecimal imposto = new BigDecimal("0");

		for (Producao producao : listaProducao) {
			UnidadeFederacao uf = producao.getUf();
			imposto = imposto.add(uf.calcularImposto(producao));
			valorTotalProducao = valorTotalProducao
					.add(producao.getValorUnitario().multiply(new BigDecimal(producao.getQuantidade())));
		}

		String valorImpostoFormatado = NumberFormat.getCurrencyInstance().format(imposto);
		System.out.println(String.format("Valor total de impostos: %s", valorImpostoFormatado));

		String valorTotalProducaoFormatado = NumberFormat.getCurrencyInstance().format(valorTotalProducao);
		System.out.println(String.format("Valor total da produção: %s", valorTotalProducaoFormatado));
	}

	private static ArrayList<Producao> lerArquivoProducao() {
		ArrayList<Producao> producao = new ArrayList<>();
		Gson gson = new Gson();
		try (FileReader reader = new FileReader(
				"/home/pitica/git/repository/br.edu.utfpr.td.tsi.padroes/src/main/resources/relatorioProducao.json")) {
			producao = gson.fromJson(reader, new TypeToken<ArrayList<Producao>>() {
			}.getType());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return producao;
	}
}
