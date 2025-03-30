package com.example.mybookmanagesystem;

import java.util.List;

import Adapter.BookAdaper;
import Adapter.BorrowAdapter;
import Bean.Book;
import Bean.Borrow;
import DB.BookBean;
import DB.BorrowBean;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class BorrowBook extends Activity {
	ListView list;
	Button quit;
	String id, bookid;
	int booknumber;
	BorrowBean borrowBean = new BorrowBean(BorrowBook.this);
	BookBean bookBean = new BookBean(BorrowBook.this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.borrow_book);
		list = (ListView) findViewById(R.id.listView1);
		quit = (Button) findViewById(R.id.button1);

		Intent intent = getIntent();
		id = intent.getStringExtra("id");

		quit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
				Toast.makeText(BorrowBook.this, "进入用户图书借阅", Toast.LENGTH_LONG).show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.borrow_book, menu);
		return true;
	}

	@Override
	protected void onStart() {
		super.onStart();
		List<Borrow> borrows = borrowBean.UserAllBorrowBooks(id);
		BorrowAdapter myAdapter = new BorrowAdapter(BorrowBook.this, R.layout.borrowlist, borrows);
		list.setAdapter(myAdapter);
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

				Borrow borrow = (Borrow) arg0.getItemAtPosition(arg2);
				bookid = borrow.getBookid();
				AlertDialog.Builder builder = new AlertDialog.Builder(BorrowBook.this);
				builder.setTitle("是否归还图书");
				builder.setPositiveButton("是的，归还", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {

						bookBean = new BookBean(BorrowBook.this);
						Book tempbook = bookBean.ReturnBookNumberChange(bookid);
						bookBean.UpdateBorrowOrReturnBookInfo(tempbook);
						Borrow borrow = new Borrow(id, bookid);
						borrowBean.deleteBorrowBookInfo(borrow);
						Toast.makeText(getApplicationContext(),  "还书操作成功", Toast.LENGTH_LONG).show();
						finish();
					}
				});
				builder.setNegativeButton("不了", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						finish();
					}
				});
				builder.show();

			}
		});
	}

}
