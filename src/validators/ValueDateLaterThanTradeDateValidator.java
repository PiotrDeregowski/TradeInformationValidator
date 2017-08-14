package validators;

import java.util.Date;

import tradeinformation.model.TradeInformation;
import tradeinformation.model.TradeInformationWithValueDate;

public class ValueDateLaterThanTradeDateValidator extends TradeInformationValidator {

	@Override
	public String validate(TradeInformation tradeInformation) {
		Date valueDate = ((TradeInformationWithValueDate) tradeInformation).getValueDate();
		if (valueDate.before(tradeInformation.getTradeDate()))
			return "Value date before trade date." + System.lineSeparator();
		return "";
	}

}
