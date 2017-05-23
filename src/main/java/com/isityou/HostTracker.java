package com.isityou;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class HostTracker {
	
	public static void main(String[] args) {
		System.out.println("Starting the Host Tracker...");
		
		HostTracker h = new HostTracker();
		
		
		String urlStr = "https://pix2you.com/is_alive";
		System.out.println("URL:" + urlStr);

		boolean hostAvailable = h.checkHostAvailability(urlStr);
		
		if(!hostAvailable)
		{
			SendGmail gmail = new SendGmail("shai.amar@isityou-online.com", "Zinazina10");
			
			InternetAddress[] toArray;
			try {
				toArray = InternetAddress.parse("eitan.brown@isityou-online.com, shai.amar@isityou-online.com");
				
				String from = "shai.amar@isityou-online.com";
				String subject = "Pix2You.com is not available";

				String message = "pix2you.com is not available at the moment. Please contact your administrator for help.";
				
				gmail.sendMessage(from, toArray, subject, message);
				
				System.out.println("Complete");

			} 
			catch (AddressException e) {
				e.printStackTrace();
			}

		}
	}
	
	private boolean checkHostAvailability(String urlStr)
	{
		if(urlStr == "")
		{
			System.out.println("The URL is empty.");
			return false;
		}
		else
		{
			HttpURLConnection con = null;
			
			try {
				URL url = new URL(urlStr);
				con = (HttpURLConnection) url.openConnection();
				
				con.setRequestMethod("GET");
				
				int responseCode = con.getResponseCode();
				System.out.println("Response code:" + responseCode);
				
				// Reading response from input Stream
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				  
				String output;
				StringBuffer response = new StringBuffer();
				  
				while ((output = in.readLine()) != null) 
				{
					response.append(output);
				}
				in.close();
				
				System.out.println("response:" + response.toString());
				
				if(responseCode == 200)
				{
					return true;
				}
				else
				{
					return false;
				}
			} 
			catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		return false;
	}
	
}
