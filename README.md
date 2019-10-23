# Visio Lending Coding Challenge

---

### Table of Contents
- [Description](#description)
- [Expectations](#expectations)
- [Testing](#testing)
- [References](#references)
- [License](#license)
- [Additional Info](#additional-info)

---

## Description

A stakeholder at Visio Financial Services wants a solution that allows the business to dynamically generating product pricing from a set rules defined by the finance team.
<br>
The finance team has given you an initial set of rules on how to price the products, however, these rules could change at any time so we need to be able to update the rules easily and rerun the product pricing to see the new prices of the products.

#### Initial Rules:

- All products start at 5.0 interest_rate.
- If the person lives in Florida (condition), we do not offer the product to them and the product is to be disqualified (action).
- If the person has a credit score greater than or equal to 720(condition) then we reduce the interest_rate on the product by .3 (action that has an input of “.3”, remember the business may decide in the future they want to reduce it by .5).
- If the person has a credit score lower than 720 we increase the interest_rate on the product by .5.
- If the name of the product is “7-1 ARM” then we need to add .5 to the interest_rate of the product.

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

- [ ] Build a rules engine to accomplish what the business has asked above.
- [ ] Exhaustively test all possible rule outcomes and assert that the rules engine works as expected and meets the business requirements.
- [ ] Show the extendability of your rules engine and create your own rules definition to execute an additional action on a product.
- [ ] Upload your solution to github giving instructions on how to run the rules engine and any tests.
- [ ] Finally, be prepared to lead a review on the submitted code to discuss any patterns or design decisions you made.

[Back To The Top](#visio-lending-coding-challenge)

---

## Testing


[Back To The Top](#visio-lending-coding-challenge)

---

## References

[Back To The Top](#visio-lending-coding-challenge)

---

## License
<sup>The MIT License (MIT)

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
SOFTWARE.</sup>

[Back To The Top](#visio-lending-coding-challenge)

---

## Additional Info
- GitHub Repo: [github.com/seankimsdet/visiolendingdemo](https://github.com/seankimsdet/visiolendingdemo)
- Author Profile: [seankimsdet.github.io](https://seankimsdet.github.io)

[Back To The Top](#visio-lending-coding-challenge)

<br>
