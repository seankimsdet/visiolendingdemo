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

    private final static String filePath = "src/main/resources/productRules.json";

    public RulesEngine() {
    }

    public static HashMap loadRules() {

        double baseInterestRate = 0;
        double highCreditScoreInterestOffsetRate = 0;
        double lowCreditScoreInterestOffsetRate = 0;
        double additionalProductInterestRate = 0;
        Integer goodCreditScore = 0;
        JSONObject productRules;
        ArrayList<String> disqualifiedStates = null;

        Product product = new Product();

        try {

            //load product-base rules from productRules.json file
            JSONObject baseRules = getProductRules("BASE");
            baseInterestRate = (double) baseRules.get("baseInterestRate");
            highCreditScoreInterestOffsetRate = (double) baseRules.get("highCreditScoreInterestOffsetRate");
            lowCreditScoreInterestOffsetRate = (double) baseRules.get("lowCreditScoreInterestOffsetRate");
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
        product.setProductDisqualifiedStatesRule(disqualifiedStates);

        //store and return product-base and product-specific rules as HashMap
        HashMap hm = new HashMap();
        hm.put(RuleTypes.BaseInterestRate, product.getBaseInterestRate());
        hm.put(RuleTypes.HighCreditScoreInterestOffsetRate, product.getHighCreditScoreInterestOffsetRule());
        hm.put(RuleTypes.LowCreditScoreInterestOffsetRate, product.getLowCreditScoreInterestOffsetRule());
        hm.put(RuleTypes.DiscountQualifyingCreditScore, product.getDiscountQualifyingCreditScoreRule());
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

    private double getInterestRateOffset(IPerson person, IProduct product, HashMap rules) {

        double interestOffset;
        Integer creditScore;
        Integer discountQualifyingCreditScore;

        creditScore = person.getCreditScore();
        discountQualifyingCreditScore = (Integer) rules.get(RuleTypes.DiscountQualifyingCreditScore);

        interestOffset = creditScore >= discountQualifyingCreditScore
                ? (double) rules.get(RuleTypes.HighCreditScoreInterestOffsetRate)
                : (double) rules.get(RuleTypes.LowCreditScoreInterestOffsetRate);

        double productInterestOffset = product.getAdditionalProductInterestRate();

        interestOffset = interestOffset + productInterestOffset;

        return interestOffset;
    }

    private boolean checkIfIsDisqualified(IPerson person, IProduct product) {

        boolean isDisqualified = true;
        String state = person.getState();

        List<String> disQualifiedStates = product.getProductDisqualifiedStates();
        if(state != null && !disQualifiedStates.contains(state)) {
            isDisqualified = false;
        }

        return isDisqualified;
    }

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
