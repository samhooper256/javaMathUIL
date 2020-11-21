package math;

import java.math.*;
import java.util.Objects;

/**
 * <p>A complex number, represented in the rectangular form <i>a+bi</i>, where <i>a</i> is the real part and <i>bi</i> is the imaginary part.</p>
 * @author Sam Hooper
 *
 */
public class Complex {
	
	public static final Complex ZERO = new Complex(0);
	/**
	 * The "a" in "a + bi"
	 */
	private final BigDecimal a;
	/**
	 * The "b" in "a + bi"
	 */
	private final BigDecimal b;
	
	/**
	 * The "a" and "b" parameters are those in the rectangular form "a + bi"
	 */
	public Complex(final BigDecimal a, final BigDecimal b) {
		this.a = Objects.requireNonNull(a);
		this.b = Objects.requireNonNull(b);
	}
	
	/**
	 * A {@link Complex} with only a real part.
	 */
	public Complex(final BigDecimal a) {
		this(a, BigDecimal.ZERO);
	}
	
	/**
	 * The "a" and "b" parameters are those in the rectangular form "a + bi"
	 */
	public Complex(final double a, final double b) {
		this(BigDecimal.valueOf(a), BigDecimal.valueOf(b));
	}
	
	/**
	 * The "a" parameter is the one in the rectangular form "a + bi". The imaginary part is {@code 0}.
	 */
	public Complex(final double a) {
		this(a, 0);
	}
	
	/**
	 * The "a" and "b" parameters are those in the rectangular form "a + bi"
	 */
	public Complex(final long a, final long b) {
		this(BigDecimal.valueOf(a), BigDecimal.valueOf(b));
	}
	
	/**
	 * The "a" parameter is the one in the rectangular form "a + bi". The imaginary part is {@code 0}.
	 */
	public Complex(final long a) {
		this(a, 0);
	}
	
	/**
	 * Takes a {@link String} either of the form "a+bi" (where a and b are valid {@link BigDecimal BigDecimals}) or "a" where
	 * a is a valid {@link BigDecimal}.
	 * @param abi
	 */
	public Complex(final String abi) {
//		System.out.printf("entered Complex(abi=%s)%n", abi);
		int p = abi.indexOf('+');
		if(p >= 0) {
			a = new BigDecimal(abi.substring(0, p));
			b = new BigDecimal(abi.substring(p + 1, abi.length() - 1));
		}
		else {
			a = new BigDecimal(abi);
			b = BigDecimal.ZERO;
		}
	}

	@Override
	public String toString() {
		if(b.compareTo(BigDecimal.ZERO) == 0)
			return String.format("%f", a);
		return String.format("%f+%fi", a, b);
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(a, b);
	}
	
	/**
	 * Returns {@code true} if the two {@link Complex} objects are equal in value, {@code false} otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null) {
			return false;
		}
		if(getClass() != obj.getClass()) {
			return false;
		}
		Complex other = (Complex) obj;
		return a.compareTo(other.a) == 0 && b.compareTo(other.b) == 0;
	}
	
	public Complex sum(Complex augend) {
		return new Complex(realPart().add(augend.realPart()), imaginaryPart().add(augend.imaginaryPart()));
	}
	
	public Complex sum(Complex augend, MathContext mc) {
		return new Complex(realPart().add(augend.realPart(), mc), imaginaryPart().add(augend.imaginaryPart(), mc));
	}
	
	public Complex multiply(Complex multiplicand, MathContext mc) {
		BigDecimal real = realPart().multiply(multiplicand.realPart(), mc).subtract(imaginaryPart().multiply(multiplicand.imaginaryPart(), mc), mc);
		BigDecimal im = realPart().multiply(multiplicand.imaginaryPart(), mc).add(imaginaryPart().multiply(multiplicand.realPart(), mc), mc);
		return new Complex(real, im);
	}

	public BigDecimal realPart() {
		return a;
	}
	
	public boolean hasRealPart() {
		return a.compareTo(BigDecimal.ZERO) != 0;
	}
	
	public BigDecimal imaginaryPart() {
		return b;
	}
	
	public boolean hasImaginaryPart() {
		return b.compareTo(BigDecimal.ZERO) != 0;
	}
	
	/**
	 * Returns {@code (this % divisor)}. {@code this} and {@code divisor} must not {@link #hasImaginaryPart() have an imaginary part}. The
	 * returned {@link Complex} will not have an imaginary part.
	 * 
	 * The remainder is given as described in {@link BigDecimal#remainder(BigDecimal)}, which is <i>not</i> to the modulo operation.
	 */
	public Complex remainder(final Complex divisor) {
		if(divisor.hasImaginaryPart())
			throw new IllegalArgumentException("Cannot find the remainder when the divisor has an imaginary part");
		return remainder(divisor.realPart());
	}
	
	public Complex remainder(final BigDecimal divisor) {
		if(hasImaginaryPart())
			throw new IllegalArgumentException("Cannot find the remainder when the dividend has an imaginary part");
		return new Complex(realPart().remainder(divisor));
	}
	
	public Complex remainder(final long divisor) {
		return remainder(BigDecimal.valueOf(divisor));
	}
	
	public long longValueExact() {
		if(hasImaginaryPart())
			throw new ArithmeticException("This complex number has an imaginary part, so it does not have an exact long value.");
		return a.longValueExact();
	}
	
	
}
