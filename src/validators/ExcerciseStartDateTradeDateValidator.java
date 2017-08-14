package validators;

import tradeinformation.model.Option;
import tradeinformation.model.TradeInformation;

public class ExcerciseStartDateTradeDateValidator extends TradeInformationValidator {

	@Override
	public String validate(TradeInformation tradeInformation) {
		Option option = ((Option) tradeInformation);
		StringBuilder result = new StringBuilder();

		if ("AMERICAN".equals(option.getStyle())) {
			if (!option.getExcerciseStartDate().after(option.getTradeDate())) {
				result.append("Excercise start date should be after trade date." + System.lineSeparator());
			}
		}
		return result.toString();
	}

}
