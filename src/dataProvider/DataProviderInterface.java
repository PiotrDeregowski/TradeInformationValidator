package dataProvider;

import java.util.List;

import tradeinformation.model.TradeInformation;

public interface DataProviderInterface {
	List<TradeInformation> provideData(Object source);
}
