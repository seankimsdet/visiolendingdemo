package ProductModel;

import java.util.List;

public class Product extends ProductRules implements IProduct {

    public Product() {
    }

    public Product(String productName, double addedInterestRate) {
        setName(productName);
        setAdditionalProductInterestRate(addedInterestRate);
    }

    @Override
    public String getName() {
        return ProductRules.name.get();
    }

    @Override
    public void setName(String name) {
        ProductRules.name.set(name);
    }

    @Override
    public void setBaseInterestRateRule(double baseInterestRate) {
        ProductRules.baseInterestRate.set(baseInterestRate);
    }

    @Override
    public double getBaseInterestRate() {
        return ProductRules.baseInterestRate.get();
    }

    @Override
    public double getAdditionalProductInterestRate() {
        return ProductRules.additionalProductInterestRate.get();
    }

    @Override
    public void setAdditionalProductInterestRate(double addedInterestRate) {
        ProductRules.additionalProductInterestRate.set(addedInterestRate);
    }

    @Override
    public double getProductInterestRate() {
        return ProductRules.interest_rate.get();
    }

    @Override
    public void setProductInterestRate(double interest_rate) {
        ProductRules.interest_rate.set(interest_rate);
    }

    @Override
    public Integer getDiscountQualifyingCreditScoreRule() {
        return ProductRules.discountQualifyingCreditScore.get();
    }

    @Override
    public void setDiscountQualifyingCreditScoreRule(Integer goodCreditScore) {
        ProductRules.discountQualifyingCreditScore.set(goodCreditScore);
    }

    @Override
    public void setHighCreditScoreInterestOffsetRule(double interestOffsetRate) {
        ProductRules.highCreditScoreInterestOffsetRate.set(interestOffsetRate);
    }

    @Override
    public double getHighCreditScoreInterestOffsetRule() {
        return ProductRules.highCreditScoreInterestOffsetRate.get();
    }

    @Override
    public void setLowCreditScoreInterestOffsetRule(double interestOffsetRate) {
        ProductRules.lowCreditScoreInterestOffsetRate.set(interestOffsetRate);
    }

    @Override
    public double getLowCreditScoreInterestOffsetRule() {
        return ProductRules.lowCreditScoreInterestOffsetRate.get();
    }

    @Override
    public List<String> getProductDisqualifiedStates() {
        return ProductRules.disqualifiedStates.get();
    }

    @Override
    public void setProductDisqualifiedStatesRule(List<String> disqualifiedStates) {
        ProductRules.disqualifiedStates.set(disqualifiedStates);
    }

    @Override
    public boolean getIsDisqualified() {
        return ProductRules.disqualified.get();
    }

    @Override
    public void setIsDisqualified(boolean disqualified) {
        ProductRules.disqualified.set(disqualified);
    }

}
