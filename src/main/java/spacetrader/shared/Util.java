package spacetrader.shared;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    
    /**
     * Sorts a List according to values stored in a Map. 
     * 
     * For example, if list contains
     * the five U.S. States "Florida", "Georgia", "Alabama", Mississippi", "South 
     * Carolina" in that order, and values maps those U.S. states to the year of 
     * their admission to the union: Florida => 1845, Georgia => 1788, Alabama => 1819
     * Mississippi => 1817, Louisiana => 1812; then after this method is called,
     * list will be sorted as follows: Georgia, Louisiana, Mississippi, Alabama,
     * Florida.
     * 
     * For several reasons, before any sorting occurs, the "values" Map is converted
     * to a new Map that maps keys to Doubles, rather than Numbers. For this reason,
     * VALUES SHOULD ONLY CONTAIN NUMBERS THAT CAN BE LOSSLESSLY CONVERTED TO DOUBLES.
     * Such Numbers are: Byte, Short, Integer, Char, Long, Float, or Double. This 
     * excludes BigInteger and BigDecimal in the standard Java API.
     * 
     * @param <T>
     * @param list non-null
     * @param values non-null
     */
    public static <T> void sortUsingValuesInMap(
    List<T> list, Map<T, ? extends Number> values) {
        
        if (list == null || values == null) return;
        
        Map<T, Double> doubleValues = new HashMap();
        for (T key : values.keySet()) {
            doubleValues.put(key, values.get(key).doubleValue());
        }
        Collections.sort(list, (T t1, T t2) -> {
            Double v1 = doubleValues.get(t1);
            Double v2 = doubleValues.get(t2);
            if (v1 > v2) return 1;
            if (v1 < v2) return -1;
            return 0;
        });
    }
    
    public static <K, V> void unionListMaps(
    Map<K, List<V>> map, 
    Map<? extends K, ? extends List<V>> withMap) {
        
        withMap.forEach((k, v) -> {
            List<V> old = map.get(k);
            if (old != null) {
                for (V item : v) {
                    if (!old.contains(item)) {
                        old.add(item);
                    }
                }
            } else {
                map.put(k, v);
            }
        });
    }
   
}