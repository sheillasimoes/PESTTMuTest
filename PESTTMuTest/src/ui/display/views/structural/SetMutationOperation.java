package ui.display.views.structural;

import java.util.LinkedList;

/**
 * Ver se irei precisar desta class
 * 
 */
public enum SetMutationOperation {

	INSTANCE;

	public LinkedList<String> SetOperation(String groupMutOperator) {
		LinkedList<String> operator = new LinkedList<String>();
		switch (groupMutOperator) {
		case "TO":
			operator.add("ABS");
			operator.add("AOR");
			operator.add("ROR");
			operator.add("COR");
			operator.add("ASR");
			operator.add("UOI");
			operator.add("UOD");
			operator.add("SVR");
			operator.add("BSR");
			return operator;
		case "AC":
			operator.add("AMC");
			return operator;
		case "I":
			operator.add("IHD");
			operator.add("IHI");
			operator.add("IOD");
			operator.add("IOP");
			operator.add("IOR");
			operator.add("ISK");
			operator.add("IPC");
			return operator;
		case "P":
			operator.add("PNC");
			operator.add("PMD");
			operator.add("PPD");
			operator.add("PRV");
			return operator;
		case "O":
			operator.add("OMR");
			operator.add("OMD");
			operator.add("OAO");
			operator.add("OAN");
			return operator;
		case "JSF":
			operator.add("JTD");
			operator.add("JSC");
			operator.add("JID");
			operator.add("JDC");
			return operator;
		case "CPM":
			operator.add("EOA");
			operator.add("EOC");
			operator.add("EAM");
			operator.add("EMM");
			return operator;
		default:
			return null;
		}

	}
}
