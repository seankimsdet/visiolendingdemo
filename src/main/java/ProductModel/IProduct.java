package ProductModel;

import java.util.List;

public interface IProduct {

    String getName();

    void setName(String name);

    void setBaseInterestRateRule(double baseInterestRate);

    double getBaseInterestRate();

    void setAdditionalProductInterestRate(double addedInterestRate);

    double getAdditionalProductInterestRate();

    double getProductInterestRate();

    boolean getIsDisqualified();

    void setProductInterestRate(double productInterestRate);

    void setIsDisqualified(boolean disqualified);

    Integer getDiscountQualifyingCreditScoreRule();

    void setDiscountQualifyingCreditScoreRule(Integer goodCreditScore);

    void setHighCreditScoreInterestOffsetRule(double interestOffsetRate);

    double getHighCreditScoreInterestOffsetRule();

    void setLowCreditScoreInterestOffsetRule(double interestOffsetRate);

    double getLowCreditScoreInterestOffsetRule();

    List<String> getProductDisqualifiedStates();

    void setProductDisqualifiedStatesRule(List<String> disqualifiedStates);

    void setMinimumCreditScore(Integer minimumCreditScore);

    Integer getMinimumCreditScoreRules();
}
