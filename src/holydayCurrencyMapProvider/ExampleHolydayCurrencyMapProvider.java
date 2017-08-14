package holydayCurrencyMapProvider;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExampleHolydayCurrencyMapProvider implements HolydayCurrencyMapProviderInderface {
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	public Map<String, List<Date>> getMap() {
		Map<String, List<Date>> map = new HashMap<String, List<Date>>();
		List<Date> list = new ArrayList<Date>();
		try {
			list.add(formatter.parse("2016-01-01"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		map.put("USD", list);
		map.put("EUR", list);

		return map;
	}

}
