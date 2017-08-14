package tests.validators;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import acceptedPartnersProvider.AcceptedPartnersProvider;
import tradeinformation.model.TradeInformation;
import validators.PartnerValidator;

public class PartnerValidatorTest {

	PartnerValidator validator;
	TradeInformation tradeInformation;

	@Before
	public void init() {
		validator = new PartnerValidator();
		tradeInformation = mock(TradeInformation.class);
		AcceptedPartnersProvider acceptedPartnersProvider = mock(AcceptedPartnersProvider.class);
		when(acceptedPartnersProvider.getAcceptedPartners()).thenReturn(Arrays.asList("Accepted"));
		validator.setAcceptedPartnersProvider(acceptedPartnersProvider);
	}

	@Test
	public void shouldAcceptAcceptedPartners() {
		when(tradeInformation.getCustomer()).thenReturn("Accepted");
		assertEquals("", validator.validate(tradeInformation));
	}

	@Test
	public void shouldFindNotAcceptedPartners() {
		when(tradeInformation.getCustomer()).thenReturn("Not accepted");
		assertEquals("Not accepted partner.", validator.validate(tradeInformation));
	}

}
