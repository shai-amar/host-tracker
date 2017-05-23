package org.sa;

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
		
		HostTracker h = new HostTracker();
		
		
		//	The URL you wish to check (this one is not real)
		//	Normally it is a GET request that returns a is_alive response (HTML status 200)
		String urlStr = "https://example.com/is_alive";

		boolean hostAvailable = h.checkHostAvailability(urlStr);
		
		if(!hostAvailable)
		{
			// You need to configure a mail that sends the mails to the proper administrators
			SendGmail gmail = new SendGmail("my_name@gmail.com", "password");
			
			InternetAddress[] toArray;
			try {
				toArray = InternetAddress.parse("shai.amar@example.com, eitan.brown@example.com");
				
				String from = "my_name@gmail.com";
				String subject = "The subject of the mail";

				String message = "This server is not available at the moment. Please contact your administrator for help.";
				
				gmail.sendMessage(from, toArray, subject, message);
				
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
