package tests.validators;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;

import tradeinformation.model.Option;
import validators.ExpiryDateValidator;

public class ExpiryDateValidatorTest {

	private ExpiryDateValidator validator;
	private SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
	private Option option;

	@Before
	public void init() {
		validator = new ExpiryDateValidator();
		option = mock(Option.class);
	}

	@Test
	public void expiryDateShouldBeBeforeDeliveryDate() throws ParseException {
		when(option.getExpiryDate()).thenReturn(formatter.parse("08-09-2017"));
		when(option.getDeliveryDate()).thenReturn(formatter.parse("08-10-2017"));
		assertEquals("", validator.validate(option));
	}

	@Test
	public void expiryDateShouldNotBeAfterDeliveryDate() throws ParseException {
		when(option.getExpiryDate()).thenReturn(formatter.parse("08-11-2017"));
		when(option.getDeliveryDate()).thenReturn(formatter.parse("08-10-2017"));
		assertEquals("Expiry date should be before delivery date." + System.lineSeparator(),
				validator.validate(option));
	}

	@Test
	public void expiryDateShouldNotBeSameAsDeliveryDate() throws ParseException {
		when(option.getExpiryDate()).thenReturn(formatter.parse("08-14-2017"));
		when(option.getDeliveryDate()).thenReturn(formatter.parse("08-14-2017"));
		assertEquals("Expiry date should be before delivery date." + System.lineSeparator(),
				validator.validate(option));
	}

}
