
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;

import dataProvider.JSONDataProvider;
import engine.TradeInformationsValidatorEngine;
import listeners.ContextServletListener;

@Path("/validator")
public class App {

	@GET
	@Produces("application/json")
	public Response main() {
		TradeInformationsValidatorEngine tradeInformationValidator = new TradeInformationsValidatorEngine();
		tradeInformationValidator.setDataProviderInterface(new JSONDataProvider());

		String tomcatRoot = ContextServletListener.context.getRealPath("/");
		String sourceFile = tomcatRoot + "/WEB-INF/Example.JSON";
		tradeInformationValidator.init(sourceFile);

		JSONObject json = new JSONObject();
		json.put("result", tradeInformationValidator.validate());
		System.out.print(tradeInformationValidator.validate());
		return Response.status(200).entity(json.toJSONString()).build();
	}
}
