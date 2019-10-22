package ProductModel;

import java.util.List;

public class ProductRules {

    public static ThreadLocal<Integer> id = new ThreadLocal<>();
    public static ThreadLocal<String> name = new ThreadLocal<>();
    public static ThreadLocal<String> description = new ThreadLocal<>();
    public static ThreadLocal<Double> interest_rate = new ThreadLocal<>();
    public static ThreadLocal<Double> baseInterestRate = new ThreadLocal<>();
    public static ThreadLocal<Double> additionalProductInterestRate = new ThreadLocal<>();
    public static ThreadLocal<Double> highCreditScoreInterestOffsetRate = new ThreadLocal<>();
    public static ThreadLocal<Double> lowCreditScoreInterestOffsetRate = new ThreadLocal<>();
    public static ThreadLocal<Integer> discountQualifyingCreditScore = new ThreadLocal<>();
    public static ThreadLocal<Integer> minimumCreditScore = new ThreadLocal<>();
    public static ThreadLocal<List<String>> disqualifiedStates = new ThreadLocal<>();
    public static ThreadLocal<Boolean> disqualified = new ThreadLocal<>();

    public enum RuleTypes
    {
        BaseInterestRate,
        HighCreditScoreInterestOffsetRate,
        LowCreditScoreInterestOffsetRate,
        DiscountQualifyingCreditScore,
        AdditionalInterestRate,
        DisqualifiedStates
    }

}
