# US 007 - Register user in the system

# 4. Tests 
### Tests for RegisterUserController

* Fill the AnnouncementRepository


    UserRepository userRepository = new UserRepository();
    RegisterUserController controller =
            new RegisterUserController(userRepository);


    User u1 = new User(1,"user1","email1@gmail.com","password");
    User u2 = new User(2,"user2","email2@gmail.com","password");
    User u3 = new User(3,"user3","email3@gmail.com","password");
    User u4 = new User(4,"user4","email4@gmail.com","password");

**Test 1:** Check if it possible to add users.

    @Test
    void addUserSuccessfully() {
        controller.addUser(u1);
        controller.addUser(u2);
        controller.addUser(u3);
        controller.addUser(u4);

    }

**Test 2:** Check if a user isn´t add if the email already exist in repository

    @Test
    void addUserFailForRepeatedEmail() {
        User u5 = new User(5,"user4","email1@gmail.com","password");

        controller.addUser(u1);
        controller.addUser(u2);
        controller.addUser(u3);
        Optional<User> actual = controller.addUser(u5);


        Integer[] expected = {1,2,3};

        List<User> listusers = controller.getUsers();

        assertEquals("Optional.empty", actual.toString());

    }

**Test 3:** Check if it possible to get all users in the repository

    @Test
    void getUsers() {
        controller.addUser(u1);
        controller.addUser(u2);
        controller.addUser(u3);
        controller.addUser(u4);


        Integer[] expected = {1,2,3,4};
        Integer[] actual = new Integer[expected.length];
        List<User> listusers = controller.getUsers();

        int position =0;
        for (User user : listusers){
            actual[position] = user.getId();
            position++;
        }

        assertArrayEquals(expected, actual);
    }
	

### Tests for UserRepository
**Test 4:** Check if it possible to get all users in the repository

    @Test
    void addUser() {
    UserRepository ur = new UserRepository();
    List<User> users = ur.getUsers();

        User u1 = new User(1,"user1","email1@gmail.com","password");
        users.add(u1);
    }

**Test 5:** Check if it possible to get all users in the repository

    @Test
    void isAccountAlreadyRegisteredTrue() {
        UserRepository ur = new UserRepository();
        List<User> users = ur.getUsers();

        String email="email1@gmail.com";
        User u1 = new User(1,"user1","email1@gmail.com","password");
        User u2 = new User(2,"user2","email2@gmail.com","password");

        users.add(u1);
        users.add(u2);

        assertTrue(ur.isAccountAlreadyRegistered(email));
    }

**Test 6:** Check if it possible to get all users in the repository

    @Test
    void isAccountAlreadyRegisteredFalse() {
        UserRepository ur = new UserRepository();
        List<User> users = ur.getUsers();

        String email="email3@gmail.com";
        User u1 = new User(1,"user1","email1@gmail.com","password");
        User u2 = new User(2,"user2","email2@gmail.com","password");

        users.add(u1);
        users.add(u2);

        assertFalse(ur.isAccountAlreadyRegistered(email));
    }



# 5. Construction (Implementation)


## Class RegisterUserController

```java
public class RegisterUserController {

    private UserRepository userRepository = null;

    public RegisterUserController() {
        getUserRepository();

    }

    //Allows receiving the repositories as parameters for testing purposes
    public RegisterUserController(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    private UserRepository getUserRepository() {
        if (userRepository == null) {
            Repositories repositories = Repositories.getInstance();
            userRepository = repositories.getUserRepository();
        }
        return userRepository;

    }

    public List<User> getUsers() {

        UserRepository userRepo = getUserRepository();

        return userRepo.getUsers();
    }

    public Optional<User> addUser(User user) {
        UserRepository userRepository = getUserRepository();
        return userRepository.add(user);
    }




}

```


## Class User

```java
public class User {
    private int id;
    private String name;
    private String email;

    private String password;

    public User(Integer id, String name, String email, String password){
        this.id=id;
        this.name=name;
        this.email=email;
        this.password=password;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public User clone()  {
        return new User(id,name,email,password);

    }

}
```

### class UserRepository
````java
public class UserRepository {

    public List<User> getUsers() {return usersList;}

    private final List<User> usersList = new ArrayList<>();

    public boolean isAccountAlreadyRegistered(String email){

        for (User user: usersList) {
            if (user.getEmail().equalsIgnoreCase(email)){
                return true;
            }
        }

        return false;

    }

    public Optional<User> add(User user) {

        Optional<User> newUsers = Optional.empty();

        boolean operationSuccess = false;

        if (!isAccountAlreadyRegistered(user.getEmail())) {
            newUsers = Optional.of(user.clone());
            operationSuccess = usersList.add(newUsers.get());
        }

        if (!operationSuccess) {
            newUsers = Optional.empty();
        }

        return newUsers;

    }


}
````
# 6. Integration and Demo 

* A new option on the Main menu options was added.



# 7. Observations

Some classes need some verifications in some inputs of the users.





