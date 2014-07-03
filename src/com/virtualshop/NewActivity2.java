package com.virtualshop;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;

public class NewActivity2 extends ListActivity{
	
	Button btn4;
	ListView lista3;
	ArrayList<String> produse;
	ArrayAdapter<String> adapter;
	@Override

	
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.newactivity2);
		
		lista3 = (ListView) findViewById(android.R.layout.simple_list_item_1);
		produse = new ArrayList<String>();
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,produse);
		setListAdapter(adapter);

		produse.add("dsadas");

		btn4 = (Button) findViewById(R.id.button4);
		btn4.setOnClickListener(new View.OnClickListener() {
	
			@Override
			public void onClick(View v) {
				finish();
				// TODO Auto-generated method stub
				
			}
		});
	}
	
}
