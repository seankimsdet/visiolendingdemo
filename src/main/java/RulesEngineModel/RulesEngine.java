package RulesEngineModel;

import PersonModel.IPerson;
import ProductModel.IProduct;
import ProductModel.Product;
import ProductModel.ProductRules;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class RulesEngine extends ProductRules implements IRulesEngine {

    private final static String filePath = Utils.RulesFileName.getValue();

    public RulesEngine() {
    }

    public static HashMap loadRules() {

        double baseInterestRate = 0;
        double highCreditScoreInterestOffsetRate = 0;
        double lowCreditScoreInterestOffsetRate = 0;
        double additionalProductInterestRate = 0;
        Integer goodCreditScore = 0;
        Integer minimumCreditScore = 0;
        JSONObject productRules;
        ArrayList<String> disqualifiedStates = null;

        Product product = new Product();

        try {

            //load product-base rules from productRules.json file
            JSONObject baseRules = getProductRules("BASE");
            baseInterestRate = (double) baseRules.get("baseInterestRate");
            highCreditScoreInterestOffsetRate = (double) baseRules.get("highCreditScoreInterestRateOffset");
            lowCreditScoreInterestOffsetRate = (double) baseRules.get("lowCreditScoreInterestRateOffset");
            goodCreditScore = (Integer) baseRules.get("goodCreditScore");
            JSONArray states = (JSONArray) baseRules.get("disqualifiedStates");

            disqualifiedStates = new ArrayList<>();
            if(states != null) {
                for(int i = 0; i < states.length(); i++) {
                    disqualifiedStates.add(states.getString(i));
                }
            }

            //load product-specific rules from productRules.json file
            productRules = getProductRules(product.getName());
            minimumCreditScore = (Integer) productRules.get("minimumCreditScore");
            additionalProductInterestRate = (double) productRules.get("additionalProductInterestRate");

        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

        //set values for ThreadLocal variable
        product.setBaseInterestRateRule(baseInterestRate);
        product.setHighCreditScoreInterestOffsetRule(highCreditScoreInterestOffsetRate);
        product.setLowCreditScoreInterestOffsetRule(lowCreditScoreInterestOffsetRate);
        product.setDiscountQualifyingCreditScoreRule(goodCreditScore);
        product.setAdditionalProductInterestRate(additionalProductInterestRate);
        product.setMinimumCreditScore(minimumCreditScore);
        product.setProductDisqualifiedStatesRule(disqualifiedStates);

        //store and return product-base and product-specific rules as HashMap
        HashMap hm = new HashMap();
        hm.put(RuleTypes.BaseInterestRate, product.getBaseInterestRate());
        hm.put(RuleTypes.HighCreditScoreInterestRateOffset, product.getHighCreditScoreInterestOffsetRule());
        hm.put(RuleTypes.LowCreditScoreInterestRateOffset, product.getLowCreditScoreInterestOffsetRule());
        hm.put(RuleTypes.DiscountQualifyingCreditScore, product.getDiscountQualifyingCreditScoreRule());
        hm.put(RuleTypes.MinimumCreditScore, product.getMinimumCreditScoreRules());
        hm.put(RuleTypes.AdditionalInterestRate, product.getAdditionalProductInterestRate());
        hm.put(RuleTypes.DisqualifiedStates, product.getProductDisqualifiedStates());

        return hm;
    }


    @Override
    public void runRules(IPerson person, IProduct product, HashMap rules) {

        double interestRate;

        boolean isDisqualified = checkIfIsDisqualified(person, product);
        interestRate = product.getBaseInterestRate();
        double offsetRate = getInterestRateOffset(person, product, rules);
        interestRate = interestRate + offsetRate;

        product.setProductInterestRate(interestRate);
        product.setIsDisqualified(isDisqualified);
    }

    //Determine interest rate offset value
    private double getInterestRateOffset(IPerson person, IProduct product, HashMap rules) {

        Integer creditScore = person.getCreditScore();
        Integer discountQualifyingCreditScore = (Integer) rules.get(RuleTypes.DiscountQualifyingCreditScore);

        //based on person's credit score being higher/equal or lower than the discount qualifying credit score
        //applicable interest rate offset value from rules(HashMap) is assigned to interestOffset variable
        double interestOffset = creditScore >= discountQualifyingCreditScore
                ? (double) rules.get(RuleTypes.HighCreditScoreInterestRateOffset)
                : (double) rules.get(RuleTypes.LowCreditScoreInterestRateOffset);

        //get additional interest rate offset value for the product
        double productInterestOffset = product.getAdditionalProductInterestRate();

        interestOffset = interestOffset + productInterestOffset;

        return interestOffset;
    }

    //Check if applicant is disqualified due to person's residing State or credit score, based on the product's rules
    private boolean checkIfIsDisqualified(IPerson person, IProduct product) {

        //disqualified by default
        boolean isDisqualified = true;

        String state = person.getState();
        Integer creditScore = person.getCreditScore();
        Integer minCreditScoreRule = product.getMinimumCreditScoreRules();

        List<String> disqualifiedStatesRule = product.getProductDisqualifiedStates();

        // qualified if disqualifiedStatesRule list does not contain person's state
        if(state != null && !disqualifiedStatesRule.contains(state)) {
            isDisqualified = false;

            // disqualified if person's credit score is lower then minCreditScoreRule
            if(creditScore < minCreditScoreRule) {
                isDisqualified = true;
            }
        }

        return isDisqualified;
    }

    //Get JSONObject based on the product name
    private static JSONObject getProductRules(String productName) {

        JSONArray jsonArray = parseJSONFile();
        JSONObject jsonObject;

        for(int i = 0; i < jsonArray.length(); i++) {
            jsonObject = jsonArray.getJSONObject(i);
            String ruleName = (String) jsonObject.get("name");

            if(ruleName.equals(productName)) {
                return jsonObject;
            }
        }
        return null;
    }

    //Get JSONArray from JSON file in resources (productRules.json)
    private static JSONArray parseJSONFile() {

        String content = null;

        try {
            content = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new JSONArray(content);
    }

}
