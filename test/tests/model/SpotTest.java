package tests.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dataProvider.JSONDataProvider;
import tradeinformation.model.Spot;
import util.Util;
import validators.CurrencyValidator;
import validators.PartnerValidator;
import validators.TradeInformationValidator;

public class SpotTest {

	private Spot spot;
	List<TradeInformationValidator> validators;

	@Before
	public void init() {
		JSONDataProvider provider = new JSONDataProvider();
		spot = (Spot) provider.provideData("test/ValidSpot.JSON").get(0);
		validators = new ArrayList<TradeInformationValidator>();
	}

	@Test
	public void typeShouldBeSpot() {
		assertEquals("Spot", spot.getType());
	}

	@Test
	public void validationForInvalidObjectShouldReturnErrorMessage() {
		JSONDataProvider provider = new JSONDataProvider();
		spot = (Spot) provider.provideData("test/InvalidSpot.JSON").get(0);
		assertTrue(!spot.validate().isEmpty());
	}

	@Test
	public void validationForCorrectObjectShouldReturnEmptyString() {
		assertEquals("", spot.validate());
	}

	@Test
	public void shouldAddPartnerValidatorToSpotValidators() {
		spot.prepareValidators(validators);
		assertTrue(Util.listContainsClass(validators, PartnerValidator.class));
	}

	@Test
	public void shouldAddCurrencyValidatorToSpotValidators() {
		spot.prepareValidators(validators);
		assertTrue(Util.listContainsClass(validators, CurrencyValidator.class));
	}

	@Test
	public void shouldAddValueDateHolidayValidatorToSpotValidators() {
		spot.prepareValidators(validators);
		assertTrue(Util.listContainsClass(validators, CurrencyValidator.class));
	}

	@Test
	public void shouldAddValueDateLaterThanTradeDateValidatorToSpotValidators() {
		spot.prepareValidators(validators);
		assertTrue(Util.listContainsClass(validators, CurrencyValidator.class));
	}

}
