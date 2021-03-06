package suppliers.bases;

import static problems.Problem.*;
import static suppliers.NamedIntRange.*;

import math.Utils;
import problems.*;
import suppliers.*;

/**
 * Converting from base a to base a^n (n is a whole number).
 * @author Sam Hooper
 *
 */
public class BaseAToPowerOfASupplier extends SettingsProblemSupplier {
	
	private static final RangeStore VALUE = RangeStore.of(1, 10_000, 2, 200);
	
	private static int[] generateBases() {
		int ran = RAND.nextInt(3);
		if(ran == 0)
			return switch(RAND.nextInt(3)) {
				case 0 -> Problem.shuffled(2, 4);
				case 1 -> Problem.shuffled(2, 8);
				default -> Problem.shuffled(2, 16);
			};
		else if(ran == 1)
			return Problem.shuffled(3, 9);
		else
			return Problem.shuffled(4, 16);
	}
	
	private final NamedIntRange value = of(VALUE, "Base 10 value of number");
	
	public BaseAToPowerOfASupplier() {
		addAllSettings(value);
	}

	@Override
	public Problem get() {
		String v = Integer.toString(intInclusive(value));
		int[] bases = generateBases();
		String base1 = Utils.convertBase(v, 10, bases[0]), base2 = Utils.convertBase(v, 10, bases[1]);
		return Builder.of(String.format("What is %s in base %d?", Prettifier.ensureMath(Prettifier.base(base1, bases[0])), bases[1]))
				.addBaseResult(base2, bases[1]).build();
	}

	@Override
	public String getName() {
		return "Base a to a^n";
	}	
	
	
	
	
}
