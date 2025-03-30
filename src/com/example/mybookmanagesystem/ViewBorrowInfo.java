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

public class ViewBorrowInfo extends Activity {
ListView list;//�벼���е�listView�����
Button quit;
String id,bookid;//ɾ��������Ϣ����ѧ�ź�ͼ����
BorrowBean borrowBean=new BorrowBean(ViewBorrowInfo.this);
BookBean bookBean=new BookBean(ViewBorrowInfo.this);//��������¼ʱʹ��
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_borrow_info);
		list=(ListView)findViewById(R.id.listView1);
		quit=(Button)findViewById(R.id.button1);
//�˳��鿴��������Ӧ
		quit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO �Զ����ɵķ������
				Intent intent=new Intent(ViewBorrowInfo.this,AdminActivity.class);
				startActivity(intent);
				Toast.makeText(ViewBorrowInfo.this,  "返回管理员界面", Toast.LENGTH_LONG).show();
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_borrow_info, menu);
		return true;
	}

	@Override
	protected void onStart() {
		// TODO �Զ����ɵķ������
		super.onStart();
		List<Borrow>borrows=borrowBean.showBorrowBookInfo();
		BorrowAdapter myAdapter=new BorrowAdapter(ViewBorrowInfo.this,R.layout.borrowlist,borrows);
		list.setAdapter(myAdapter);
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO �Զ����ɵķ������
				Borrow borrow=(Borrow)arg0.getItemAtPosition(arg2);
				id=borrow.getId();
				bookid=borrow.getBookid();
				AlertDialog.Builder builder=new AlertDialog.Builder(ViewBorrowInfo.this);
				builder.setTitle("是否删除借阅记录");
				builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						bookBean=new BookBean(ViewBorrowInfo.this);
						Book tempbook=bookBean.ReturnBookNumberChange(bookid);
						bookBean.UpdateBorrowOrReturnBookInfo(tempbook);
						Borrow borrow=new Borrow(id,bookid);
						borrowBean.deleteBorrowBookInfo(borrow);
						Toast.makeText(getApplicationContext(), "删除成功", Toast.LENGTH_LONG).show();
						finish();
					}});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				finish();
			}});
				builder.show();
			}
		});
	}

}
