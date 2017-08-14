package acceptedPartnersProvider;

import java.util.Arrays;
import java.util.List;

public class HardcodedPartnersProvider implements AcceptedPartnersProvider {

	public List<String> getAcceptedPartners() {
		return Arrays.asList("PLUTO1", "PLUTO2");
	}

}
