package validators;

import tradeinformation.model.Option;
import tradeinformation.model.TradeInformation;

public class ExcerciseStartDateExpiryDateValidator extends TradeInformationValidator {

	@Override
	public String validate(TradeInformation tradeInformation) {
		Option option = ((Option) tradeInformation);
		StringBuilder result = new StringBuilder();

		if ("AMERICAN".equals(option.getStyle())) {
			if (!option.getExcerciseStartDate().before(option.getExpiryDate())) {
				result.append("Excercise start date should be before expiry date." + System.lineSeparator());
			}
		}
		return result.toString();
	}

}
