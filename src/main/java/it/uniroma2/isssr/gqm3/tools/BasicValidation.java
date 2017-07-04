package it.uniroma2.isssr.gqm3.tools;

import it.uniroma2.isssr.gqm3.model.Metric;

public class BasicValidation {

	/**
	 * Basic validation on measure value depends on metric defined in GQM+Strategies Tool Phase 2.2b
	 * 
	 * @param metric
	 * @param value
	 * @return true if the value is valid, false otherwise
	 */
	public static boolean isValid(Metric metric, String value) {

		double valueDouble;
		int valueInt;

		if (metric.getHasUserDefinedList()) {
			for (String userDefined : metric.getUserDefinedList()) {
				if (userDefined.equals(value))
					return true;
			}
			return false;
		} else if (metric.getSet().equals(Costants.REALS)) {

			valueDouble = Double.parseDouble(value);

			if (metric.getHasMax() && valueDouble > metric.getMax())
				return false;
			else if (metric.getHasMin() && valueDouble < metric.getMin())
				return false;
			else
				return true;

		} else if (metric.getSet().equals(Costants.INTEGERS)) {

			valueInt = Integer.parseInt(value);

			if (metric.getHasMax() && valueInt > metric.getMax())
				return false;
			else if (metric.getHasMin() && valueInt < metric.getMin())
				return false;
			else
				return true;
		}

		return false;
	}

}
