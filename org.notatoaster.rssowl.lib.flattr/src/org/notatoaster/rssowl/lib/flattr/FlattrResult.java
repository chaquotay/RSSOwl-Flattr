package org.notatoaster.rssowl.lib.flattr;

import org.rssowl.core.util.StringUtils;

public class FlattrResult {

	private FlattrResultCode fType;
	private String fMessage;
	
	private FlattrResult(FlattrResultCode type, String message) {
		fType = type;
		fMessage = message;
	}
	
	public boolean isSuccess() {
		return fType==FlattrResultCode.Success;
	}
	
	public FlattrResultCode getCode() {
		return fType;
	}
	
	public String getMessage() {
		return fMessage;
	}
	
	public static FlattrResult success() {
		return new FlattrResult(FlattrResultCode.Success, "");
	}
	
	public static FlattrResult failure(String errorCode) {
		return new FlattrResult(resultCodeFromErrorCode(errorCode), errorCode);
	}
	
	private static FlattrResultCode resultCodeFromErrorCode(String errorCode) {
		if(StringUtils.isSet(errorCode)) {
			switch(errorCode){
			case "flattr_once":
				return FlattrResultCode.FailureFlattrOnce;
			case "flattr_owner":
				return FlattrResultCode.FailureFlattrOwner;
			case "no_means":
				return FlattrResultCode.FailureFlattrOwner;
			case "not_found":
				return FlattrResultCode.FailureNotFound;
			case "unauthorized":
				return FlattrResultCode.FailureUnauthorized;
			}
		}
		// everything else, including "invalid_request":
		return FlattrResultCode.FailureMisc;
	}
	
	public static FlattrResult failureBecauseConfigurationIncomplete() {
		return new FlattrResult(FlattrResultCode.FailureUnauthorized, "unauthorized");
	}
	
	public static FlattrResult error(Exception error) {
		return new FlattrResult(FlattrResultCode.Error, error.getLocalizedMessage());
	}
}
