package tradeinformation.model;

import org.json.simple.JSONObject;

public class Spot extends TradeInformationWithValueDate {

	public Spot(JSONObject json) {
		super(json);
	}

	@Override
	public String getType() {
		return "Spot";
	}

}
