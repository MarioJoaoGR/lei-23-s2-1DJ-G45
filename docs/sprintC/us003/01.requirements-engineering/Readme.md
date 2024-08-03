# US 003 - Register a new employee

## 1. Requirements Engineering


### 1.1. User Story Description


As a system administrator, I want to register a new employee.



### 1.2. Customer Specifications and Clarifications 



**From the specifications document:**

>The company's systems administrator will be responsible for registering all employees (specifying the name, the citizen's card number, the tax number, the address, the email address, the contact telephone number and the agency to which it is assigned).

**From the client clarifications:**

> **Question:**  When a System Administrator (admin) makes a request to register a new employee or a new network branch (or any other alteration), does the System ask for the admin credentials (login, password)?
>
>>**Answer:** The System Administrator should be logged in the application.

> **Question:**  When registering a new employee, all the required data (name, citizen's card number, etc...) have to be filled or exists not mandatory data?
>>**Answer:** Required/Mandatory data that should be filled when registering an employee: name, the citizen's card number, the tax number, the email address, the contact telephone number and the agency to which it is assigned.

> **Question:**  However, it was replied to a question when a new Employee is created in the system, that a 8 digit Password should be automatically generated. How many digits should we go forward for password length validation in your software? And please confirm required special characters, etc.
>>**Answer:** Sorry, I completely forgot that all our authentication systems require passwords with seven alphanumeric characters in length , including three capital letters and two digits. The password should be generated automatically. The password is sent to the employee by e-mail.



### 1.3. Acceptance Criteria

* **AC1:** The administrator needs to be authenticated by the System.
* **AC2:** Citizen's card number, phone number and text number need to has minimum characters.
* **AC3:** All required fiels must be filled in.
* **AC4:** A agency must be selected. 

### 1.4. Found out Dependencies

Dependency with US5: "Register a store" There is a dependency with the US5 since the employee need to be assign to an agency.
### 1.5. Input and Output Data

**Input Data**

* Typed data:
  * Name
  * Citizen's card number
  * Tax number
  * Address
  * Email address
  * Phone number

* Selected data:
  * Agency

**Output data**
* Operation status
* Password sent to Employee


### 1.6. System Sequence Diagram (SSD)


### Alternative One

![System Sequence Diagram ](svg/us003-system-sequence-diagram-alternative-one.svg)

### Alternative Two

![System Sequence Diagram ](svg/us003-system-sequence-diagram-alternative-two.svg)



### 1.7 Other Relevant Remarks

* **Brief format:**
  * The administrator starts a register of a new employee. The system request the required data  (specifying the name, the
    citizen's card number, the tax number, the address, the email address, the contact telephone number and the agency to
    which it is assigned). The administrator types the requested data. The system validates and presents the data to the
    administrator, asking her/him to confirm. The administrator confirms.Then the system shows a list of agencys and ask where the employee will work. The administrator choose one agency and confirm. The system records the data, sends an email to Emplyoee with the password and informs the
    administrator of the operation’s success.
