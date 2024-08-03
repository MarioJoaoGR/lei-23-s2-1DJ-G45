# US 012 -  import information from a legacy system


# 4. Tests 

**Test 1:** Check if the file is in csv format

    @Test
    void validateCSV() {
        ImportController controller = new ImportController();
        File file = new File("email.txt");
        File file1 = new File("email.csv");
        assertFalse( controller.validateCSV(file));
        assertTrue( controller.validateCSV(file1));
    
    }

**Test 2:** Check if the file isn´t empty


    @Test
    void validateEmpty() {
        ImportController controller = new ImportController();
        File file = new File("email.txt");
        assertTrue( controller.validateEmpty(file));

    }

# 5. Construction (Implementation)


## Class ImportController

```java
public class ImportController {
    ExternalModuleAdapterCSV adapterCSV = new ExternalModuleCSV();

    public boolean addLegacyFile(File file,String filePath){
        if(validateCSV(file) && validateEmpty(file)){
            return adapterCSV.readFile(String.valueOf(filePath));

        }
        if (!validateCSV(file)){
            System.out.println("file is not in csv format");
        }
        if (!validateEmpty(file)){
            System.out.println("file is empty");
        }
        return false;
    }

    public boolean validateCSV(File file) {
        return file.getName().toLowerCase().endsWith(".csv");

    }

    public boolean validateEmpty(File file){
        return file != null;
    }
}
```


## Interface ExternalModule

```java
public interface ExternalModule {
    boolean readFile(String filepath);
}
```

### class ExternalModuleAdapterCSV
````java
public class ExternalModuleAdapterCSV implements ExternalModule {
    List<ExternalModuleCSV> externalModuleCSVList = new ArrayList<>();

    String[] columnNames = {"sid", "owner_name", "owner_passportNum", "owner_TIN(SSN)", "owner_email", "owner_phone",
            "property_type", "property_area(square feet)", "property_location", "property_distanceFromCenter (miles)", "property_numberBedrooms", "property_numberBathrooms", "property_pnumParking", "property_centralHeating", "property_airconditioned", "property_basement", "property_loft", "property_sunExposure", "property_requested_sale_rent_price", "property_sale_rent_price (USD)",
            "commission(%)", "contract_duration(months)", "property_dateAnnounceRequest", "property_dateofSale", "type_business", "store_ID", "store_name", "store_location", "store_phonenumber", "store_emailAddress"

    };


