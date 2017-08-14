package tests.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dataProvider.JSONDataProvider;
import tradeinformation.model.Forward;
import util.Util;
import validators.CurrencyValidator;
import validators.PartnerValidator;
import validators.TradeInformationValidator;

public class ForwardTest {

	private Forward forward;
	List<TradeInformationValidator> validators;

	@Before
	public void init() {
		JSONDataProvider provider = new JSONDataProvider();
		forward = (Forward) provider.provideData("test/ValidForward.JSON").get(0);
		validators = new ArrayList<TradeInformationValidator>();
	}

	@Test
	public void validationForInvalidObjectShouldReturnErrorMessage() {
		JSONDataProvider provider = new JSONDataProvider();
		forward = (Forward) provider.provideData("test/InvalidForward.JSON").get(0);
		assertTrue(!forward.validate().isEmpty());
	}

	@Test
	public void validationForCorrectObjectShouldReturnEmptyString() {
		assertEquals("", forward.validate());
	}

	@Test
	public void typeShouldBeForward() {
		assertEquals("Forward", forward.getType());
	}

	@Test
	public void shouldAddPartnerValidatorToForwardValidators() {
		forward.prepareValidators(validators);
		assertTrue(Util.listContainsClass(validators, PartnerValidator.class));
	}

	@Test
	public void shouldAddCurrencyValidatorToForwardValidators() {
		forward.prepareValidators(validators);
		assertTrue(Util.listContainsClass(validators, CurrencyValidator.class));
	}

	@Test
	public void shouldAddValueDateHolidayValidatorToForwardValidators() {
		forward.prepareValidators(validators);
		assertTrue(Util.listContainsClass(validators, CurrencyValidator.class));
	}

	@Test
	public void shouldAddValueDateLaterThanTradeDateValidatorToForwardValidators() {
		forward.prepareValidators(validators);
		assertTrue(Util.listContainsClass(validators, CurrencyValidator.class));
	}
}
