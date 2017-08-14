package engine;

import java.util.List;

import dataProvider.DataProviderInterface;
import tradeinformation.model.TradeInformation;

public class TradeInformationsValidatorEngine {

	private List<TradeInformation> tradeInformations;
	private DataProviderInterface dataProviderInterface;

	public void init(Object source) {
		tradeInformations = dataProviderInterface.provideData(source);
	}

	public String validate() {
		if (tradeInformations == null || tradeInformations.isEmpty()) {
			return "";
		}
		StringBuilder result = new StringBuilder();
		for (TradeInformation tradeInfromation : tradeInformations) {
			String validationResult = tradeInfromation.validate();
			if (!validationResult.isEmpty()) {
				result.append(tradeInfromation.toString());
				result.append(System.lineSeparator());
				result.append(validationResult);
			}
		}
		return result.toString();
	}

	public List<TradeInformation> getTradeInformations() {
		return tradeInformations;
	}

	public void setTradeInformations(List<TradeInformation> tradeInformations) {
		this.tradeInformations = tradeInformations;
	}

	public DataProviderInterface getDataProviderInterface() {
		return dataProviderInterface;
	}

	public void setDataProviderInterface(DataProviderInterface dataProviderInterface) {
		this.dataProviderInterface = dataProviderInterface;
	}

}
