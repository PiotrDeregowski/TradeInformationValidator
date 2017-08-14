package validators;

import acceptedPartnersProvider.AcceptedPartnersProvider;
import tradeinformation.model.TradeInformation;

public class PartnerValidator extends TradeInformationValidator {

	private AcceptedPartnersProvider acceptedPartnersProvider;

	@Override
	public String validate(TradeInformation tradeInformation) {
		if (acceptedPartnersProvider.getAcceptedPartners().contains(tradeInformation.getCustomer()))
			return "";
		else
			return "Not accepted partner.";
	}

	public AcceptedPartnersProvider getAcceptedPartnersProvider() {
		return acceptedPartnersProvider;
	}

	public void setAcceptedPartnersProvider(AcceptedPartnersProvider acceptedPartnersProvider) {
		this.acceptedPartnersProvider = acceptedPartnersProvider;
	}
}
