package validators;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import tradeinformation.model.TradeInformation;
import tradeinformation.model.TradeInformationWithValueDate;

public class ValueDateCannotFallOnFreeDayValidator extends TradeInformationValidator {

	private Map<String, List<Date>> holydayCurrencyMap;

	@Override
	public String validate(TradeInformation tradeInformation) {
		Date valueDate = ((TradeInformationWithValueDate) tradeInformation).getValueDate();
		StringBuilder result = new StringBuilder();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(valueDate);
		result.append(checkIfValueDateFallOnWeekend(calendar));

		String currencies = tradeInformation.getCcyPair();
		result.append(checkIfValueDateFallOnNonWorkingDay(valueDate, currencies));

		return result.toString();
	}

	private Object checkIfValueDateFallOnNonWorkingDay(Date valueDate, String currencies) {
		if (holydayCurrencyMap.get(currencies.substring(0, 3)).contains(valueDate)
				|| holydayCurrencyMap.get(currencies.substring(3)).contains(valueDate))
			return "Value date cannot fall on holyday";
		return "";
	}

	private String checkIfValueDateFallOnWeekend(Calendar calendar) {
		if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			return "Value date cannot fall on saturday";
		} else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			return "Value date cannot fall on sunday";
		}
		return "";
	}

	public Map<String, List<Date>> getHolydayCurrencyMap() {
		return holydayCurrencyMap;
	}

	public void setHolydayCurrencyMap(Map<String, List<Date>> holydayCurrencyMap) {
		this.holydayCurrencyMap = holydayCurrencyMap;
	}

}
