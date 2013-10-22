package domain.constants;

import org.eclipse.jdt.core.dom.Modifier;

public enum EnumModifierKeyword {
	 PRIVATE_KEYWORD(Modifier.ModifierKeyword.PRIVATE_KEYWORD),
	 PROTECTED_KEYWORD(Modifier.ModifierKeyword.PROTECTED_KEYWORD),
	 PUBLIC_KEYWORD(Modifier.ModifierKeyword.PUBLIC_KEYWORD);
	
	 // Representa string correspondente ao modifier
	 private Modifier.ModifierKeyword modifier;
	
	 EnumModifierKeyword(Modifier.ModifierKeyword operator) {
	 this.modifier = operator;
	 }
	
	 public Modifier.ModifierKeyword getModifierKeyword() {
	 return modifier;
	 }
}
