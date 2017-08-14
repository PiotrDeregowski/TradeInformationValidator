package tradeinformation.model;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;

import validators.ExcerciseStartDateExpiryDateValidator;
import validators.ExcerciseStartDateTradeDateValidator;
import validators.ExpiryDateValidator;
import validators.PremiumDateValidator;
import validators.TradeInformationValidator;

public class Option extends TradeInformation {
	private String style;
	private String strategy;
	private Date deliveryDate;
	private Date expiryDate;
	private String payCcy;
	private Double premium;
	private String premiumCcy;
	private String premiumType;
	private Date premiumDate;
	private Date excerciseStartDate;

	public Option(JSONObject json) {
		super(json);
		style = (String) json.get("style");
		strategy = (String) json.get("strategy");
		payCcy = (String) json.get("payCcy");
		premium = (Double) json.get("premium");
		premiumCcy = (String) json.get("premiumCcy");
		premiumType = (String) json.get("premiumType");
		try {
			if (json.get("deliveryDate") != null)
				deliveryDate = formatter.parse((String) json.get("deliveryDate"));
			if (json.get("expiryDate") != null)
				expiryDate = formatter.parse((String) json.get("expiryDate"));
			if (json.get("excerciseStartDate") != null)
				excerciseStartDate = formatter.parse((String) json.get("excerciseStartDate"));
			if (json.get("premiumDate") != null)
				premiumDate = formatter.parse((String) json.get("premiumDate"));
		} catch (ParseException exception) {
			System.out.print("Wrong data format");
		}
	}

	@Override
	public void prepareValidators(List<TradeInformationValidator> validators) {

		super.prepareValidators(validators);
		prepareExpiryDateValidator(validators);
		preparePremiumDateValidator(validators);
		prepareExcerciseStartDateTradeDateValidator(validators);
		prepareExcerciseStartDateExpiryDateValidator(validators);

	}

	private void prepareExcerciseStartDateTradeDateValidator(List<TradeInformationValidator> validators) {
		ExcerciseStartDateTradeDateValidator currencyValidator = new ExcerciseStartDateTradeDateValidator();
		validators.add(currencyValidator);
	}

	private void prepareExcerciseStartDateExpiryDateValidator(List<TradeInformationValidator> validators) {
		ExcerciseStartDateExpiryDateValidator currencyValidator = new ExcerciseStartDateExpiryDateValidator();
		validators.add(currencyValidator);
	}

	private void preparePremiumDateValidator(List<TradeInformationValidator> validators) {
		PremiumDateValidator currencyValidator = new PremiumDateValidator();
		validators.add(currencyValidator);
	}

	private void prepareExpiryDateValidator(List<TradeInformationValidator> validators) {
		ExpiryDateValidator currencyValidator = new ExpiryDateValidator();
		validators.add(currencyValidator);
	}

	@Override
	public String getType() {
		return "Option";
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getStrategy() {
		return strategy;
	}

	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getPayCcy() {
		return payCcy;
	}

	public void setPayCcy(String payCcy) {
		this.payCcy = payCcy;
	}

	public Double getPremium() {
		return premium;
	}

	public void setPremium(Double premium) {
		this.premium = premium;
	}

	public String getPremiumCcy() {
		return premiumCcy;
	}

	public void setPremiumCcy(String premiumCcy) {
		this.premiumCcy = premiumCcy;
	}

	public String getPremiumType() {
		return premiumType;
	}

	public void setPremiumType(String premiumType) {
		this.premiumType = premiumType;
	}

	public Date getPremiumDate() {
		return premiumDate;
	}

	public void setPremiumDate(Date premiumDate) {
		this.premiumDate = premiumDate;
	}

	public Date getExcerciseStartDate() {
		return excerciseStartDate;
	}

	public void setExcerciseStartDate(Date excerciseStartDate) {
		this.excerciseStartDate = excerciseStartDate;
	}
}
