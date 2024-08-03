# US 003 - Register a new employee

## 3. Design - User Story Realization 

### 3.1. Rationale

**SSD - Alternative 1 is adopted.**

| Interaction ID | Question: Which class is responsible for...         | Answer                     | Justification (with patterns)                                                                                 |
|:---------------|:----------------------------------------------------|:---------------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1  		     | 	...interacting with the System administrator?					 | RegisterEmployeeUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
|                | ...coordinating the US?	                            | RegisterEmployeeController | Controller                                                                                                    |
|                | ...instantiating a new employee?		                  | Agency                     |                                                                                                               |
|                | ... knowing the user using the system?              | UserSession                | Information Expert: cf. A&A component documentation.                                                          |
| Step 2  		     | 						                                              |                            |                                                                                                               |
| Step 3  		     | 	...saving inputted data                            | Employee                   | Information Expert: knows it own data.                                                                                        | 
| Step 4  		     | 	...validating all data(local validation)?          | Employee                   | Information Expert: owns its data.                                                                                            |
|                | ...validating all data (global validation)?         | Agency                     | Information Expert: knows all its requests.                                                                                   |
|                | ...saving the created request                       | Agency                     | Information Expert: owns all its stores.                                                                                      |
| Step 5  		     | ...informing operation success							               | RegisterEmployeeUI         | Information Expert: is responsible for user interactions.                                                                     |

### Systematization ##
According to the taken rationale, the conceptual classes promoted to software classes are:

* Agency
* Employee
* AgencyRepository


Other software classes (i.e. Pure Fabrication) identified:
* RegisterEmployeeUI
* RegisterEmployeeController

## 3.2. Sequence Diagram (SD)

###  Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us003-sequence-diagram-full.svg)


## 3.3. Class Diagram (CD)

![Class Diagram](svg/us003-class-diagram.svg)