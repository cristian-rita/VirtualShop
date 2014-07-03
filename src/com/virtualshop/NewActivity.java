package com.virtualshop;
import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.app.AlertDialog;

public class NewActivity extends ListActivity {

	ListView lista;
	Button btn2; //add
	Button btn3; //buy
	ArrayList<String> produse;
	ArrayAdapter<String> adapter;


	@Override

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.newactivity);

		lista = (ListView) findViewById(android.R.id.list);
		btn2 = (Button) findViewById(R.id.button2);
		btn3 = (Button) findViewById(R.id.button3);
		final Context context = this;

		produse = new ArrayList<String>();
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,produse);
		setListAdapter(adapter);

		produse.add("dsadas");
		lista.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adaptor, View arg1, int position,long id) 
			{
				produse.remove(position);
				adapter.notifyDataSetChanged();
				return true;
			}
		});

		btn3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(context);

				// Setting Dialog Title
				alertDialog2.setTitle("Purchase confirmation");

				// Setting Dialog Message
				alertDialog2.setMessage("Are you sure you want to buy these products worth:    ?");

				// Setting Icon to Dialog
		

				// Setting Positive "Yes" Btn
				alertDialog2.setPositiveButton("YES",
				        new DialogInterface.OnClickListener() {
				            public void onClick(DialogInterface dialog, int which) {
				                // Write your code here to execute after dialog
				                Toast.makeText(getApplicationContext(),
				                        "Your purchase is currently being processed. Thanks for using our App! :) ", Toast.LENGTH_SHORT)
				                        .show();
				            }
				        });
				// Setting Negative "NO" Btn
				alertDialog2.setNegativeButton("NO",
				        new DialogInterface.OnClickListener() {
				            public void onClick(DialogInterface dialog, int which) {
				                // Write your code here to execute after dialog
				                dialog.cancel();
				            }
				        });
				alertDialog2.show();

			}
		});
		btn2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(NewActivity.this,NewActivity2.class);
				startActivity(intent);
				
				
			}
		});
	
	}
}


