package dataProvider;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import tradeinformation.model.Forward;
import tradeinformation.model.Option;
import tradeinformation.model.Spot;
import tradeinformation.model.TradeInformation;

public class JSONDataProvider implements DataProviderInterface {

	public List<TradeInformation> provideData(Object source) {
		JSONParser parser = new JSONParser();
		List<TradeInformation> tradeInformations = new ArrayList<TradeInformation>();
		try {
			JSONArray tradeInformationsArray = (JSONArray) parser.parse(new FileReader((String) source));
			Iterator<JSONObject> iterator = tradeInformationsArray.iterator();
			while (iterator.hasNext()) {
				tradeInformations.add(provideTradeInformationFromJSONObject(iterator.next()));
			}
		} catch (Exception e) {
			System.out.println("File " + source + " not found");
		}
		return tradeInformations;
	}

	private TradeInformation provideTradeInformationFromJSONObject(JSONObject sourceObject) {
		if ("Spot".equals(sourceObject.get("type"))) {
			return new Spot(sourceObject);
		} else if ("Forward".equals(sourceObject.get("type"))) {
			return new Forward(sourceObject);
		} else if ("VanillaOption".equals(sourceObject.get("type"))) {
			return new Option(sourceObject);
		}
		return null;
	}

}
