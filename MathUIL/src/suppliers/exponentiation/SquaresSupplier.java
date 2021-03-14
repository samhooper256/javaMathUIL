package suppliers.exponentiation;

import java.util.*;

import base.*;
import problems.*;
import suppliers.*;
import utils.*;
import utils.refs.*;

/**
 * @author Sam Hooper
 *
 */
public class SquaresSupplier extends SettingsProblemSupplier {
	
	private static final int MIN_BASE = 0, MAX_BASE = 200;
	public static final int DEFAULT_MIN_BASE = 1, DEFAULT_MAX_BASE = 30;
	
	private final NamedSetting<IntRange> baseRange;
	
	public SquaresSupplier() {
		this(DEFAULT_MIN_BASE, DEFAULT_MAX_BASE);
	}
	
	public SquaresSupplier(int minBase, int maxBase) {
		this.baseRange = NamedSetting.of(new IntRange(MIN_BASE, MAX_BASE, minBase, maxBase), "Base");
		addAllSettings(baseRange);
	}

	@Override
	public SimpleExpression get() {
		return new SimpleExpression(String.format("%d^2", Problem.intInclusive(minBase(), maxBase())));
	}
	
	public int minBase() {
		return baseRange.ref().getLow();
	}
	
	public int maxBase() {
		return baseRange.ref().getHigh();
	}
}
