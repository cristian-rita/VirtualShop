package com.virtualshop;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.*;

import com.json.parsers.JsonParserFactory;
import com.json.parsers.JSONParser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.apache.http.Header;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter.LengthFilter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;




public class NewActivity2 extends ListActivity{
	
	Button btn4;
	ListView lista3;
	ArrayList<String> produse;
	Product p;
	ArrayAdapter<String> adapter;
	ArrayList<Double> preturi;
	ArrayList<String> descriere;
	protected Intent resultIntent;
	@Override

	
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.newactivity2);
		
		lista3 = (ListView) findViewById(android.R.id.list);
		produse = new ArrayList<String>();
		descriere = new ArrayList<String>();
		preturi = new ArrayList<Double>();
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,produse);
		setListAdapter(adapter);

	
		 RequestParams params = new RequestParams();
	        params.put("api_auth", "VECeKU2puHatudreb7A3");
	        params.put("trigger", "user_profile");
	        params.put("id_user", "35");
	        params.put("session", "61ff95571aa3db550df827f0cae10c938cd31fd2");

	        AsyncHttpClient client = new AsyncHttpClient();
	        client.get("http://10.0.2.2:5000/api/list", new JsonHttpResponseHandler() {

	            @Override
	            public void onStart() {
	                // called before request is started
	            }

	            @Override
	            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
	                // called when response HTTP status is "200 OK"
	                System.out.println(response.toString());
	                JsonParserFactory factory=JsonParserFactory.getInstance();
	                JSONParser parser=factory.newJsonParser();
	                Map jsonData=parser.parseJson(response.toString());
	                List al = (List) jsonData.get("name");
	                List al2 =(List) jsonData.get("price");
	                List al3 =(List) jsonData.get("description");
	                for(int i=0;i<al.size();i++)
	                {
	                	produse.add((String) al.get(i));
	                	String pret = (String) al2.get(i);
	                	double val = Double.parseDouble(pret);
	                	preturi.add(val);
	                	descriere.add((String)al3.get(i));
	                	adapter.notifyDataSetChanged();
	                
	                }
	          
	               // string value=(String) jsonData.get("name")
	               
	            }

	            @Override
	            public void onFailure(int statusCode, Header[] headers, java.lang.Throwable throwable, JSONObject errorResponse) {
	                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
	                System.out.println(errorResponse.toString());
	            }

	            @Override
	            public void onRetry(int retryNo) {
	                // called when request is retried
	            }
	        });
	        

			btn4 = (Button) findViewById(R.id.button4);
			btn4.setOnClickListener(new View.OnClickListener() {
			
				@Override
				public void onClick(View v) {
					
					// TODO Auto-generated method stub
					
				}
			});
			
		      lista3.setOnItemClickListener(new OnItemClickListener() {

  				@Override
  				public void onItemClick(AdapterView<?> parent, View view,
  						int position, long id) {
  					
  					Product p = new Product(produse.get(position),
  											preturi.get(position),
  											descriere.get(position));
  					
  					Intent resultIntent = new Intent();
					resultIntent.putExtra("mydata",p);
					setResult(Activity.RESULT_OK,resultIntent);
					
					finish();
  				}
  			});
			
	    
		
		

	}
}

	

