# US 005 - Register a store 

## 1. Requirements Engineering


### 1.1. User Story Description


As a system administrator, I want to register a store.



### 1.2. Customer Specifications and Clarifications 


**From the specifications document:**

>	Each task is characterized by having a unique reference per organization, a designation, an informal and a technical description, an estimated duration and cost as well as the its classifying task category. 


>	As long as it is not published, access to the task is exclusive to the employees of the respective organization. 



**From the client clarifications:**

> **Question:** When a System Administrator (admin) makes a request to register a new employee or a new network branch (or any other alteration), does the System ask for the admin credentials (login, password)?
>  
>> **Answer:** The System Administrator should be logged in the application.




> **Question:** Lastly, can there be more than one admin?
>  
>> **Answer:** No.



> **Question:** Could you please share how will the designation of new stores be made, is there a pattern perhaps?
> 
>> **Answer:** There is no pattern. The System Administrator can introduce any designation/name. The designation/name should have at most forty characters.




> **Question:** Will the System Administrator be able to choose a location from a list of available locations (defined elsewhere in the application) or will he be able to submit any location he wants?
>
>> **Answer:** The System Administrator can submit any location.



> **Question:** Are the local managers restricted to their initial location or can they be re-registered/reassigned to other locations?
>
>> **Answer:** For now this is not a problem.

>**Question**: Does a store designation have to be detailed? If so, will the system administrator have to register the name, email, phone number or anything else? Does the location of a store have to be detailed as well? If yes, will the system administrator have to record the address, postcode or something else? To register the store manager, does the system administrator only register the manager's name? If not, will the system administrator have to register the manager as if he were an employee (except the agent he is assigned to)?
> 
>>**Answer**: When registering a store, the System Administrator should introduce the following information: an ID, a designation/name, a location, a local manager, a phone number and an e-mail address. The ID is an integer number. An example of the store location is: 71 ST. NICHOLAS DRIVE, NORTH POLE, FAIRBANKS NORTH STAR, AK, 99705. An example phone number is (907) 488-6419.


### 1.3. Acceptance Criteria

* **AC1:** The administrator needs to be authenticated by the System.
* **AC2:** All required fiels must be filled in.
* **AC3:** Phone number need to has minimum and maximum characters.
* **AC4:** The designation/name should have at most forty characters.

### 1.4. Found out Dependencies

None dependency

### 1.5 Input and Output Data


**Input Data:**

* Typed data:
	* an ID, 
	* a designation/name, 
	* a location
	* a phone number
	* an e-mail address
  
* Selected data:
  * local manager
	


**Output Data:**

* Information of registered store
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)



![System Sequence Diagram - Alternative One](svg/us005-system-sequence-diagram.svg)


### 1.7 Other Relevant Remarks
* Brief format:
  * System administrator asks to register a new store and the system request all the data needed. After he types all the 
  data, the system shows a list of local managers and the system administrator needs to select one to assign to store. 
  After confirmation, the system shows all the data and request a confirmation from the administrator. Then, after 
  confirmation, the systems return a success message.