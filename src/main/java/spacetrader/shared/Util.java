package spacetrader.shared;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.BinomialDistribution;
import org.apache.commons.math3.distribution.UniformRealDistribution;

/**
 * Miscellaneous methods
 */
public class Util {

	/**
	 * Sample ONCE from a normal distribution. If you want to sample
	 * more than once, it would be better to instantiate a new
	 * NormalDistribution and sample from that.
	 * 
	 * @param mean
	 * @param sd must be positive
	 */
	public static double sampleFromNormal(double mean, double sd) {
		
		if (sd <= 0) {
			throw new IllegalArgumentException("sd must be positive");
		}

		NormalDistribution distr = new NormalDistribution(mean, sd);
		return distr.sample();
	}

	/**
	 * Sample ONCE from a binomial distribution. If you want to sample
	 * more than once, it would be better to instantiate a new
	 * BinomialDistribution and sample from that.
	 * 
	 * @param trials Number of trials; positive
	 * @param p Probability of success; in (0, 1)
	 */
	public static double sampleFromBinomial(int trials, double p) {

		if (trials <= 0) {
			throw new IllegalArgumentException("trials must be positive");
		}
		if (p < 0 || p > 1) {
			throw new IllegalArgumentException("p must be in the range (0, 1)");
		}

		BinomialDistribution distr = new BinomialDistribution(trials, p);
		return distr.sample();
	}

	/**
	 * Sample ONCE from a normal distribution. If you want to sample
	 * more than once, it would be better to instantiate a new
	 * UniformRealDistribution and sample from that.
	 * 
	 * @param lower Lower bound of the uniform distro
	 * @param upper Upper bound of the uniform distro
	 */
	public static double sampleFromUniformReal(double lower, double upper) {

		UniformRealDistribution distr = new UniformRealDistribution(lower, upper);
		return distr.sample();
	}
}