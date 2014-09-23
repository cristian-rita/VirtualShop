package com.virtualshop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class NewActivity2 extends ListActivity {

	Button btn4;
	ListView lista3;
	ArrayList<String> produse = null;
	Product p;
	ArrayAdapter<String> adapter;
	ArrayList<Double> preturi;
	ArrayList<String> descriere;
	protected Intent resultIntent;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.newactivity2);
		
		produse = new ArrayList<String>();
		preturi = new ArrayList<Double>();
		descriere = new ArrayList<String>();

		new FetchData().execute();
		lista3 = (ListView) findViewById(android.R.id.list);
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, produse);
		setListAdapter(adapter);
		
		adapter.notifyDataSetChanged();
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

				Product p = new Product(produse.get(position), preturi
						.get(position), descriere.get(position));

				Intent resultIntent = new Intent();
				resultIntent.putExtra("mydata", p);
				setResult(Activity.RESULT_OK, resultIntent);

				finish();
			}
		});

	}

	public Void getDataFromJson(String response) throws JSONException {

		JSONObject dataJson = new JSONObject(response);
		JSONArray descJson = dataJson.getJSONArray("description");
		JSONArray nameJson = dataJson.getJSONArray("name");
		JSONArray priceJson = dataJson.getJSONArray("price");
			for (int i = 0; i < nameJson.length(); i++) {
				produse.add(nameJson.getString(i));
				preturi.add(priceJson.getDouble(i));
				descriere.add(descJson.getString(i));
			}
			adapter.notifyDataSetChanged();
	
		return null;
	}

	public class FetchData extends AsyncTask<Void, Void, String> {

		private final String LOG_TAG = FetchData.class.getSimpleName();

		@Override
		protected String doInBackground(Void... params) {

			HttpURLConnection urlConnection = null;
			BufferedReader reader = null;

			String dataJsonStr = null;

			try {

				final String BASE_URL = "http://192.168.1.6:8120/api/list";

				// Construct the URL

				Uri builtUri = Uri.parse(BASE_URL);

				URL url = new URL(builtUri.toString());

				// Create the request to the server and open the connection
				urlConnection = (HttpURLConnection) url.openConnection();
				urlConnection.setRequestMethod("GET");
				urlConnection.connect();

				// Read the input stream into a string
				InputStream inputStream = urlConnection.getInputStream();
				StringBuffer buffer = new StringBuffer();

				if (inputStream == null) {
					// nothing to do
					return null;
				}

				reader = new BufferedReader(new InputStreamReader(inputStream));
				String line;
				while ((line = reader.readLine()) != null) {
					buffer.append(line + "\n");
				}

				//System.out.println(buffer);

				if (buffer.length() == 0) {
					// String was empty
					return null;
				}
				dataJsonStr = buffer.toString();
				System.out.println(buffer);

			} catch (IOException e) {
				Log.e(LOG_TAG, "error", e);
				// If the code didn't successfully get the data there's no point
				// in attempting to parse it

				return null;
			} finally {
				if (urlConnection != null) {
					urlConnection.disconnect();
				}
				if (reader != null) {
					try {
						reader.close();
					} catch (final IOException e) {
						Log.e(LOG_TAG, "Error closing stream", e);
					}
				}
			}

			return dataJsonStr;
		}

		@Override
		protected void onPostExecute	/**
		 * 
		 */(String dataJsonStr) {
			try {
				getDataFromJson(dataJsonStr);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
