package validators;

import tradeinformation.model.Option;
import tradeinformation.model.TradeInformation;

public class ExpiryDateValidator extends TradeInformationValidator {

	@Override
	public String validate(TradeInformation tradeInformation) {
		Option option = ((Option) tradeInformation);
		if (!option.getExpiryDate().before(option.getDeliveryDate())) {
			return "Expiry date should be before delivery date." + System.lineSeparator();
		}
		return "";
	}

}
