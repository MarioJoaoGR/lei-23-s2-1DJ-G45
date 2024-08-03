package pt.ipp.isep.dei.esoft.project.domain.Adapter;

import pt.ipp.isep.dei.esoft.project.application.controller.AnnouncementController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.Interface.ExternalModule;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The type External module adapter csv.
 */
public class ExternalModuleAdapterCSV implements ExternalModule {

    /**
     * The External module csv list.
     */
    List<ExternalModuleCSV>  externalModuleCSVList = new ArrayList<>();

    /**
     * The Controller.
     */
    AnnouncementController controller = new AnnouncementController();


    /**
     * The Column names.
     */
    String[] columnNames = {"sid", "owner_name", "owner_passportNum", "owner_TIN(SSN)", "owner_email", "owner_phone",
            "property_type","property_area(square feet)","property_location","property_distanceFromCenter (miles)",	"property_numberBedrooms","property_numberBathrooms","property_pnumParking","property_centralHeating","property_airconditioned","property_basement","property_loft","property_sunExposure","property_requested_sale_rent_price","property_sale_rent_price (USD)",
            "commission(%)","contract_duration(months)","property_dateAnnounceRequest","property_dateofSale","type_business", "store_ID","store_name","store_location","store_phonenumber","store_emailAddress"

    };


