package tests.validators;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;

import tradeinformation.model.Option;
import validators.ExcerciseStartDateExpiryDateValidator;

public class ExcerciseStartDateExpiryDateValidatorTest {

	private ExcerciseStartDateExpiryDateValidator validator;
	private SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
	private Option option;

	@Before
	public void init() {
		validator = new ExcerciseStartDateExpiryDateValidator();
		option = mock(Option.class);
	}

	@Test
	public void shouldNotCheckEuropenStyleOptions() {
		when(option.getStyle()).thenReturn("EUROPEAN");
		assertEquals("", validator.validate(option));
	}

	@Test
	public void excerciseDateShouldBeBeforeTradeDateForAmericanOption() throws ParseException {
		when(option.getStyle()).thenReturn("AMERICAN");
		when(option.getExcerciseStartDate()).thenReturn(formatter.parse("08-09-2017"));
		when(option.getExpiryDate()).thenReturn(formatter.parse("08-10-2017"));
		assertEquals("", validator.validate(option));
	}

	@Test
	public void excerciseDateShouldNotBeAfterExpiryDateForAmericanOption() throws ParseException {
		when(option.getStyle()).thenReturn("AMERICAN");
		when(option.getExcerciseStartDate()).thenReturn(formatter.parse("08-15-2017"));
		when(option.getExpiryDate()).thenReturn(formatter.parse("08-14-2017"));
		assertEquals("Excercise start date should be before expiry date." + System.lineSeparator(),
				validator.validate(option));
	}

	@Test
	public void excerciseDateShouldNotBeSameAsExpiryDateForAmericanOption() throws ParseException {
		when(option.getStyle()).thenReturn("AMERICAN");
		when(option.getExcerciseStartDate()).thenReturn(formatter.parse("08-15-2017"));
		when(option.getExpiryDate()).thenReturn(formatter.parse("08-15-2017"));
		assertEquals("Excercise start date should be before expiry date." + System.lineSeparator(),
				validator.validate(option));
	}

}
