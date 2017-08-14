package tests.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dataProvider.JSONDataProvider;
import tradeinformation.model.Option;
import util.Util;
import validators.CurrencyValidator;
import validators.ExcerciseStartDateExpiryDateValidator;
import validators.ExcerciseStartDateTradeDateValidator;
import validators.ExpiryDateValidator;
import validators.PartnerValidator;
import validators.PremiumDateValidator;
import validators.TradeInformationValidator;

public class OptionTest {

	private Option option;
	List<TradeInformationValidator> validators;

	@Before
	public void init() {
		JSONDataProvider provider = new JSONDataProvider();
		option = (Option) provider.provideData("test/ValidOption.JSON").get(0);
		validators = new ArrayList<TradeInformationValidator>();
	}

	@Test
	public void typeShouldBeOption() {
		assertEquals("Option", option.getType());
	}

	@Test
	public void validationForInvalidObjectShouldReturnErrorMessage() {
		JSONDataProvider provider = new JSONDataProvider();
		option = (Option) provider.provideData("test/InvalidOption.JSON").get(0);
		assertTrue(!option.validate().isEmpty());
	}

	@Test
	public void validationForCorrectObjectShouldReturnEmptyString() {
		assertEquals("", option.validate());
	}

	@Test
	public void shouldAddPartnerValidatorToOptionValidators() {
		option.prepareValidators(validators);
		assertTrue(Util.listContainsClass(validators, PartnerValidator.class));
	}

	@Test
	public void shouldAddCurrencyValidatorToOptionValidators() {
		option.prepareValidators(validators);
		assertTrue(Util.listContainsClass(validators, CurrencyValidator.class));
	}

	@Test
	public void shouldAddExpiryDateValidatorToOptionValidators() {
		option.prepareValidators(validators);
		assertTrue(Util.listContainsClass(validators, ExpiryDateValidator.class));
	}

	@Test
	public void shouldAddPremiumDateValidatorToOptionValidators() {
		option.prepareValidators(validators);
		assertTrue(Util.listContainsClass(validators, PremiumDateValidator.class));
	}

	@Test
	public void shouldAddExcerciseStartDateTradeDateValidatorToOptionValidators() {
		option.prepareValidators(validators);
		assertTrue(Util.listContainsClass(validators, ExcerciseStartDateTradeDateValidator.class));
	}

	@Test
	public void shouldAddExcerciseStartDateExpiryDateValidatorToOptionValidators() {
		option.prepareValidators(validators);
		assertTrue(Util.listContainsClass(validators, ExcerciseStartDateExpiryDateValidator.class));
	}
}
