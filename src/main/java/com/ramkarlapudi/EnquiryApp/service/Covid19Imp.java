package com.ramkarlapudi.EnquiryApp.service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Service
public class Covid19Imp implements Covid19 {

	public String settup(String Countryname) throws UnirestException, UnsupportedEncodingException {
		System.out.println("Countryname " + Countryname);
		String host = "https://covid-19-data.p.rapidapi.com/totals?format=undefined";
		String countryHost = "https://covid-19-data.p.rapidapi.com/country?format=undefined&name=";

		String charset = "UTF-8";
		// Headers for a request
		String x_rapidapi_host = "covid-19-data.p.rapidapi.com";
		String x_rapidapi_key = "";

		// Json response

		HttpResponse<JsonNode> response = null;
		if (Countryname.equals("ALL")) {
			System.out.println("Inside If   " + host);
			/* HttpResponse<JsonNode> */ response = Unirest.get(host).header("x-rapidapi-host", x_rapidapi_host)
					.header("x-rapidapi-key", x_rapidapi_key).asJson();

		} else {
			System.out.println("Else If   " + countryHost);
			/* HttpResponse<JsonNode> */ response = Unirest.get(countryHost + Countryname)
					.header("x-rapidapi-host", x_rapidapi_host).header("x-rapidapi-key", x_rapidapi_key).asJson();

		}
		// Prettifying
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(response.getBody().toString());
		String prettyJsonString = gson.toJson(je);
		System.out.println("Prettifying " + prettyJsonString);

		return prettyJsonString;

	}

	public HashMap<String, String> getAllData(String ALL) throws UnsupportedEncodingException, UnirestException {
		String finalData = settup(ALL);

		JSONArray Jarray = new JSONArray(finalData);
		HashMap<String, String> Hmap = new HashMap<String, String>(Jarray.length());
		for (int i = 0; i < Jarray.length(); i++) {
			JSONObject obj = (JSONObject) Jarray.get(i);
			Iterator<String> iterator = obj.keys();
			while (iterator.hasNext()) {
				String currentKey = iterator.next();
				Hmap.put(currentKey, obj.getString(currentKey));
			}
		}

		for (Entry<String, String> entry : Hmap.entrySet())
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		return Hmap;
	}

	public HashMap<String, String> getCountryWiseData(String ALL)
			throws UnsupportedEncodingException, UnirestException {

		String finalData = settup(ALL);

		JSONArray Jarray = new JSONArray(finalData);
		HashMap<String, String> Hmap = new HashMap<String, String>(Jarray.length());
		for (int i = 0; i < Jarray.length(); i++) {
			JSONObject obj = (JSONObject) Jarray.get(i);
			Iterator<String> iterator = obj.keys();
			while (iterator.hasNext()) {
				String currentKey = iterator.next();
				Long big = 0L;
				if (currentKey.equals("country")) {
					System.out.println("key  inside if   " + currentKey);
					Hmap.put(currentKey, obj.getString(currentKey));
				} else {
					System.out.println("key  inside else  " + currentKey);
					Hmap.put(currentKey, String.valueOf(obj.getLong(currentKey)));

				}

			}
		}

		for (Entry<String, String> entry : Hmap.entrySet())
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		return Hmap;    

	}
}
