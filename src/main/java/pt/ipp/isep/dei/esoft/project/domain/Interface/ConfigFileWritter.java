package pt.ipp.isep.dei.esoft.project.domain.Interface;

import java.io.*;
import java.util.Formatter;
import java.util.Properties;

import static java.lang.System.in;

/**
 * The interface Config file writter.
 */
public interface ConfigFileWritter {


    /**
     * Read config file.
     *
     * @param fileType         the file type
     * @param phoneNumber      the phone number
     * @param agentName        the agent name
     * @param idProperty       the id property
     * @param locationProperty the location property
     * @param response         the response
     */
    void configFileWritter(String fileType, long phoneNumber, String agentName, int idProperty, String locationProperty, String response);
}
