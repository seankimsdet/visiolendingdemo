package PersonModel;

public class Person implements IPerson {

    private ThreadLocal<String> state = new ThreadLocal<>();
    private ThreadLocal<Integer> credit_score = new ThreadLocal<>();

    public Person() {
    }

    public Person(Integer credit_score, String state) {
        setCreditScore(credit_score);
        setState(state);
    }

    @Override
    public Integer getCreditScore() {
        return credit_score.get();
    }

    @Override
    public void setCreditScore(Integer creditScore) {
        this.credit_score.set(creditScore);
    }

    @Override
    public String getState() {
        return state.get();
    }

    @Override
    public void setState(String state) {
        this.state.set(state);
    }

}