    @Override
    public boolean readFile(String filePath) {
        boolean flag;

        try {
            Map<String, Integer> columnIndices = new HashMap<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                boolean isFirstLine = true;

                while ((line = reader.readLine()) != null) {
                    if (isFirstLine) {
                        String[] headers = line.split(";");
                        for (int i = 0; i < headers.length; i++) {
                            columnIndices.put(headers[i], i);
                        }
                        isFirstLine = false;
                    } else {
                        String[] values = line.split(";");

                        if (!validateNumberColumns(values)) {
                            return false;
                        }


                        int sid = getValueAsInt(columnIndices, values, columnNames[0]);
                        String owner_name = getValue(columnIndices, values, columnNames[1]);
                        long owner_passportNum = getValueAsLong(columnIndices, values, columnNames[2]);
                        long owner_TIN = getValueAsLong(columnIndices, values, columnNames[3]);
                        String owner_email = getValue(columnIndices, values, columnNames[4]);
                        long owner_phone = getValueAsLong(columnIndices, values, columnNames[5]);
                        String property_type = getValue(columnIndices, values, columnNames[6]);
                        double property_area = getValueAsDouble(columnIndices, values, columnNames[7]);
                        String property_location = getValue(columnIndices, values, columnNames[8]);
                        double property_distanceFromCenter = getValueAsDouble(columnIndices, values, columnNames[9]);
                        int property_numberBedrooms = getValueAsInt(columnIndices, values, columnNames[10]);
                        int property_numberBathrooms = getValueAsInt(columnIndices, values, columnNames[11]);
                        int property_pnumParking = getValueAsInt(columnIndices, values, columnNames[12]);
                        boolean property_centralHeating = getValueAsBoolean(columnIndices, values, columnNames[13]);
                        boolean property_airconditioned = getValueAsBoolean(columnIndices, values, columnNames[14]);
                        boolean property_basement = getValueAsBoolean(columnIndices, values, columnNames[15]);
                        boolean property_loft = getValueAsBoolean(columnIndices, values, columnNames[16]);
                        String property_sunExposure = getValueAsSunExposure(columnIndices, values, columnNames[17]);
                        double property_requested_sale_rent_price = getValueAsDouble(columnIndices, values, columnNames[18]);
                        double property_sale_rent_price = getValueAsDouble(columnIndices, values, columnNames[19]);
                        double commissionValue = getValueAsDouble(columnIndices, values, columnNames[20]);
                        ;
                        int contract_duration = getValueAsInt(columnIndices, values, columnNames[21]);
                        ;
                        Date property_dateAnnounceRequest = getValueAsDate(columnIndices, values, columnNames[22]);
                        Date property_dateofSale = getValueAsDate(columnIndices, values, columnNames[23]);
                        String type_business = getValue(columnIndices, values, columnNames[24]);
                        int store_ID = getValueAsInt(columnIndices, values, columnNames[25]);
                        String store_name = getValue(columnIndices, values, columnNames[26]);
                        String store_location = getValue(columnIndices, values, columnNames[27]);
                        long store_phonenumber = getValueAsLong(columnIndices, values, columnNames[28]);
                        String store_emailAddress = getValue(columnIndices, values, columnNames[29]);

                        Commission commission = new Commission(commissionValue, "percentage");

                        ExternalModuleCSV lineCsv = new ExternalModuleCSV(sid, owner_name, owner_passportNum, owner_TIN, owner_email, owner_phone
                                , property_type, property_area, property_location, property_distanceFromCenter, property_numberBedrooms, property_numberBathrooms,
                                property_pnumParking, property_centralHeating, property_airconditioned, property_basement, property_loft, property_sunExposure, property_requested_sale_rent_price,
                                property_sale_rent_price, commission, contract_duration, property_dateAnnounceRequest, property_dateofSale, type_business, store_ID, store_name, store_location, store_phonenumber, store_emailAddress);

                        externalModuleCSVList.add(lineCsv);


                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (ExternalModuleCSV a : externalModuleCSVList) {
            addAnnouncement(a.getAnnouncementSold());
        }
        return true;
    }


    private boolean validateNumberColumns(String[] values) {
        int lenght = 0;

        for (String value : values) {
            if (!value.isEmpty()) {
                lenght++;
            }
        }
        return lenght == columnNames.length;
    }

    private void addAnnouncement(AnnouncementSold announcementSold) {
        AnnouncementController controller = new AnnouncementController();
        controller.addAnnouncementSold(announcementSold);

    }
}
````


    

## Interface ExternalModule

```java
public class ExternalModuleCSV {
    public ExternalModuleCSV(int sid, String owner_name, long owner_passportNum, long owner_TIN, String owner_email, long owner_phone, String property_type, double property_area, String property_location, double property_distanceFromCenter, int property_numberBedrooms, int property_numberBathrooms, int property_pnumParking, boolean property_centralHeating, boolean property_airconditioned, boolean property_basement, boolean property_loft, String property_sunExposure, double property_requested_sale_rent_price, double property_sale_rent_price, Commission commission, int contract_duration, Date property_dateAnnounceRequest, Date property_dateofSale, String type_business, int store_ID, String store_name, String store_location, long store_phonenumber, String store_emailAddress) {
        this.sid = sid;
        this.owner_name = owner_name;
        this.owner_passportNum = owner_passportNum;
        this.owner_TIN = owner_TIN;
        this.owner_email = owner_email;
        this.owner_phone = owner_phone;
        this.property_type = property_type;
        this.property_area = property_area;
        this.property_location = property_location;
        this.property_distanceFromCenter = property_distanceFromCenter;
        this.property_numberBedrooms = property_numberBedrooms;
        this.property_numberBathrooms = property_numberBathrooms;
        this.property_pnumParking = property_pnumParking;
        this.property_centralHeating = property_centralHeating;
        this.property_airconditioned = property_airconditioned;
        this.property_basement = property_basement;
        this.property_loft = property_loft;
        this.property_sunExposure = property_sunExposure;
        this.property_requested_sale_rent_price = property_requested_sale_rent_price;
        this.property_sale_rent_price = property_sale_rent_price;
        this.commission = commission;
        this.contract_duration = contract_duration;
        this.property_dateAnnounceRequest = property_dateAnnounceRequest;
        this.property_dateofSale = property_dateofSale;
        this.type_business = type_business;
        this.store_ID = store_ID;
        this.store_name = store_name;
        this.store_location = store_location;
        this.store_phonenumber = store_phonenumber;
        this.store_emailAddress = store_emailAddress;
    }


    public AnnouncementSold getAnnouncementSold() {
        return new AnnouncementSold(commission, property_dateAnnounceRequest, type_business, getProperty(), contract_duration, property_dateofSale, property_sale_rent_price, getAgency());
    }
}
```


# 6. Integration and Demo 

* A new option on the Admin menu options was added.



