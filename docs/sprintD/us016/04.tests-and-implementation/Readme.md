# US 016 - Respond to the user that scheduled the visit


# 4. Tests 
### Tests for RegisterUserController

**Test 1:** Check if sending email is correct and include the name and phone number of the
responsible Agent and the property identification and location.


    @Test
    void ensureSendEmailWorks(){
        String message = name + ", your visit request for announcement about " + info + "to day " + visitRequest.getDate() +" at "+ visitRequest.getTimeSlot() + " was accepted \n\n"+announcementAvailable.getAgent().getName() +"\n"+announcementAvailable.getAgent().getPhoneNumber();
        String toEmail = testEmail@gmail.com;
            SendEmail.sendEmail(message,toEmail);
    }





**Test 2:** Check if it's working for differente platforms

    @Test
    void ensureSendEmailWorks(){
        String message = name + ", your visit request for announcement about " + info + "to day " + visitRequest.getDate() +" at "+ visitRequest.getTimeSlot() + " was accepted \n\n"+announcementAvailable.getAgent().getName() +"\n"+announcementAvailable.getAgent().getPhoneNumber();
        String toEmail = testEmail@gmail.com;
            SendEmail.sendEmail(message,toEmail);
        toEmail = testMail@outlook.com ;   
            SendEmail.sendEmail(message,toEmail);
        toEmail = testMail@hotmail.com;
            SendEmail.sendEmail(message,toEmail);

}


**Test 3:** Check that the booking request  list is updated


    @Test
    void ensureThatTheBookingRequestListIsUpdated(){
        List<VisitRequest> vrTest = new ArrayList <>
        vr.add(vr1);
        vr.add(vr2);
        vr.add(vr3);
        vr.get(1).setStatus("Approved");
        announcement.updateList(vr);
        assertTrue(!vrTest.contains(vr1));
    }

# 5. Construction (Implementation)

## Class AnnouncementController

```java
    public boolean sendEmail(AnnouncementAvailable announcementAvailable, VisitRequest visitRequest,boolean flag) {
        String name = visitRequest.getUserName();
        //String phoneNumber = String.valueOf(announcementAvailable.getAgent().getPhoneNumber());
        String toMail = visitRequest.getEmail();
        String info = "default";
        if (announcementAvailable.getProperty() instanceof Land) {
            info = "a "+announcementAvailable.getProperty().getPropertyType() + " in " + announcementAvailable.getProperty().getLocation();
        }
        if (announcementAvailable.getProperty() instanceof House) {
            info = "a "+((House) announcementAvailable.getProperty()).getNumberRooms() + "-" + announcementAvailable.getProperty().getPropertyType() + " in " + announcementAvailable.getProperty().getLocation();
        }
        if (announcementAvailable.getProperty() instanceof Apartment) {
            info = "an "+((Apartment) announcementAvailable.getProperty()).getNumberRooms() + "-" + announcementAvailable.getProperty().getPropertyType() + " in " + announcementAvailable.getProperty().getLocation();
        }

        String message ="";
        if (flag){
            message= name + ", your visit request for announcement about " + info + "to day " + visitRequest.getDate() +" at "+ visitRequest.getTimeSlot() + " was accepted \n\n"+announcementAvailable.getAgent().getName() +"\n"+announcementAvailable.getAgent().getPhoneNumber();
        }else {
            message= name + ", your visit request for announcement about " + info + "to day " + visitRequest.getDate() +" at "+ visitRequest.getTimeSlot() + " was rejected \n\n"+announcementAvailable.getAgent().getName() +"\n"+announcementAvailable.getAgent().getPhoneNumber();

        }
       return SendEmail.emailSend(toMail, message);
    }


    /**
     * Accept visit request.
     *
     * @param visitRequest the visit request
     */
    public void acceptVisitRequest(VisitRequest visitRequest){
        visitRequest.setStatus(VisitRequest.getStatusAccept());
        announcementRepository.saveVisitRequestState(visitRequest);
        updateVisitRequestList();
    }

    /**
     * Refuse visit request.
     *
     * @param visitRequest the visit request
     */
    public void refuseVisitRequest(VisitRequest visitRequest){
        visitRequest.setStatus(VisitRequest.getStatusRefuse());
        updateVisitRequestList();
    }

    /**
     * Update visit request list.
     */
    public void updateVisitRequestList(){
        visitRequestList.removeIf(visit -> !visit.getStatus().equalsIgnoreCase(VisitRequest.getStatusWaiting()));
    }
```

## Class VisitRequest
```java  
public String getUserName() {
        return userName;
    }
     public String getEmail() {
        return email;
    }
     public void setStatus(String status) {
        this.status = status;
    }
    public Agent getAgent() {
        return agent;
    }

```


## Class Announcement
```java
    public Property getProperty() {
        return property;
    }
```

## Class SendEmail
```java
public static boolean emailSend(String toEmail, String messageVisitRequest) {


        String to = toEmail;
        String from = "g45noreplytestmail@gmail.com";
        Properties properties = new Properties();
        InputStream in = null;
        try {
            in = new FileInputStream("src/main/resources/config.properties");
            properties.load(in);
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("g45noreplytestmail@gmail.com", "bvfuaavmcrikhuoz");
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Email for visitRequest");
            message.setText(messageVisitRequest);
            Transport.send(message);
            return true;
            //System.out.println("success");
        } catch (MessagingException messageException) {
            messageException.printStackTrace();
            return false;
        }
```



