package utils;
/** Dummy class that is similar in functionality to maxipago **/
public class PaymentUtils {
	public enum BANK_RESPONSE {APPROVED, DECLINED, NOT_ENOUGH_FUNDS};
	static float account3 = 20000;
	public static BANK_RESPONSE authorize(String number, String cvv, float fund) {
		if (number.equals("4716108999716531") && cvv.equals("257")) {
			return BANK_RESPONSE.APPROVED;
		} else if (number.equals("5281037048916168") && cvv.equals("043")) {
			if (fund <= 10000) {
				return BANK_RESPONSE.APPROVED;
			} else {
				return BANK_RESPONSE.NOT_ENOUGH_FUNDS;
			}
		} else if (number.equals("342498818630298") && cvv.equals("3156")) {
			if (fund <= account3) {
				return BANK_RESPONSE.APPROVED;
			} else {
				return BANK_RESPONSE.NOT_ENOUGH_FUNDS;
			}
		} else if (number.equals("378282246310005") && cvv.equals("233")) {
			return BANK_RESPONSE.NOT_ENOUGH_FUNDS;
		} else {
			return BANK_RESPONSE.DECLINED;
		}
	}
	public static void capture(String number, String cvv, float fund) {
		if (number.equals("342498818630298") && cvv.equals("3156") && fund <= account3) {
			account3 -= fund;
		}
	}
	public static float get_account3() {
		return account3;
	}
}
