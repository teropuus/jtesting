/**
 * @author Juha Peltomäki
 *
  *
 * Counting avarages and sum for Person objects in ArrayList using lambda
 * expressions.
 */
package lambdas;

import java.util.List;
import java.util.Arrays;
import java.util.OptionalDouble;

class User {

    private String name;
    public int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

public class MapReduceExample {

    public static void main(String[] args) {
        List<User> users = Arrays.asList(
                new User("jack", 40),
                new User("john", 32),
                new User("jill", 47),
                new User("mike", 28));
        System.out.println("Counting avg using for loop:");
        System.out.println(countAvarage(users));
        System.out.println("Counting avg using lambda expression:");
        System.out.println(countAvarageWithLambda(users));
        System.out.println("Counting sum using lambda expression:");
        System.out.println(countSumWithReduce(users));

    }

    private static double countAvarage(List<User> users) {
        int total = 0;

        for (User u : users) {
            total += u.getAge();
        }

        return (double) total / users.size();
    }

    private static double countAvarageWithLambda(List<User> users) {
        OptionalDouble val = users.stream()
            .filter(u -> u.getAge() > 0)
            .mapToDouble(u -> u.getAge())
            .average();

        return val.getAsDouble();
    }

    private static double countSumWithReduce(List<User> users) {
        return users.stream()
                .reduce(new User(),
                        (User n1, User n2) -> new User("", n1.getAge() + n2.getAge()))
                .getAge();
    }

}

