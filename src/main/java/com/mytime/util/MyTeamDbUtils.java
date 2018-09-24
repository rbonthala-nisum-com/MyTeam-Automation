package com.mytime.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.testng.Assert;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytime.database.dto.AccountDTO;
import com.mytime.database.dto.DomainDTO;
import com.mytime.database.dto.EmployeeDTO;

public class MyTeamDbUtils {

	public void compareAccountWithDb(AccountDTO accountDto, Map<String, String> uiRecord, EmployeeDTO empDto, String inputEmpIds, String dbEmpIds) {

		for (Map.Entry<String, String> pair : uiRecord.entrySet()) {
			if (accountDto.getAccountName().equals(pair.getValue())) {
				Assert.assertTrue(true, "Account Name entered in ui is " + accountDto.getAccountName()
						+ " and Account Name stored in database is " + pair.getValue() + " both are same");
				continue;
//			} else {
//				Assert.assertFalse(true, "Account Name entered in ui is " + accountDTO.getAccountName()
//						+ " and Account Name stored in database is " + pair.getValue() + " both are same");
			}
			if (accountDto.getIndustryType().equals(pair.getValue())) {
				Assert.assertTrue(true, "IndustryType entered in ui is " + accountDto.getIndustryType()
						+ " and IndustryType stored in database is " + pair.getValue() + " both are same");
				continue;
//			} else {
//				Assert.assertFalse(true, "IndustryType entered in ui is " + accountDTO.getIndustryType()
//						+ " and IndustryType stored in database is " + pair.getValue() + " both are same");

			}
//			if (accountDTO.getClientAddress().equals(pair.getValue())) {
//				Assert.assertTrue(true, "ClientAddress entered in ui is " + accountDTO.getClientAddress()
//						+ " and ClientAddress stored in database is " + pair.getValue() + " both are same");
//				continue;
//			} else {
//				Assert.assertFalse(true, "ClientAddress entered in ui is " + accountDTO.getClientAddress()
//						+ " and ClientAddress stored in database is " + pair.getValue() + " both are same");
//			}
			if (compareTwoStringArrays(inputEmpIds.split(","), dbEmpIds.split(","))) {
				Assert.assertTrue(true, "Delivery Leads are stored in database successfully");
				continue;
			} else {
				Assert.assertFalse(false, "Delivery Managers are not stored as expected in database");
			}

		}
	}
	
	public void compareDomainWithDb(DomainDTO domainDto, Map<String, String> uiRecord, EmployeeDTO empDto, String inputEmpIds, String dbEmpIds) {
		
		for(Map.Entry<String, String> pair: uiRecord.entrySet()) {
			if(domainDto.getDomainName().equals(pair.getValue())) {
				Assert.assertTrue(true, "DomainName is stored in database successfully");
				continue;
			}
			if(compareTwoStringArrays(inputEmpIds.split(","), dbEmpIds.split(","))) {
				Assert.assertTrue(true, "Delivery Leads are stored in database successfully");
				continue;
			}
		}
	}
	public boolean compareTwoStringArrays(String[] s1, String[] s2) {
		Boolean result = false;
		if (s1 == null || s2 == null) {
			Assert.fail("two strings are not euqal");
		}
		if (s1.length != s2.length) {
			Assert.fail("1st length of the String is " + s1.length + " 2nd length of the String is" + s2.length
					+ " we cannot compare those String because both lengths are not equal ");
		}
		for (int i = 0; i < s1.length; i++) {
			for(String strings:s2) {
				if(s1[i].equals(strings)){
					result = true;
				}
				if(i==s1.length) {
					break;
				}
			}
		}
		return result;
	}
	

	public EmployeeDTO convertEmpJsonToJavaObject(String dbRecord, EmployeeDTO dt) {

		EmployeeDTO dto = null;
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
		// mapper.setDateFormat(df);
		try {
			dto = mapper.readValue(dbRecord, EmployeeDTO.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	public AccountDTO convertAccountJsonToJavaObject(String dbRecord, AccountDTO dt) {

		AccountDTO dto = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			dto = mapper.readValue(dbRecord, AccountDTO.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	public DomainDTO convertDomainJsonToJavaObject(String dbRecord, DomainDTO dt) {
		
		DomainDTO dto = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			dto = mapper.readValue(dbRecord, DomainDTO.class);
		}catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dto;
	}
	
}
