package wk.dd.split;

public class CodeDemo {
	private String code;
	private String codeAddress;

	
	public CodeDemo() {
		super();
	}
	public CodeDemo(String code,String codeAddress){
		this.code = code;
		this.codeAddress = codeAddress;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeAddress() {
		return codeAddress;
	}

	public void setCodeAddress(String codeAddress) {
		this.codeAddress = codeAddress;
	}
}