    /**
     * Reads a CSV file and populates a list with the data.
     *
     * @param filePath The path to the CSV file to be read.
     * @return `true` if the file is read successfully, `false` otherwise.
     */
    @Override
    public boolean readFile(String filePath) {

        try {
            Map<String, Integer> columnIndices = new HashMap<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                boolean isFirstLine = true;


                while ((line = reader.readLine()) != null) {
                    if (line.trim().isEmpty()) {
                        break;
                    }

                    if (isFirstLine) {
                        String[] headers = line.split(";");
                        for (int i = 0; i < headers.length; i++) {
                            columnIndices.put(headers[i], i);
                        }
                        isFirstLine = false;
                    } else {
                        String[] values = line.split(";");

                        if(!validateNumberColumns(values)){
                            return false;
                        }


                        int sid = getValueAsInt(columnIndices, values, columnNames[0]);
                        String owner_name = getValue(columnIndices, values, columnNames[1]);
                        long owner_passportNum = getValueAsLong(columnIndices, values, columnNames[2]);
                        long owner_TIN = getValueAsLong(columnIndices, values, columnNames[3]);
                        String owner_email = getValue(columnIndices, values, columnNames[4]);
                        long owner_phone = getValueAsLong(columnIndices, values, columnNames[5]);
                        String property_type= getValue(columnIndices, values, columnNames[6]);
                        double property_area=getValueAsDouble(columnIndices, values, columnNames[7]);
                        String property_location=getValue(columnIndices, values, columnNames[8]);
                        double property_distanceFromCenter= getValueAsDouble(columnIndices, values, columnNames[9]);
                        int property_numberBedrooms=getValueAsInt(columnIndices, values, columnNames[10]);
                        int property_numberBathrooms=getValueAsInt(columnIndices, values, columnNames[11]);
                        int property_pnumParking=getValueAsInt(columnIndices, values, columnNames[12]);
                        boolean property_centralHeating=getValueAsBoolean(columnIndices, values, columnNames[13]);
                        boolean property_airconditioned=getValueAsBoolean(columnIndices, values, columnNames[14]);
                        boolean property_basement= getValueAsBoolean(columnIndices, values, columnNames[15]);
                        boolean property_loft=getValueAsBoolean(columnIndices, values, columnNames[16]);
                        String property_sunExposure=getValueAsSunExposure(columnIndices, values, columnNames[17]);
                        double property_requested_sale_rent_price=getValueAsDouble(columnIndices, values, columnNames[18]);
                        double property_sale_rent_price=getValueAsDouble(columnIndices, values, columnNames[19]);
                        double commissionValue=getValueAsDouble(columnIndices, values, columnNames[20]);
                        int contract_duration=getValueAsInt(columnIndices, values, columnNames[21]);
                        Date property_dateAnnounceRequest=getValueAsDate(columnIndices, values, columnNames[22]);
                        Date property_dateofSale=getValueAsDate(columnIndices, values, columnNames[23]);
                        String type_business=getValue(columnIndices, values, columnNames[24]);
                        int store_ID=getValueAsInt(columnIndices, values, columnNames[25]);
                        String store_name=getValue(columnIndices, values, columnNames[26]);
                        String store_location=getValue(columnIndices, values, columnNames[27]);
                        long store_phonenumber=getValueAsLong(columnIndices, values, columnNames[28]);
                        String store_emailAddress=getValue(columnIndices, values, columnNames[29]);

                        Commission commission = new Commission(commissionValue, "percentage");

                        ExternalModuleCSV lineCsv = new ExternalModuleCSV(sid, owner_name, owner_passportNum, owner_TIN, owner_email, owner_phone
                                ,property_type,property_area,property_location,property_distanceFromCenter,property_numberBedrooms,property_numberBathrooms,
                                property_pnumParking,property_centralHeating, property_airconditioned, property_basement , property_loft ,property_sunExposure ,property_requested_sale_rent_price,
                                property_sale_rent_price,commission,contract_duration,property_dateAnnounceRequest,property_dateofSale,type_business,store_ID,store_name,store_location,store_phonenumber,store_emailAddress);


                        externalModuleCSVList.add(lineCsv);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        addRepositorys();


        return true;
    }







    /**
     * Adds the announcements from the external module to the system.
     * Also add the agencies from the external module to the system.
     */
    public void addRepositorys(){
        for (ExternalModuleCSV a : externalModuleCSVList){
            addAnnouncement(a.getAnnouncementSold());
            controller.addAgencyList(a.getAgency());
        }
    }



    /**
     * Retrieves the value from the specified column as an integer.
     *
     * @param columnIndices A map of column names to their corresponding indices.
     * @param values        An array of values representing a row in the CSV file.
     * @param columnName    The name of the column from which the value is retrieved.
     * @return The integer value of the specified column, or a default value if the value is missing or invalid.
     */
    private int getValueAsInt(Map<String, Integer> columnIndices, String[] values, String columnName) {
        if (columnIndices.containsKey(columnName)) {
            int columnIndex = columnIndices.get(columnName);

            if (values.length > columnIndex) {
                String value = values[columnIndex].replaceAll("-","");
                if (!value.isEmpty() && !value.equalsIgnoreCase("NA")) {
                    return Integer.parseInt(value);
                }
            }
        }
        return 0;
    }

    /**
     * Retrieves the value from the specified column as a long.
     *
     * @param columnIndices A map of column names to their corresponding indices.
     * @param values        An array of values representing a row in the CSV file.
     * @param columnName    The name of the column from which the value is retrieved.
     * @return The long value of the specified column, or a default value if the value is missing or invalid.
     */
    private long getValueAsLong(Map<String, Integer> columnIndices, String[] values, String columnName) {
        if (columnIndices.containsKey(columnName)) {
            int columnIndex = columnIndices.get(columnName);
            if (values.length > columnIndex) {
                String value = values[columnIndex].replaceAll("-","");
                if (!value.isEmpty() && !value.equalsIgnoreCase("NA")) {
                    return Long.parseLong(value);
                }
            }
        }
        return 0L;
    }


    /**
     * Retrieves the value from the specified column as a double.
     *
     * @param columnIndices A map of column names to their corresponding indices.
     * @param values        An array of values representing a row in the CSV file.
     * @param columnName    The name of the column from which the value is retrieved.
     * @return The double value of the specified column, or a default value if the value is missing or invalid.
     */
    private double getValueAsDouble(Map<String, Integer> columnIndices, String[] values, String columnName) {
        if (columnIndices.containsKey(columnName)) {
            int columnIndex = columnIndices.get(columnName);
            if (values.length > columnIndex) {
                String value = values[columnIndex].replaceAll("-","");
                if (!value.isEmpty() && !value.equalsIgnoreCase("NA")) {
                    return Double.parseDouble(value);
                }
            }
        }
        return 0;
    }

    /**
     * Retrieves the value from the specified column as a sun exposure description.
     *
     * @param columnIndices A map of column names to their corresponding indices.
     * @param values        An array of values representing a row in the CSV file.
     * @param columnName    The name of the column from which the value is retrieved.
     * @return The sun exposure description of the specified column, or null if the value is missing or invalid.
     */
    private String getValueAsSunExposure(Map<String, Integer> columnIndices, String[] values, String columnName) {
        if (columnIndices.containsKey(columnName)) {
            int columnIndex = columnIndices.get(columnName);
            if (values.length > columnIndex) {
                switch (values[columnIndex]){
                    case "N":
                        return "North";
                    case "S":
                        return "South";
                    case "W":
                        return "West";
                    case "E":
                        return "East";
                    case "NA":
                        return "None";
                    default:
                        return null;
                }
            }
        }
        return null;
    }


    /**
     * Retrieves the value from the specified column as a boolean.
     *
     * @param columnIndices A map of column names to their corresponding indices.
     * @param values        An array of values representing a row in the CSV file.
     * @param columnName    The name of the column from which the value is retrieved.
     * @return The boolean value of the specified column, or a default value if the value is missing or invalid.
     */
    private boolean getValueAsBoolean(Map<String, Integer> columnIndices, String[] values, String columnName) {
        if (columnIndices.containsKey(columnName)) {
            int columnIndex = columnIndices.get(columnName);
            if (values.length > columnIndex) {
                return values[columnIndex].equalsIgnoreCase("y");
            }
        }
        return false;
    }

    /**
     * Retrieves the value from the specified column as a Date object.
     *
     * @param columnIndices A map of column names to their corresponding indices.
     * @param values        An array of values representing a row in the CSV file.
     * @param columnName    The name of the column from which the value is retrieved.
     * @return The Date object representing the value of the specified column, or null if the value is missing or invalid.
     */
    private Date getValueAsDate(Map<String, Integer> columnIndices, String[] values, String columnName){
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");

        if (columnIndices.containsKey(columnName)) {
            int columnIndex = columnIndices.get(columnName);
            if (values.length > columnIndex) {
                String dateStr = values[columnIndex];
                try {
                    return dateFormat1.parse(dateStr);
                } catch (ParseException e) {
                    try {
                        return dateFormat2.parse(dateStr);
                    } catch (ParseException ex) {

                        ex.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    /**
     * Retrieves the value from the specified column as a string.
     *
     * @param columnIndices A map of column names to their corresponding indices.
     * @param values        An array of values representing a row in the CSV file.
     * @param columnName    The name of the column from which the value is retrieved.
     * @return The string value of the specified column, or an empty string if the value is missing.
     */
    private String getValue(Map<String, Integer> columnIndices, String[] values, String columnName) {
        if (columnIndices.containsKey(columnName)) {
            int columnIndex = columnIndices.get(columnName);
            if (values.length > columnIndex) {
                return values[columnIndex];
            }
        }
        return "";
    }



    /**
     * Validates the number of columns in a row against the expected number of columns.
     *
     * @param values An array of values representing a row in the CSV file.
     * @return `true` if the number of columns matches the expected number, `false` otherwise.
     */
    private boolean validateNumberColumns(String[] values){
        int lenght=0;

        for (String value : values) {
            if (!value.isEmpty()) {
                lenght++;
            }
        }
        return lenght == columnNames.length;
    }


    /**
     * Adds an announcement to the system.
     *
     * @param announcementSold The announcement to be added.
     */
    private void addAnnouncement(AnnouncementSold announcementSold){
        controller.addAnnouncementSold(announcementSold);
    }

}
