package ui.display.views.tree.structure;

import ui.constants.EnumGroupMutationOperator;
import ui.constants.EnumMutationOperator;

public enum DefTreeMutationOperators {
	INSTANCE;

	public AbstractTree getTreeMutationOperators() {

		AbstractTree group_TO = new AbstractTree(
				EnumGroupMutationOperator.TO_DESCRIPTION,
				EnumGroupMutationOperator.TO.toString());
		addChildToTree(group_TO, group_TO.getAcronym());

		AbstractTree group_IH_AC = new AbstractTree(
				EnumGroupMutationOperator.IH_AC_DESCRIPTION,
				EnumGroupMutationOperator.IH_AC.toString());
		addChildToTree(group_IH_AC, group_IH_AC.getAcronym());

		AbstractTree group_I = new AbstractTree(
				EnumGroupMutationOperator.I_DESCRIPTION,
				EnumGroupMutationOperator.I.toString());
		addChildToTree(group_I, group_I.getAcronym());

		AbstractTree group_P = new AbstractTree(
				EnumGroupMutationOperator.P_DESCRIPTION,
				EnumGroupMutationOperator.P.toString());
		addChildToTree(group_P, group_P.getAcronym());

		AbstractTree group_JSF = new AbstractTree(
				EnumGroupMutationOperator.JSF_DESCRIPTION,
				EnumGroupMutationOperator.JSF.toString());
		addChildToTree(group_JSF, group_JSF.getAcronym());

		AbstractTree group_CPM = new AbstractTree(
				EnumGroupMutationOperator.CPM_DESCRIPTION,
				EnumGroupMutationOperator.CPM.toString());
		addChildToTree(group_CPM, group_CPM.getAcronym());

		AbstractTree invisibleRoot = new AbstractTree("", "");
		invisibleRoot.addChild(group_TO);
		invisibleRoot.addChild(group_IH_AC);
		invisibleRoot.addChild(group_I);
		invisibleRoot.addChild(group_P);
		invisibleRoot.addChild(group_JSF);
		invisibleRoot.addChild(group_CPM);
		return invisibleRoot;
	}

