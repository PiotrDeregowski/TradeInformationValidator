package tradeinformation.model;

import org.json.simple.JSONObject;

public class Forward extends TradeInformationWithValueDate {

	public Forward(JSONObject json) {
		super(json);
	}

	@Override
	public String getType() {
		return "Forward";
	}
}
