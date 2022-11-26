package com.restapi.testcase;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.restapi.utilities.MyXLSReader;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC02_PostEmpTest extends BaseClass {

	MyXLSReader xlsreader;
	String sname = "Ravi" + getRandomStringAlpha(2);
	String ssid = getRandomStringNumeric(4);
	String sage = getRandomStringNumeric(2);

	@SuppressWarnings("unchecked")
	@Test(priority = 1, dataProvider = "TestDatas")
	public void postEmpDetailRequest() {

		log.info("************** Started TC02_PostEmpTest Started *****************");

		RestAssured.baseURI = URI;
		httprequest = RestAssured.given();

		JSONObject data = new JSONObject();
		data.put("id", ssid);
		data.put("username", sname);

		httprequest.header("Content-Type", "application/json");

		httprequest.body(data.toJSONString());

		response = httprequest.request(Method.POST, "v2/user");

		try {
			Thread.sleep(5000);
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

	@Test(priority = 2)
	public void checkResponseBody() {

		log.info("** Checking Response Body **");

		String responsebody = response.getBody().asString();
		log.info("Response Body ==>" + responsebody);
		Assert.assertEquals(responsebody.contains(ssid), true);
		Assert.assertTrue(responsebody.contains("200"));
	}

	@Test(priority = 3)
	public void checkStatusCode() {

		log.info("** Checking Status Code **");

		int responsecode = response.getStatusCode();
		log.info("Response Status Code ==>" + responsecode);
		Assert.assertEquals(responsecode, 200);
	}

	@Test(priority = 4)
	public void checkResponseTime() {

		log.info("** Checking Response Time **");

		long responsetime = response.getTime();
		log.info("Response Time ==>" + responsetime);

		if (responsetime > 5000) {
			log.warn("Response Time Is Greater Than 5000");
		}
		Assert.assertTrue(responsetime < 5000);
	}

	@Test(priority = 5)
	public void checkStatusLine() {

		log.info("** Checking Status Line **");

		String responsestatusline = response.getStatusLine();
		log.info("Response Status Line ==>" + responsestatusline);
		Assert.assertEquals(responsestatusline, "HTTP/1.1 200 OK");

	}

	@Test(priority = 6)
	public void checkContentType() {

		log.info("** Checking Content Type **");

		String responsecontenttype = response.header("Content-Type");
		log.info("Response Content Type ==>" + responsecontenttype);
		Assert.assertEquals(responsecontenttype, "application/json");
	}

	@Test(priority = 7)
	public void checkServer() {

		log.info("** Checking Server Type **");

		String responseserver = response.header("Server");
		log.info("Response Server Type ==>" + responseserver);
		Assert.assertEquals(responseserver, "Apache");

	}

	@Test(priority = 8, enabled = false)
	public void checkContentEncoding() {

		log.info("** Checking Content Encoding **");

		String responsecontencod = response.header("Content-Encoding");
		log.info("Response Content Encoding ==>" + responsecontencod);
		Assert.assertEquals(responsecontencod, "gzip");

	}

	// @Test(priority = 9)
	public void checkContentLength() {

		log.info("** Checking Content Length **");

		String responsecontlength = response.header("Content-Length");
		log.info("Response Content Length ==>" + responsecontlength);

		if (Integer.parseInt(responsecontlength) > 500) {
			log.warn("Content Length Is More Than 500");
		}

		Assert.assertTrue(Integer.parseInt(responsecontlength) < 500);

	}

}
