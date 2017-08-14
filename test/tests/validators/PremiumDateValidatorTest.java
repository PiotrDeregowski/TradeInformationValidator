package tests.validators;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;

import tradeinformation.model.Option;
import validators.PremiumDateValidator;

public class PremiumDateValidatorTest {

	private PremiumDateValidator validator;
	private SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
	private Option option;

	@Before
	public void init() {
		validator = new PremiumDateValidator();
		option = mock(Option.class);
	}

	@Test
	public void premiumDateShouldBeBeforeDeliveryDate() throws ParseException {
		when(option.getPremiumDate()).thenReturn(formatter.parse("08-09-2017"));
		when(option.getDeliveryDate()).thenReturn(formatter.parse("08-10-2017"));
		assertEquals(validator.validate(option), "");
	}

	@Test
	public void premiumDateShouldNotBeAfterDeliveryDate() throws ParseException {
		when(option.getPremiumDate()).thenReturn(formatter.parse("08-11-2017"));
		when(option.getDeliveryDate()).thenReturn(formatter.parse("08-10-2017"));
		assertEquals(validator.validate(option),
				"Premium date should be before delivery date." + System.lineSeparator());
	}

	@Test
	public void premiumDateShouldNotBeSameAsDeliveryDate() throws ParseException {
		when(option.getPremiumDate()).thenReturn(formatter.parse("08-14-2017"));
		when(option.getDeliveryDate()).thenReturn(formatter.parse("08-14-2017"));
		assertEquals(validator.validate(option),
				"Premium date should be before delivery date." + System.lineSeparator());
	}

}
