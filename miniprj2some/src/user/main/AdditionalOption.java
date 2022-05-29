package user.main;

public class AdditionalOption {  // 옵션 저장
	
	public int optIdx;
	public String optName;
	
	public AdditionalOption() {
		super();
	}
 
	public AdditionalOption(int optIdx, String optName) {
		super();
		this.optIdx = optIdx;
		this.optName = optName;
	}

	@Override
	public String toString() {
		return "AdditionalOption [optIdx=" + optIdx + ", optName=" + optName + "]";
	}
	
}
