package apachePoiHandsOn;

public class DummyExcel {
	
	
	private String category;
	private String parameterName;
	private String defaultvalue;
	private String datatype;
	private String parameterDescription;
	private String verificationProcedure;
	private String parameterType;
	private String tunedvalue;
		
	public DummyExcel() {}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public String getDefaultvalue() {
		return defaultvalue;
	}

	public void setDefaultvalue(String defaultvalue) {
		this.defaultvalue = defaultvalue;
	}

	public String getDatatype() {
		return datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	public String getParameterDescription() {
		return parameterDescription;
	}

	public void setParameterDescription(String parameterDescription) {
		this.parameterDescription = parameterDescription;
	}

	public String getVerificationProcedure() {
		return verificationProcedure;
	}

	public void setVerificationProcedure(String verificationProcedure) {
		this.verificationProcedure = verificationProcedure;
	}

	public String getParameterType() {
		return parameterType;
	}

	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}

	public String getTunedvalue() {
		return tunedvalue;
	}

	public void setTunedvalue(String tunedvalue) {
		this.tunedvalue = tunedvalue;
	}

	@Override
	public String toString() {
		return "[category=" + category + ", parameterName=" + parameterName + ", defaultvalue="
				+ defaultvalue + ", datatype=" + datatype + ", parameterDescription=" + parameterDescription
				+ ", verificationProcedure=" + verificationProcedure + ", parameterType=" + parameterType
				+ ", tunedvalue=" + tunedvalue + "]";
	}


}
