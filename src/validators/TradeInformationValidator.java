package validators;

import tradeinformation.model.TradeInformation;

public abstract class TradeInformationValidator {
	public abstract String validate(TradeInformation tradeInformation);
}
