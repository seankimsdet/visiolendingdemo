package RulesEngineModel;

import PersonModel.IPerson;
import ProductModel.IProduct;
import java.util.HashMap;

public interface IRulesEngine {

    void runRules(IPerson person, IProduct product, HashMap rules);
}
