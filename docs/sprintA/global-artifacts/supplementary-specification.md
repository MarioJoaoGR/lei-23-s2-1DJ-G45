# Supplementary Specification (FURPS+)

## Functionality

- **Data validation**

  The system must provide necessary code to validate data related to
  the real estate business. That is, data related to a lease or sale,
  and to the property.


- **Data management**

  The system must organize all the data related to the business and
  manage it, i.e., organize it and relate it according to specific parameters.


- **Security**

  The system must provide a function to authenticate an agent or a costumer.
  The password must obey specific parameters (7 alphanumeric digits,
  including 3 capital letters and 2 numbers; is generated automatically;
  is sent to the user by email) to prevent any security issues.
  Also, the system can't allow regular users the same functions/privileges
  that an agent has, as well as a store manager and the network manager.


- **Network support and management**

  The system must provide means for an easy management between the stores.
  This also means that the stores are interconnected via a network which needs
  to be supported by the system.


- **Support English language**


- **Support a GUI**

****

## Usability

- **Human factors**

  The GUI must take in account the sight of different people, as so,
  it needs present a visible interface and font. Also, it must support
  a colorblind mode. It is also paramount that the GUI has ease of access
  and usability ease. The GUI should also have an option to access help
  for any kind of operation.


## Reliability

- **Recoverability**

  The system must allow for an instant recovery of data in case of a
  malfunction. It needs to have a constant back up present.

- **Low bug frequency**

- **Low crash frequency**
- **Low severity of error**
- **High response time**
- **High recovery**

## Performance

The system and GUI must be fast and responsive. The system needs to be fast
even when is almost overloaded, i.e., when many people are using it at
the same tyme

## Supportability

- **Adaptability**

  The system must support different types of real estate business. That is,
  the system needs to be adaptable to the needs that different real estate businesses have.


- **Configurability**

  As briefly mentioned in the functionality section the system needs to
  be configurable for different types of users. It must support different
  privileges and operations for a costumer user, an agent user, a store manager,
  a network manager, and the system manager(s).

## +

### Design Constraints

- **Written in Java language**
- **Graphical Interface using JavaFX**
- **Dollar coin implementation**

### Implementation Constraints

- **Written in Java language**
- **Graphical Interface using JavaFX**

### Interface Constraints

- **Visible interface that supports colorblind people**

### Physical Constraints


- **None**