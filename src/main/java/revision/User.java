package revision;

public class User {
    private String username;
    private int age;
    private int numberOfPost;
    private String group;

    public User(String username, int age, int numberOfPost, String group) {
        this.username = username;
        this.age = age;
        this.numberOfPost = numberOfPost;
        this.group = group;
    }

    public String getUsername() {
        return username;
    }

    public int getAge() {
        return age;
    }

    public int getNumberOfPost() {
        return numberOfPost;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "User " +  username + " is a member of " + group + " group and is " + age + " years old.";

    }
}


