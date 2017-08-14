
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dataProvider.JSONDataProvider;

public class JSONDataProviderTest {

	JSONDataProvider jsonDataProvider;

	@Before
	public void init() {
		jsonDataProvider = new JSONDataProvider();
	}

	@Test
	public void shouldProvideDataFromJSONFile() {
		Assert.assertTrue(!jsonDataProvider.provideData("test/Example.JSON").isEmpty());
	}

	@Test
	public void shouldReturnEmptyListIfFileIsNotValid() {
		Assert.assertTrue(jsonDataProvider.provideData("").isEmpty());
	}
}
