package enumeracao;

import java.math.BigDecimal;

import calculator.Calculadora;
import calculator.CalculadoraAcre;
import calculator.CalculadoraAlagoas;
import calculator.CalculadoraAmapa;
import calculator.CalculadoraAmazonas;
import calculator.CalculadoraBahia;
import calculator.CalculadoraCeara;
import calculator.CalculadoraDistrito_Federal;
import calculator.CalculadoraEspirito_Santo;
import calculator.CalculadoraGoias;
import calculator.CalculadoraMaranhao;
import calculator.CalculadoraMato_Grosso;
import calculator.CalculadoraMato_Grosso_Do_Sul;
import calculator.CalculadoraMinas_Gerais;
import calculator.CalculadoraPara;
import calculator.CalculadoraParaiba;
import calculator.CalculadoraParana;
import calculator.CalculadoraPernambuco;
import calculator.CalculadoraPiaui;
import calculator.CalculadoraRio_De_Janeiro;
import calculator.CalculadoraRio_Grande_Do_Norte;
import calculator.CalculadoraRio_Grande_Do_Sul;
import calculator.CalculadoraRondonia;
import calculator.CalculadoraRoraima;
import calculator.CalculadoraSanta_Catarina;
import calculator.CalculadoraSao_Paulo;
import calculator.CalculadoraSergipe;
import calculator.CalculadoraTocantins;
import model.Producao;

public enum UnidadeFederacao {
	ACRE(new CalculadoraAcre()), ALAGOAS(new CalculadoraAlagoas()), AMAPA(new CalculadoraAmapa()),
	AMAZONAS(new CalculadoraAmazonas()), BAHIA(new CalculadoraBahia()), CEARA(new CalculadoraCeara()),
	DISTRITO_FEDERAL(new CalculadoraDistrito_Federal()), ESPIRITO_SANTO(new CalculadoraEspirito_Santo()),
	GOIAS(new CalculadoraGoias()), MARANHAO(new CalculadoraMaranhao()), MATO_GROSSO(new CalculadoraMato_Grosso()),
	MATO_GROSSO_DO_SUL(new CalculadoraMato_Grosso_Do_Sul()), MINAS_GERAIS(new CalculadoraMinas_Gerais()),
	PARA(new CalculadoraPara()), PARAIBA(new CalculadoraParaiba()), PARANA(new CalculadoraParana()),
	PERNAMBUCO(new CalculadoraPernambuco()), PIAUI(new CalculadoraPiaui()),
	RIO_DE_JANEIRO(new CalculadoraRio_De_Janeiro()), RIO_GRANDE_DO_NORTE(new CalculadoraRio_Grande_Do_Norte()),
	RIO_GRANDE_DO_SUL(new CalculadoraRio_Grande_Do_Sul()), RONDONIA(new CalculadoraRondonia()),
	RORAIMA(new CalculadoraRoraima()), SANTA_CATARINA(new CalculadoraSanta_Catarina()),
	SAO_PAULO(new CalculadoraSao_Paulo()), SERGIPE(new CalculadoraSergipe()), TOCANTINS(new CalculadoraTocantins());

	private final Calculadora calculadora;

	UnidadeFederacao(Calculadora calculadora) {
		this.calculadora = calculadora;
	}

	public BigDecimal calcularImposto(Producao producao) {
		return calculadora.calcularImposto(producao);
	}
}
