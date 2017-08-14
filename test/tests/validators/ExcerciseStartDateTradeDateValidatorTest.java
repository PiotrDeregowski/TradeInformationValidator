package tests.validators;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;

import tradeinformation.model.Option;
import validators.ExcerciseStartDateTradeDateValidator;

public class ExcerciseStartDateTradeDateValidatorTest {

	private ExcerciseStartDateTradeDateValidator validator;
	private SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
	private Option option;

	@Before
	public void init() {
		validator = new ExcerciseStartDateTradeDateValidator();
		option = mock(Option.class);
	}

	@Test
	public void shouldNotCheckEuropenStyleOptions() {
		when(option.getStyle()).thenReturn("EUROPEAN");
		assertEquals("", validator.validate(option));
	}

	@Test
	public void excerciseDateShouldBeAfterTradeDateForAmericanOption() throws ParseException {
		when(option.getStyle()).thenReturn("AMERICAN");
		when(option.getExcerciseStartDate()).thenReturn(formatter.parse("08-13-2017"));
		when(option.getTradeDate()).thenReturn(formatter.parse("08-10-2017"));
		assertEquals("", validator.validate(option));
	}

	@Test
	public void excerciseDateShouldNotBeBeforeTradeDateForAmericanOption() throws ParseException {
		when(option.getStyle()).thenReturn("AMERICAN");
		when(option.getExcerciseStartDate()).thenReturn(formatter.parse("08-13-2017"));
		when(option.getTradeDate()).thenReturn(formatter.parse("08-14-2017"));
		assertEquals("Excercise start date should be after trade date." + System.lineSeparator(),
				validator.validate(option));
	}

	@Test
	public void excerciseDateShouldNotBeSameAsTradeDateForAmericanOption() throws ParseException {
		when(option.getStyle()).thenReturn("AMERICAN");
		when(option.getExcerciseStartDate()).thenReturn(formatter.parse("08-14-2017"));
		when(option.getTradeDate()).thenReturn(formatter.parse("08-14-2017"));
		assertEquals("Excercise start date should be after trade date." + System.lineSeparator(),
				validator.validate(option));
	}
}
