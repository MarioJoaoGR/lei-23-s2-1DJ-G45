# US 015 - List all booking requests for properties managed by me.

## 1. Requirements Engineering


### 1.1. User Story Description


As an agent, I intend to list all booking requests for properties managed by
me.

### 1.2. Customer Specifications and Clarifications 


**From the client clarifications:**


> **Question:** The US15 does the listing and in US16 we are already responsing to one booking request. That said, were is the selection part being done? 
>> **Answer:** In US15 the Agent gets a list of booking requests (made to him). Then, the agent, may want to respond to the user (as defined in US16). US15 and US16 are executed sequentially. Even so, the agent should be able to see a list of all booking requests made to him (US15) without answer any booking request. In US16 the agent selects the booking request.

> **Question:** One of our questions' answers made us believe there might have been some miscommunication, as it had some significant mistakes in phrasing and your answer wasn't clear enough. As such, here is that same question, in a simpler and clearer way:
US017's AC2 states that "Two sorting algorithms should be implemented (to be chosen manually by the network manager)."
US015's AC2 states that "The list of requests must be sorted by date in ascending order. The sorting algorithm to be used by the application must be defined through a configuration file. At least two sorting algorithms should be available."
As such, the team would like to know if, just like in US017, the sorting methods would be chosen manually in US015.
>> **Answer:** In US 15 the sorting algorithm to be used by the application must be defined through a configuration file. This is a diferent strategy from what I want in US17. Please study ESOFT.

> **Question:** In US015's AC1, it is stated that "The list of requests must be shown for a specific period (begin date, end date)."
This time period caused confusion, as the team is unsure whether the "date" is referring to the date chosen by the client in the message (See US009's requirements) or the date of creation of the visit request.
>> **Answer:** The goal is to use the date chosen by the client (the preferred date that you can see in US9) to list booking requests.

> **Question:** When the agent requests the booking requests list to contact the client, that list should ONLY contain the requests related to that agent?
>> **Answer:** Yes. Listing is a feature described in US15.
Important: In US15 the Agent gets a list of booking requests (made to him). Then, the agent, may want to respond to the user (as defined in US16). US15 and US16 are executed sequentially. Even so, the agent should be able to see a list of all booking requests made to him (US15) without answer any booking request.

> **Question:** US015's AC1 states that "The list of requests must be shown for a specific period (begin date, end date)". As such, our team would like to know if you want this time period to be selected or typed, and in which format it should be in.
>> **Answer:** The dates should be selected. The format should be DD-MM-YYYY.

> **Question:** Booking is a slightly vague word. Our team wants clarifications on what type of requests the word encompasses (visit requests being our strongest guess, but we are not sure).
>> **Answer:** It is a visit request.


### 1.3. Acceptance Criteria

* **AC1:** The list of requests must be shown for a specific period (begin date, end
  date).
* **AC2:** The list of requests must be sorted by date in ascending order. The sorting
  algorithm to be used by the application must be defined through a configuration
  file. At least two sorting algorithms should be available.


### 1.4. Found out Dependencies

* None dependency

### 1.5 Input and Output Data


**Input Data:**

* Selected data:
    * Date of beginning and end of the search




**Output Data:**

* List of booking requests


### 1.6. System Sequence Diagram (SSD)




![System Sequence Diagram - Alternative One](svg/us015-system-sequence-diagram.svg)