	private void addChildToTree(AbstractTree parent, String acronymParent) {
		switch (EnumGroupMutationOperator.valueOf(acronymParent)) {
		case TO:
			parent.addChild(new AbstractTree(
					EnumMutationOperator.ABS_DESCRIPTION,
					EnumMutationOperator.ABS.toString()));
			parent.addChild(new AbstractTree(
					EnumMutationOperator.AOR_DESCRIPTION,
					EnumMutationOperator.AOR.toString()));
			parent.addChild(new AbstractTree(
					EnumMutationOperator.ROR_DESCRIPTION,
					EnumMutationOperator.ROR.toString()));
			parent.addChild(new AbstractTree(
					EnumMutationOperator.COR_DESCRIPTION,
					EnumMutationOperator.COR.toString()));
			parent.addChild(new AbstractTree(
					EnumMutationOperator.SOR_DESCRIPTION,
					EnumMutationOperator.SOR.toString()));
			parent.addChild(new AbstractTree(
					EnumMutationOperator.LOR_DESCRIPTION,
					EnumMutationOperator.LOR.toString()));
			parent.addChild(new AbstractTree(
					EnumMutationOperator.ASR_DESCRIPTION,
					EnumMutationOperator.ASR.toString()));
			parent.addChild(new AbstractTree(
					EnumMutationOperator.UOI_DESCRIPTION,
					EnumMutationOperator.UOI.toString()));
			parent.addChild(new AbstractTree(
					EnumMutationOperator.UOD_DESCRIPTION,
					EnumMutationOperator.UOD.toString()));
			parent.addChild(new AbstractTree(
					EnumMutationOperator.SVR_DESCRIPTION,
					EnumMutationOperator.SVR.toString()));
			parent.addChild(new AbstractTree(
					EnumMutationOperator.BSR_DESCRIPTION,
					EnumMutationOperator.BSR.toString()));
			break;
		case IH_AC:
			parent.addChild(new AbstractTree(
					EnumMutationOperator.AMC_DESCRIPTION,
					EnumMutationOperator.AMC.toString()));
			break;
		case I:
			parent.addChild(new AbstractTree(
					EnumMutationOperator.IHD_DESCRIPTION,
					EnumMutationOperator.IHD.toString()));
			parent.addChild(new AbstractTree(
					EnumMutationOperator.IHI_DESCRIPTION,
					EnumMutationOperator.IHI.toString()));
			parent.addChild(new AbstractTree(
					EnumMutationOperator.IOD_DESCRIPTION,
					EnumMutationOperator.IOD.toString()));
			parent.addChild(new AbstractTree(
					EnumMutationOperator.IOP_DESCRIPTION,
					EnumMutationOperator.IOP.toString()));
			parent.addChild(new AbstractTree(
					EnumMutationOperator.IOR_DESCRIPTION,
					EnumMutationOperator.IOR.toString()));
			parent.addChild(new AbstractTree(
					EnumMutationOperator.ISK_DESCRIPTION,
					EnumMutationOperator.ISK.toString()));
			parent.addChild(new AbstractTree(
					EnumMutationOperator.IPC_DESCRIPTION,
					EnumMutationOperator.IPC.toString()));
			break;
		case P:
			parent.addChild(new AbstractTree(
					EnumMutationOperator.PNC_DESCRIPTION,
					EnumMutationOperator.PNC.toString()));
			parent.addChild(new AbstractTree(
					EnumMutationOperator.PMD_DESCRIPTION,
					EnumMutationOperator.PMD.toString()));
			parent.addChild(new AbstractTree(
					EnumMutationOperator.PPD_DESCRIPTION,
					EnumMutationOperator.PPD.toString()));
			parent.addChild(new AbstractTree(
					EnumMutationOperator.PRV_DESCRIPTION,
					EnumMutationOperator.PRV.toString()));
			break;
		case O:
			parent.addChild(new AbstractTree(
					EnumMutationOperator.OMR_DESCRIPTION,
					EnumMutationOperator.OMR.toString()));
			parent.addChild(new AbstractTree(
					EnumMutationOperator.OMD_DESCRIPTION,
					EnumMutationOperator.OMD.toString()));
			parent.addChild(new AbstractTree(
					EnumMutationOperator.OAO_DESCRIPTION,
					EnumMutationOperator.OAO.toString()));
			parent.addChild(new AbstractTree(
					EnumMutationOperator.OAN_DESCRIPTION,
					EnumMutationOperator.OAN.toString()));
			break;
		case JSF:
			parent.addChild(new AbstractTree(
					EnumMutationOperator.JTD_DESCRIPTION,
					EnumMutationOperator.JTD.toString()));
			parent.addChild(new AbstractTree(
					EnumMutationOperator.JSC_DESCRIPTION,
					EnumMutationOperator.JSC.toString()));
			parent.addChild(new AbstractTree(
					EnumMutationOperator.JID_DESCRIPTION,
					EnumMutationOperator.JID.toString()));
			parent.addChild(new AbstractTree(
					EnumMutationOperator.JDC_DESCRIPTION,
					EnumMutationOperator.JDC.toString()));
			break;
		case CPM:
			parent.addChild(new AbstractTree(
					EnumMutationOperator.EOA_DESCRIPTION,
					EnumMutationOperator.EOA.toString()));
			parent.addChild(new AbstractTree(
					EnumMutationOperator.EOC_DESCRIPTION,
					EnumMutationOperator.EOC.toString()));
			parent.addChild(new AbstractTree(
					EnumMutationOperator.EAM_DESCRIPTION,
					EnumMutationOperator.EAM.toString()));
			parent.addChild(new AbstractTree(
					EnumMutationOperator.EMM_DESCRIPTION,
					EnumMutationOperator.EMM.toString()));
			break;

		}

	}

}
