import org.junit.jupiter.params.provider.ArgumentsSource;

import java.lang.annotation.*;

public class TestBase {

    @Documented
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @ArgumentsSource(VariableArgumentsProvider.class)
    public @interface VariableSource {

        /**
         * The name of the static variable
         */
        String value();
    }
}
