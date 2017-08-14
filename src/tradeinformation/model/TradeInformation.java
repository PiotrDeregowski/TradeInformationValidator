package tradeinformation.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;

import acceptedPartnersProvider.HardcodedPartnersProvider;
import validators.CurrencyValidator;
import validators.PartnerValidator;
import validators.TradeInformationValidator;

public abstract class TradeInformation {
	private String customer;
	private String ccyPair;
	private String direction;
	private Date tradeDate;
	private Double amount1;
	private Double amount2;
	private Double rate;

	private String legalEntity;
	private String trader;
	public SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	public TradeInformation(JSONObject json) {
		customer = (String) json.get("customer");
		ccyPair = (String) json.get("ccyPair");
		direction = (String) json.get("direction");
		amount1 = (Double) json.get("amount1");
		amount2 = (Double) json.get("amount2");
		rate = (Double) json.get("rate");
		legalEntity = (String) json.get("legalEntity");
		trader = (String) json.get("trader");
		try {
			if (json.get("tradeDate") != null)
				tradeDate = formatter.parse((String) json.get("tradeDate"));
		} catch (ParseException exception) {
			System.out.print("Wrong data format");
		}

	}

	public String validate() {
		List<TradeInformationValidator> validators = new ArrayList<TradeInformationValidator>();
		prepareValidators(validators);

		StringBuilder result = getValidationResult(validators);
		return result.toString();
	}

	private StringBuilder getValidationResult(List<TradeInformationValidator> validators) {
		StringBuilder result = new StringBuilder();
		for (TradeInformationValidator validator : validators) {
			String validationResult = validator.validate(this);
			if (!validationResult.isEmpty()) {
				result.append(validator.validate(this));
			}
		}
		return result;
	}

	public void prepareValidators(List<TradeInformationValidator> validators) {

		preparePartnerValidator(validators);
		prepareCurrencyValidator(validators);

	}

	private void prepareCurrencyValidator(List<TradeInformationValidator> validators) {
		CurrencyValidator currencyValidator = new CurrencyValidator();
		validators.add(currencyValidator);
	}

	private void preparePartnerValidator(List<TradeInformationValidator> validators) {
		PartnerValidator partnerIsSupportedValidator = new PartnerValidator();
		partnerIsSupportedValidator.setAcceptedPartnersProvider(new HardcodedPartnersProvider());
		validators.add(partnerIsSupportedValidator);
	}

	public abstract String getType();

	public String toString() {
		return "Operation " + getType() + " requested by " + trader + " on " + tradeDate;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getCcyPair() {
		return ccyPair;
	}

	public void setCcyPair(String ccyPair) {
		this.ccyPair = ccyPair;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getLegalEntity() {
		return legalEntity;
	}

	public void setLegalEntity(String legalEntity) {
		this.legalEntity = legalEntity;
	}

	public String getTrader() {
		return trader;
	}

	public void setTrader(String trader) {
		this.trader = trader;
	}

	public Double getAmount1() {
		return amount1;
	}

	public void setAmount1(Double amount1) {
		this.amount1 = amount1;
	}

	public Double getAmount2() {
		return amount2;
	}

	public void setAmount2(Double amount2) {
		this.amount2 = amount2;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}
}
