# US 020 - read responses for appointment requests and accept or reject them

## 1. Requirements Engineering


### 1.1. User Story Description

As a client, I want to read the response for the appointment request, to
accept or reject it

### 1.2. Customer Specifications and Clarifications 


**From the specifications document:**


**From the client clarifications:**

* Q : The reason for declining the appointment should be selected from predefined options or entered as free text?
* A1 The message should be entered as free text.


### 1.3. Acceptance Criteria


* **AC1:** The agent must be notified when the message is displayed to the client.
* **AC2:** . The appointment request must provide information about the property and
  the date of the appointment.
* **AC3:** When the appointment is rejected, the client must specify the reason.
* **AC4:** The appointment request must provide the agent name and phone number

### 1.4. Found out Dependencies


* dependency with us 02, 03, 08, 09 16


### 1.5 Input and Output Data

**Input Data:**

* Typed data:
  * reason why the appointement was rejected

* Selected data:

  * accept or reject appointment


**Output Data:**
  
  * list of appointments
  * operation success message


### 1.6. System Sequence Diagram (SSD)

**Other alternatives might exist.**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us20ssd.svg)


### 1.7 Other Relevant Remarks

none


