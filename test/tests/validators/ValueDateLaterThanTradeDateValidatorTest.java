package tests.validators;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;

import tradeinformation.model.TradeInformationWithValueDate;
import validators.ValueDateLaterThanTradeDateValidator;

public class ValueDateLaterThanTradeDateValidatorTest {

	ValueDateLaterThanTradeDateValidator validator;
	TradeInformationWithValueDate tradeInformation;
	private SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");

	@Before
	public void init() {
		validator = new ValueDateLaterThanTradeDateValidator();
		tradeInformation = mock(TradeInformationWithValueDate.class);
	}

	@Test
	public void valueDateCanBeAfterTradeDate() throws ParseException {
		when(tradeInformation.getValueDate()).thenReturn(formatter.parse("06-14-2016"));
		when(tradeInformation.getTradeDate()).thenReturn(formatter.parse("06-13-2016"));
		assertEquals("", validator.validate(tradeInformation));
	}

	@Test
	public void valueDateShouldNotBeBeforeTradeDate() throws ParseException {
		when(tradeInformation.getValueDate()).thenReturn(formatter.parse("06-12-2016"));
		when(tradeInformation.getTradeDate()).thenReturn(formatter.parse("06-13-2016"));
		assertEquals("Value date before trade date." + System.lineSeparator(), validator.validate(tradeInformation));
	}

	@Test
	public void valueDateCanBeSameAsTradeDate() throws ParseException {
		when(tradeInformation.getValueDate()).thenReturn(formatter.parse("06-14-2016"));
		when(tradeInformation.getTradeDate()).thenReturn(formatter.parse("06-14-2016"));
		assertEquals("", validator.validate(tradeInformation));
	}
}
