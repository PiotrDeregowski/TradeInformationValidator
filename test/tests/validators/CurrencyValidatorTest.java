package tests.validators;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import tradeinformation.model.TradeInformation;
import validators.CurrencyValidator;

public class CurrencyValidatorTest {

	CurrencyValidator validator;
	TradeInformation tradeInformation;

	@Before
	public void init() {
		validator = new CurrencyValidator();
		tradeInformation = mock(TradeInformation.class);
	}

	@Test
	public void shouldAcceptCurrencyCompliantWithISO4217() {
		when(tradeInformation.getCcyPair()).thenReturn("EURUSD");
		assertEquals("", validator.validate(tradeInformation));
	}

	@Test
	public void shouldInformCurrencyIsNotCompliantWithISO4217() {
		when(tradeInformation.getCcyPair()).thenReturn("EURUSDA");
		assertEquals("Currency code USDA not compliant with ISO 4217\n", validator.validate(tradeInformation));
	}

}
