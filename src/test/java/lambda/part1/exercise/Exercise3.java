package lambda.part1.exercise;

import com.google.common.collect.FluentIterable;
import lambda.data.Person;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.is;

@SuppressWarnings({"ConstantConditions", "unused", "ComparatorCombinators", "Guava"})
class Exercise3 {

    @Test
    void sortPersonsByAgeUsingArraysSortExpressionLambda() {
        Person[] persons = getPersons();

        Arrays.sort(persons, (p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()));

        assertThat(persons, is(arrayContaining(
                new Person("Иван", "Мельников", 20),
                new Person("Николай", "Зимов", 30),
                new Person("Алексей", "Доренко", 40),
                new Person("Артем", "Зимов", 45)
        )));
    }

    @Test
    void sortPersonsByLastNameThenFirstNameUsingArraysSortStatementLambda() {
        Person[] persons = getPersons();

        Arrays.sort(persons, (p1, p2) -> {
            int i = p1.getLastName().compareToIgnoreCase(p2.getLastName());
            return i == 0 ? p1.getFirstName().compareToIgnoreCase(p2.getFirstName()) : i;
        });

        assertThat(persons, is(arrayContaining(
                new Person("Алексей", "Доренко", 40),
                new Person("Артем", "Зимов", 45),
                new Person("Николай", "Зимов", 30),
                new Person("Иван", "Мельников", 20)
        )));
    }

    @Test
    void findFirstWithAge30UsingGuavaPredicateLambda() {
        List<Person> persons = Arrays.asList(getPersons());

        Person person = FluentIterable.from(persons)
                                      .firstMatch(p -> 30 == p.getAge())
                                      .orNull();

        assertThat(person, is(new Person("Николай", "Зимов", 30)));
    }

    private Person[] getPersons() {
        return new Person[]{
                new Person("Иван", "Мельников", 20),
                new Person("Алексей", "Доренко", 40),
                new Person("Николай", "Зимов", 30),
                new Person("Артем", "Зимов", 45)
        };
    }
}
