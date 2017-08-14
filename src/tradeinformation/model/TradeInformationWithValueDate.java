package tradeinformation.model;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;

import holydayCurrencyMapProvider.ExampleHolydayCurrencyMapProvider;
import holydayCurrencyMapProvider.HolydayCurrencyMapProviderInderface;
import validators.CheckValueDateToProductTypeValidator;
import validators.TradeInformationValidator;
import validators.ValueDateCannotFallOnFreeDayValidator;
import validators.ValueDateLaterThanTradeDateValidator;

public abstract class TradeInformationWithValueDate extends TradeInformation {
	private Date valueDate;

	public TradeInformationWithValueDate(JSONObject json) {
		super(json);
		try {
			if (json.get("valueDate") != null)
				valueDate = formatter.parse((String) json.get("valueDate"));
		} catch (ParseException exception) {
			System.out.print("Wrong data format");
		}
	}

	public void prepareValidators(List<TradeInformationValidator> validators) {

		super.prepareValidators(validators);
		prepareValueDateLaterThanTradeDateValidator(validators);
		prepareValueDateCannotFallOnFreeDayValidator(validators);
		prepareCheckValueDateToProductTypeValidator(validators);
	}

	private void prepareValueDateCannotFallOnFreeDayValidator(List<TradeInformationValidator> validators) {
		ValueDateCannotFallOnFreeDayValidator validator = new ValueDateCannotFallOnFreeDayValidator();
		HolydayCurrencyMapProviderInderface holydayCurrencyMap = new ExampleHolydayCurrencyMapProvider();
		validator.setHolydayCurrencyMap(holydayCurrencyMap.getMap());
		validators.add(validator);
	}

	private void prepareValueDateLaterThanTradeDateValidator(List<TradeInformationValidator> validators) {
		ValueDateLaterThanTradeDateValidator validator = new ValueDateLaterThanTradeDateValidator();
		validators.add(validator);
	}

	private void prepareCheckValueDateToProductTypeValidator(List<TradeInformationValidator> validators) {
		CheckValueDateToProductTypeValidator validator = new CheckValueDateToProductTypeValidator();
		validators.add(validator);
	}

	public Date getValueDate() {
		return valueDate;
	}

	public void setValueDate(Date valueDate) {
		this.valueDate = valueDate;
	}

}
