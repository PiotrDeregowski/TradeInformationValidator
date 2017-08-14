
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import dataProvider.JSONDataProvider;
import engine.TradeInformationsValidatorEngine;

/**
 * Unit test for simple App.
 */
public class TradeInformationsValidatorEngineTest {

	private TradeInformationsValidatorEngine tradeInformationsValidatorEngine;

	@Before
	public void init() {
		tradeInformationsValidatorEngine = new TradeInformationsValidatorEngine();
	}

	@Test
	public void shouldMessageWhenTradeInformationIncorrect() {
		tradeInformationsValidatorEngine.setDataProviderInterface(new JSONDataProvider());
		tradeInformationsValidatorEngine.init("test/Example.JSON");
		assertTrue(!tradeInformationsValidatorEngine.validate().isEmpty());
	}

	@Test
	public void shouldReturnEmptyStringWhenTradeInformationVorrect() {
		JSONDataProvider provider = new JSONDataProvider();
		tradeInformationsValidatorEngine.setTradeInformations(provider.provideData("test/ValidOption.JSON"));
		assertEquals("", tradeInformationsValidatorEngine.validate());
	}

	@Test
	public void shouldReturnEmptyStringForEmptyList() {
		tradeInformationsValidatorEngine.setTradeInformations(null);
		assertEquals("", tradeInformationsValidatorEngine.validate());
	}
}
