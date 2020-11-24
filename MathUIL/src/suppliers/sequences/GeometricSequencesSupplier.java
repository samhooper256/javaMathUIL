package suppliers.sequences;

import static problems.Problem.*;
import static suppliers.NamedIntRange.*;

import math.*;
import problems.*;
import suppliers.*;

/**
 * @author Sam Hooper
 *
 */
public class GeometricSequencesSupplier extends SettingsProblemSupplier {
	
	private static final int MIN_NTH_TERM = 6, MAX_NTH_TERM = 12;
	private static final RangeStore S_NUM = RangeStore.of(1, 10), S_DENOM = RangeStore.of(1, 10), R_DENOM = RangeStore.of(2, 10, 2, 8);
	private final NamedIntRange sNum = of(S_NUM, "Numerator of first term"), sDenom = of(S_DENOM, "Denominator of first term"), rDenom = of(R_DENOM, "Denominator of ratio");
	
	public GeometricSequencesSupplier() {
		settings(sNum, sDenom, rDenom);
	}
	
	@Override
	public Problem get() {
//		System.out.printf("enter GSS::get()%n");
		BigFraction r = BigFraction.of(1, intInclusive(rDenom));
		if(Math.random() <= 0.5)
			r = r.negate();
		GeometricSequence seq = new GeometricSequence(BigFraction.of(intInclusive(sNum), intInclusive(sDenom)), r);
		if(Math.random() <= 0.5)
			return sumProblem(seq);
		else
			return termProblem(seq);
	}

	/**
	 * @param seq
	 * @return
	 */
	private Problem termProblem(GeometricSequence seq) {
//		System.out.printf("enter GSS::termProblem(seq)%n");
		int termIndex = intInclusive(MIN_NTH_TERM, MAX_NTH_TERM);
		final BigFractionValued prob = BigFractionValued.of(String.format("What is the %d%s term of the sequence %s?", termIndex, Prettifier.ordinalSuffix(termIndex), seq.toPartialString(4)), seq.nthTerm(termIndex));
//		System.out.printf("exit GSS::termProblem(seq)%n");
		return prob;
	}

	/**
	 * @param seq
	 * @return
	 */
	private Problem sumProblem(GeometricSequence seq) {
//		System.out.printf("enter GSS::sumProblem(seq)%n");
		return BigFractionValued.of(String.format("What is the sum of the sequence %s?", seq.toPartialString(3)), seq.sum());
	}
	
	
	
	
}
