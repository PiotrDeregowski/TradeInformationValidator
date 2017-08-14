package tests.validators;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;

import holydayCurrencyMapProvider.ExampleHolydayCurrencyMapProvider;
import holydayCurrencyMapProvider.HolydayCurrencyMapProviderInderface;
import tradeinformation.model.TradeInformationWithValueDate;
import validators.ValueDateCannotFallOnFreeDayValidator;

public class ValueDateCannotFallOnFreeDayValidatorTest {

	ValueDateCannotFallOnFreeDayValidator validator;
	TradeInformationWithValueDate tradeInformation;
	private SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");

	@Before
	public void init() {
		validator = new ValueDateCannotFallOnFreeDayValidator();
		HolydayCurrencyMapProviderInderface holydayCurrencyMap = new ExampleHolydayCurrencyMapProvider();
		validator.setHolydayCurrencyMap(holydayCurrencyMap.getMap());
		tradeInformation = mock(TradeInformationWithValueDate.class);
		when(tradeInformation.getCcyPair()).thenReturn("USDEUR");
	}

	@Test
	public void shouldValueDayFallOnWorkdayThereWillBeNoMessage() throws ParseException {
		when(tradeInformation.getValueDate()).thenReturn(formatter.parse("06-14-2016"));
		assertEquals("", validator.validate(tradeInformation));
	}

	@Test
	public void shouldValueDayFallOnSaturdayThereWillBeErrorMessage() throws ParseException {
		when(tradeInformation.getValueDate()).thenReturn(formatter.parse("06-18-2016"));
		assertEquals("Value date cannot fall on saturday", validator.validate(tradeInformation));
	}

	@Test
	public void shouldValueDayFallOnSundayThereWillBeErrorMessage() throws ParseException {
		when(tradeInformation.getValueDate()).thenReturn(formatter.parse("06-19-2016"));
		assertEquals("Value date cannot fall on sunday", validator.validate(tradeInformation));
	}

	@Test
	public void shouldValueDayFallOnHolydayThereWillBeErrorMessage() throws ParseException {
		when(tradeInformation.getValueDate()).thenReturn(formatter.parse("01-01-2016"));
		assertEquals("Value date cannot fall on holyday", validator.validate(tradeInformation));
	}

}
