# US 013 - List all employees in every store

## 1. Requirements Engineering


### 1.1. User Story Description

As a network manager, I want to list all employees working in every store
of the network

### 1.2. Customer Specifications and Clarifications


**From the specifications document:**


**From the client clarifications:**


> **Question**:  Do you want a list where the header is ID, the name of the store, and the total number of listings that the store has?
>
> >**Answear**: Yes.


### 1.3. Acceptance Criteria


* **AC1:** The list of employees should be alphabetically sorted and grouped by store.
* **AC2:** . Stores should be sorted according to their property listings, from the one
  with more listings to the one with less listings.
* **AC3:** Each store should state how many property listings it has.


### 1.4. Found out Dependencies


* dependency with us03,us04,us05


### 1.5 Input and Output Data


**Output Data:**

* List of all employees sorted in each agency


### 1.6. System Sequence Diagram (SSD)

**Other alternatives might exist.**

#### Alternative One

![SSD](svg/us013-system-sequence-diagram.svg)

### 1.7 Other Relevant Remarks

none
