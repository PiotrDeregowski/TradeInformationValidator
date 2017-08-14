package validators;

import tradeinformation.model.Option;
import tradeinformation.model.TradeInformation;

public class PremiumDateValidator extends TradeInformationValidator {

	@Override
	public String validate(TradeInformation tradeInformation) {
		Option option = ((Option) tradeInformation);
		if (!option.getPremiumDate().before(option.getDeliveryDate())) {
			return "Premium date should be before delivery date." + System.lineSeparator();
		}
		return "";
	}

}
