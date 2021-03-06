package suppliers.pemdas;

import java.util.List;

import problems.*;
import suppliers.*;
import utils.refs.IntRange;

/**
 * @author Sam Hooper
 *
 */
public class Multiply25Supplier extends SettingsProblemSupplier {
	
	private static final int MIN_DIGITS = 1, MAX_DIGITS = 4, LOW_DIGITS = 1, HIGH_DIGITS = 3;
	private final NamedSetting<IntRange> digits;
	
	public Multiply25Supplier() {
		this(LOW_DIGITS, HIGH_DIGITS);
	}
	
	public Multiply25Supplier(final int lowDigits, final int highDigits) {
		digits = NamedSetting.of(new IntRange(MIN_DIGITS, MAX_DIGITS, lowDigits, highDigits), "Digits in non-25 term");
		addAllSettings(digits);
	}

	@Override
	public Problem get() {
		int term = Problem.intWithDigits(lowDigits(), highDigits());
		return SimpleExpression.multiplyTerms(Problem.shuffled(term, 25));
	}
	
	public int lowDigits() {
		return digits.ref().getLow();
	}
	
	public int highDigits() {
		return digits.ref().getHigh();
	}

	@Override
	public String getName() {
		return "Multiply by 25";
	}
	
	
	
}
