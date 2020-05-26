package com.bestbuy.utils;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by Chaitanya
 */
public class PropertyReader {

    //Declare the PropertyReader variable
    //volatile key word avoid multi thread issue
    private static volatile PropertyReader propInstance;


    //This method will return instance of propertyReader class
    //@return
    //synchronized key word handle multi thread issue
    public static synchronized PropertyReader getInstance() {
        if (propInstance == null) {
            propInstance = new PropertyReader();
        }
        return propInstance;
    }

    /**
     * For this type of comments one / and 2 star and press enter key from keyboard
     * This method will read property from property file
     *
     * @param propertyName
     * @return
     */

    public String getProperty(String propertyName) {
        Properties prop = new Properties();
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(System.getProperty("user.dir") + "/src/test/java/com/bestbuy/resources/application.properties");
            prop.load(inputStream);
            if (prop.getProperty(propertyName) != null) {
                return prop.getProperty(propertyName);
            }
        } catch (Exception e) {
            System.out.println("Property not found");
        }
        return null;
    }


}
