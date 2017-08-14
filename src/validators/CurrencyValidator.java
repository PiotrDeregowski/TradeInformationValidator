package validators;

import java.util.Currency;

import tradeinformation.model.TradeInformation;

public class CurrencyValidator extends TradeInformationValidator {

	@Override
	public String validate(TradeInformation tradeInformation) {
		StringBuilder validationResult = new StringBuilder();
		String currencies = tradeInformation.getCcyPair();
		String firstCurrencyCode = currencies.substring(0, 3);
		String secondCurrency = currencies.substring(3);

		validationResult.append(validateCurriecyCode(firstCurrencyCode));
		validationResult.append(validateCurriecyCode(secondCurrency));

		return validationResult.toString();
	}

	private String validateCurriecyCode(String currencyCode) {
		try {
			Currency.getInstance(currencyCode);
		} catch (IllegalArgumentException exception) {
			return "Currency code " + currencyCode + " not compliant with ISO 4217\n";
		}
		return "";
	}
}
