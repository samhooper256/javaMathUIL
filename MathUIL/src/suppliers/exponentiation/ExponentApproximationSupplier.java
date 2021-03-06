package suppliers.exponentiation;

import static problems.Problem.*;
import static suppliers.NamedIntRange.*;

import java.util.List;

import math.Evaluator;
import problems.*;
import suppliers.*;
import utils.refs.MutableBooleanRef;

/**
 * @author Sam Hooper
 *
 */
public class ExponentApproximationSupplier extends SettingsProblemSupplier {
	
	private static final RangeStore TERMS = RangeStore.of(1, 4, 1, 3), BASE = RangeStore.of(1, 100, 1, 20), EXPONENT = RangeStore.of(1, 6, 1, 4);
	
	private final NamedIntRange terms, base, exponent;
	private final NamedSetting<MutableBooleanRef> division;
	public ExponentApproximationSupplier() {
		addAllSettings(division = NamedSetting.of(new MutableBooleanRef(true), "Divison"),
				terms = of(TERMS, "Terms"), exponent = of(EXPONENT, "Exponents"), base = of(BASE, "Bases"));
	}
	@Override
	public Problem get() {
		final int termCount = intInclusive(terms);
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < termCount; i++)
			sb.append(getTerm()).append(getOp());
		sb.append(getTerm());
		final String str = sb.toString();
		return Builder.approximation(str, Evaluator.evaluateAsBigDecimalExact(str));
	}
	
	private String getOp() {
		if(division.ref().get() && Math.random() < 0.5)
			return "/";
		return "*";
	}
	
	private String getTerm() {
		return intInclusive(base) + "^" + intInclusive(exponent);
	}
}
