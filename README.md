# Visio Lending Coding Challenge


#### Table of Contents
- [Business Requirements](#business-requirements)
- [Functional Requirements](#functional-requirements)
- [Testing](#testing)
- [References](#references)
- [License](#license)
- [Additional Info](#additional-info)

---

## Business Requirements

>#### Summary
>A stakeholder at Visio Financial Services wants a solution that allows the business to dynamically generating product pricing from a set rules defined by the finance team.
<br>
The finance team has given you an initial set of rules on how to price the products, however, these rules could change at any time so we need to be able to update the rules easily and rerun the product pricing to see the new prices of the products.
<br>
>#### Initial Rules:
>- All products start at 5.0 interest_rate.
>- If the person lives in Florida (condition), we do not offer the product to them and the product is to be disqualified (action).
>- If the person has a credit score greater than or equal to 720(condition) then we reduce the interest_rate on the product by .3 (action that has an input of “.3”, remember the business may decide in the future they want to reduce it by .5).
>- If the person has a credit score lower than 720 we increase the interest_rate on the product by .5.
>- If the name of the product is “7-1 ARM” then we need to add .5 to the interest_rate of the product.

---

## Functional Requirements
>
>#### Base Product Rules values
>* product id, name, description
>* base interest rate
>* high credit score interest rate offset
>* low credit score interest rate offset
>* discount qualifying credit score
>* list of disqualifying States
>
>#### Product Rules values:
>* product id, name, description
>* product-specific interest rate offset
>* product-specific minimum qualifying credit score
> 
>#### Rules Definition:
>* Needs to be extendable and defined outside of the code.
>* Should define an action, any parameters an action needs, and under what condition to execute the action.

---

## Validation

>#### Product Qualification:
>* Validate qualification based on the person's residing state.
>   * Person should be qualified if person's residing state is not in base rule's disqualified states list.
>       * Qualified person, based on residing state, should be disqualified if the credit score is lower than the minimum credit score required for the product.
>   * Person should be disqualified if the person's residing state is in base rule's disqualified states list.
>       * Disqualified person, based on residing state, should not be qualified based on meeting or exceeding the minimum credit score required for the product.
>
>#### Product Interest Rate Offset:
>* Validate interest rate offset based on person's credit score.
>   * Product interest rate should be discounted if the person's credit score greater or equaled to the defined discount qualifying credit score.
>   * Product interest rate should be increased if the person's credit score is lower then the defined discount qualifying credit score.
>* Validate interest rate offset based on product.
>   * Product interest rate should be adjusted accordingly based on the product's 'additionalProductInterestRate' value.


#### Example:
```
    class Person:
        credit_score: integer
        state: string

    class Product:
        name: string
        interest_rate: decimal
        disqualified: boolean

    class RulesEngine
        def runRules(person, product, rules)

    person = new Person(720, 'Florida')
    product = new Product('7-1 ARM', 5.0)
    rules_engine = new RulesEngine()
    rules = loadRules() // Rules are loaded at runtime!
    rules_engine.runRules(person, product, rules**);
```

> The output of the above code example should be:
> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; `product.interest_rate == 5.2 (5.0 - .3 + .5)`
> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; `product.disqualified == true`
> 
> `** Hint` We have not defined how the rules are represented, they could be json, csv, etc.
> <br>The only thing that matters is that rule definitions can be extended upon and defined outside of the code, the rule definitions should define an action, any parameters an action needs, and under what condition to execute the action.

[Back To The Top](#visio-lending-coding-challenge)

---

## Expectations

>- [x] Build a rules engine to accomplish what the business has asked above.
>- [ ] Exhaustively test all possible rule outcomes and assert that the rules engine works as expected and meets the business requirements.
>- [ ] Show the extendability of your rules engine and create your own rules definition to execute an additional action on a product.
>- [ ] Upload your solution to github giving instructions on how to run the rules engine and any tests.
>- [ ] Finally, be prepared to lead a review on the submitted code to discuss any patterns or design decisions you made.

[Back To The Top](#visio-lending-coding-challenge)

---

## References

[Back To The Top](#visio-lending-coding-challenge)

---

## License
    The MIT License (MIT)

    Copyright (c) (2019) Sean Kim

    Permission is hereby granted, free of charge, to any person obtaining a copy 
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.

[Back To The Top](#visio-lending-coding-challenge)

---

## Additional Info
>- GitHub Repo: [github.com/seankimsdet/visiolendingdemo](https://github.com/seankimsdet/visiolendingdemo)
>- Author Profile: [seankimsdet.github.io](https://seankimsdet.github.io)

[Back To The Top](#visio-lending-coding-challenge)

<br>
