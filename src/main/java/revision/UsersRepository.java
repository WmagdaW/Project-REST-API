package revision;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class UsersRepository {
    public static List<User> getUsersList() {
        List<User> users = new ArrayList<>();
        users.add(new User("Walter White", 50, 7, "Chemists"));
        users.add(new User("Jessie Pinkman", 25, 4648, "Sales"));
        users.add(new User("Tuco Salamanca", 34, 116, "Manager"));
        users.add(new User("Gus Firing", 49, 0, "Board"));
        users.add(new User("Gale Boetticher", 44, 2, "Chemists"));
        users.add(new User("Mike Ehrmantraut", 57, 0, "Security"));
        return users;
    }

    public static void main(String[] args) {

        List<String> usersList = UsersRepository.getUsersList().stream()
                .filter(user -> user.getNumberOfPost() < 5)
                .map(user -> user.getUsername().toUpperCase(Locale.ROOT) + " " + user.getAge())
                .collect(Collectors.toList());

        System.out.println(usersList);

        Map<String, User> usersMap = UsersRepository.getUsersList().stream()
                .filter(user -> user.getNumberOfPost() < 5 && user.getAge() > 30)
                .collect(Collectors.toMap(user -> user.getUsername(), user -> user));

        if (usersMap.containsKey("Gus Firing")) {
            System.out.println(usersMap.get("Gus Firing"));

        }
    }

}
