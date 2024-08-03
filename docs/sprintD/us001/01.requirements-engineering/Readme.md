# US 001 - Display listed properties

## 1. Requirements Engineering



### 1.1. User Story Description


As an unregistered user, I want to display listed properties.



### 1.2. Customer Specifications and Clarifications



**From the specifications document:**
> All registered information, except the agency commission, can be accessed by the client who intends to buy or rent the property; the client is, then, responsible for being able to consult the properties by type, number of rooms, and sort by criteria such as price or the parish where the property is located.



**From the client clarifications:**

> **Question:** In the project's documentation it's mentioned that "All those who wish to use the application must be authenticated", but in the US1 it's said that an unregistered user can see a list of properties. Can users who aren't authenticated do this?
>> **Answer:** Non-authenticated users can only list properties


> **Question:** Can the Client see the list of available houses in all the branches?
>
>> **Answer:** Yes


> **Question:** The properties can be in sale and lease at the same time?
>
>> **Answer:** No.


> **Question:** In the project description it is stated that "the client is, then, responsible for being able to consult the properties by type, number of rooms, and sort by criteria such as price or the parish where the property is located.". Is the client able to sort properties by only these 4 criteria or is he able to sort properties by any of the properties' characteristics?
>
>> **Answer:** The client should be able to select the type of business (renting or buying), the type of property and the number of rooms. Then, the client should be able to sort properties by price or by parish where the property is located.  
If the client does not select the type of business, the type of property and the number of rooms, the application should allow the client to sort all properties that are on sale or on renting.


> **Question:** The property size, location and type are typed or selected in order to filter the results?
>
>> **Answer:** The client should be able to select (from a list) the type of business, the type of property and the number of rooms.

> **Question:** In a previous question, you said that the address doesn't include parishes ("In the USA, the addresses will not include municipalities or parishes."). That being said, how should we sort the properties by location, specifically city, and state?
>>**Answer:** The client should be able to sort properties by price, city and state.

> **Question:** The one of the search criteria is "number of rooms". Is "Number of Bedrooms" or "Number of Bathrooms" or both?
>>**Answer:** Number of Bedrooms.


### 1.3. Acceptance Criteria

* **AC1:** Unregistered user can search for properties if he doesn't select any data when system ask for it. He can see all properties available in the system.
* **AC2:** If the system does not contain any properties, the system should show an empty list of properties.
* **AC3:** If the client does not select the type of business, the type of property and the number of rooms, the application should allow the client to sort all properties that are on sale or on renting.


### 1.4. Found out Dependencies

None dependencie

### 1.5. Input and Output Data

**Input Data**

* Selected data:
    * Type of business
    * Type of property
    * Number of rooms


**Output data**
* List of properties


### 1.6. System Sequence Diagram (SSD)


![System Sequence Diagram ](svg/us001-system-sequence-diagram.svg)


### 1.7. Other Relevant Remarks
* **Brief format :**
  * An unregistered user starts a new search for properties. The system request the data (type of business, type of the 
  property and number of rooms). The user selects the requested data and confirm. The application shows to user all 
  properties that respect the criteria inserted. Then client can sort the properties by price or by parish where the 
  property is located.
