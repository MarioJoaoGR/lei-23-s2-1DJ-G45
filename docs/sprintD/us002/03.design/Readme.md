# US 002 - Publish any sale announcement on the system

## 3. Design - User Story Realization 

### 3.1. Rationale


| Interaction ID | Question: Which class is responsible for...   | Answer                  | Justification (with patterns)                                                                                 |
|:---------------|:----------------------------------------------|:------------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1  		     | 	... interacting with the actor?              | PublishSaleUI           | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			  		        | 	... coordinating the US?                     | PublishSaleController   | Controller                                                                                                    |
| 			  		        | 	... instantiating a new Announcement?        | PublishSaleUI           | Creator                                                                                                       |
| 			  		        | ... knowing the user using the system?        | UserSession             | IE: cf. A&A component documentation.                                                                          |
| 			  		        | 							                                       | EmoployeeRepository     | IE: knows/has its own Employees                                                                               |
| 			  		        | 							                                       | Employee                | IE: knows its own data (e.g. email)                                                                           |
| Step 2  		     | 							                                       |                         |                                                                                                               |
| Step 3  		     | 	...saving the inputted data?                 | AnnouncementRepository  | IE: object created in step 1 has its own data.                                                                |
| Step 4  		     | 	...publish the announcement						            | Announcement Repository | IE: owns its Announcements.                                                                                   |              
| Step 5  		     | 	... validating all data (local validation)?  | PublishSaleUI           | IE: owns its data.                                                                                            | 
| 			  		        | 	... validating all data (global validation)? | PublishSaleUI           | IE                                                                                                            | 
| 			  		        | 	... saving the created Announcement?         | AnnouncementRepository  | IE                                                                                                            | 
| Step 6  		     | 	... informing operation success?             | PublishSaleUI           | IE: is responsible for user interactions.                                                                     | 

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

 * AnnouncementRepository
 * Announcement

Other software classes (i.e. Pure Fabrication) identified: 

 * PublishSaleUI  
 * PublishSaleController


## 3.2. Sequence Diagram (SD)

### Alternative 1 - Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us002-sequence-diagram-full.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us002-class-diagram.svg)