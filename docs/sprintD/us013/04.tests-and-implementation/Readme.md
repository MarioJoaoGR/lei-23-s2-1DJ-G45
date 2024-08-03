# US 013 - List all employees in every store


# 4. Tests 
### Tests for RegisterUserController

    public class TestApplication {
    public static void main(String[] args) {
    // Creating sample data
    Agency agency1 = new Agency();
    agency1.setName("Agency 1");

        Agency agency2 = new Agency();
        agency2.setName("Agency 2");

        ListOfEmployees employees1 = new ListOfEmployees();
        employees1.addEmployee(new Employee("John Doe"));
        employees1.addEmployee(new Employee("Jane Smith"));

        ListOfEmployees employees2 = new ListOfEmployees();
        employees2.addEmployee(new Employee("Mike Johnson"));
        employees2.addEmployee(new Employee("Emily Brown"));

        agency1.setListOfEmployees(employees1);
        agency2.setListOfEmployees(employees2);

        // Creating the components
        ListEmployeesUI ui = new ListEmployeesUI();
        ListEmployeeController controller = new ListEmployeeController();
        AgencyMapper agencyMapper = new AgencyMapper();

        // Creating a list of agencies
        List<Agency> agencyList = new ArrayList<>();
        agencyList.add(agency1);
        agencyList.add(agency2);

        // Mapping agencies to DTOs
        List<AgencyDTO> agencyDTOList = agencyMapper.toDTO(agencyList);

        // Displaying the list of agencies and employees
        ui.showList(agencyDTOList);
    }
}

# 5. Construction (Implementation)


## Class RegisterUserController

```javapublic class NetworkManager {
    public void listEmployeesInEveryStore() {
        ListEmployeesUI ui = new ListEmployeesUI();
        ListEmployeeController controller = new ListEmployeeController();
        List<AgencyDTO> agencyDTOListSort = controller.getAgencyListSorted();
        ui.showList(agencyDTOListSort);
    }
}

public class ListEmployeesUI {
    public void showList(List<AgencyDTO> agencyDTOListSort) {
        // Display the list of agencies and employees
    }
}

public class ListEmployeeController {
    public List<AgencyDTO> getAgencyListSorted() {
        repositories = repositories.getInstance();
        AgencyRepository agencyRepository = repositories.getAgencyRepository();
        List<Agency> agencyListSort = agencyRepository.getAgencyListSorted();
        AgencyMapper agencyMapper = new AgencyMapper();
        List<AgencyDTO> agencyDTOListSort = agencyMapper.toDTO(agencyListSort);
        return agencyDTOListSort;
    }
}

public class repositories {
    private static repositories instance;

    private repositories() {
        // private constructor
    }

    public static repositories getInstance() {
        if (instance == null) {
            instance = new repositories();
        }
        return instance;
    }

    public AgencyRepository getAgencyRepository() {
        // Retrieve and return the AgencyRepository instance
    }
}

public class AgencyRepository {
    public List<Agency> getAgencyListSorted() {
        List<Agency> agenciesList = getAgenciesList();
        sort(agenciesList);
        return agenciesList;
    }

    private List<Agency> getAgenciesList() {
        // Retrieve the list of agencies from the repository
    }

    private void sort(List<Agency> agenciesList) {
        // Sort the list of agencies
    }

    public List<Employee> getListOfEmployees(Agency agency) {
        // Retrieve the list of employees for the given agency
    }
}

public class Agency {
    // Agency class implementation
}

public class ListOfEmployees {
    // ListOfEmployees class implementation
}

public class AgencyMapper {
    public List<AgencyDTO> toDTO(List<Agency> agencyList) {
        List<AgencyDTO> agencyDTOList = new ArrayList<>();
        for (Agency agency : agencyList) {
            AgencyDTO agencyDTO = createAgencyDTO(agency);
            agencyDTOList.add(agencyDTO);
            List<Employee> employeeList = agency.getListOfEmployees();
            for (Employee employee : employeeList) {
                EmployeeDTO employeeDTO = createEmployeeDTO(employee);
                agencyDTO.addEmployee(employeeDTO);
            }
        }
        return agencyDTOList;
    }

    private AgencyDTO createAgencyDTO(Agency agency) {
        // Convert an Agency object to AgencyDTO
    }

    private EmployeeDTO createEmployeeDTO(Employee employee) {
        // Convert an Employee object to EmployeeDTO
    }
}

public class AgencyDTO {
    private String agencyName;
    private List<EmployeeDTO> employees;

    public AgencyDTO(String agencyName) {
        this.agencyName = agencyName;
        this.employees = new ArrayList<>();
    }

    public void addEmployee(EmployeeDTO employeeDTO) {
        employees.add(employeeDTO);
    }
}

public class EmployeeDTO {
    // EmployeeDTO class implementation
}

````





