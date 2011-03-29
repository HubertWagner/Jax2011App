package com.accenture.jax2011.server;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.simpledb.AmazonSimpleDB;
import com.amazonaws.services.simpledb.AmazonSimpleDBClient;
import com.amazonaws.services.simpledb.model.BatchPutAttributesRequest;
import com.amazonaws.services.simpledb.model.ReplaceableAttribute;
import com.amazonaws.services.simpledb.model.ReplaceableItem;

public class AwsConsoleApp {

    static AmazonSimpleDB sdb;

    private static void init() throws Exception {
    	String propertiesPath = "/com/accenture/jax2011/AwsCredentials.properties";
    	
        AWSCredentials credentials = new PropertiesCredentials(
                AwsConsoleApp.class.getResourceAsStream(propertiesPath));
       
        sdb = new AmazonSimpleDBClient(credentials);
        
    }


    public static void writeToDb(String user, String userAgent) throws Exception {

        init();

        try {List<ReplaceableItem> sampleData = new ArrayList<ReplaceableItem>();
        		sampleData.add(new ReplaceableItem("User").withAttributes(
        				new ReplaceableAttribute("Name", user, true),
        				new ReplaceableAttribute("User-Agent", userAgent, true)));        	
        	BatchPutAttributesRequest attReq = new BatchPutAttributesRequest("User", sampleData);
        	sdb.batchPutAttributes(attReq);
        }
        catch (Exception ase) {
                System.out.println("Caught Exception: " + ase.getMessage());
                System.out.println("Reponse Status Code: " + ((AmazonServiceException) ase).getStatusCode());
                System.out.println("Error Code: " + ((AmazonServiceException) ase).getErrorCode());
                System.out.println("Request ID: " + ((AmazonServiceException) ase).getRequestId());
        }

    }
}
    
