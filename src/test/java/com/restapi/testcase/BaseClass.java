package com.restapi.testcase;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.restapi.utilities.ReadConfig;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseClass {

	public static ReadConfig config = new ReadConfig();

	public static RequestSpecification httprequest;
	public static Response response;
	public static Logger log;

	@BeforeClass
	public void setUp() {

		log = LogManager.getLogger(BaseClass.class.getName());

	}

	@AfterClass
	public void tearDown() {

		log.info("************* TestCase Completed ****************");
	}

	public String getRandomStringAlpha(int alpha) {

		return (RandomStringUtils.randomAlphabetic(alpha));
	}

	public String getRandomStringNumeric(int numeric) {

		return (RandomStringUtils.randomNumeric(numeric));
	}
}
