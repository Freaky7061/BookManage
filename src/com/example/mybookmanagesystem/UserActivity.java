package com.example.mybookmanagesystem;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UserActivity extends Activity {
Button findBook,IsBorrorBook,UpdateMyPassword,quit;
TextView welcomeName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user);
		findBook=(Button)findViewById(R.id.button1);
		IsBorrorBook=(Button)findViewById(R.id.button2);
		UpdateMyPassword=(Button)findViewById(R.id.button3);
		quit=(Button)findViewById(R.id.button4);
		welcomeName=(TextView)findViewById(R.id.textView2);
	//�Ȱ�Login��������id��password����
		Intent intent=getIntent();
		final String MyId=intent.getStringExtra("id");//��ǰ�û����˺ź�����
		final String MyPassword=intent.getStringExtra("password");
		welcomeName.setText(MyId);
		
		
	//�����鼮
		findBook.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO �Զ����ɵķ������
				Intent intent=new Intent();
				Bundle bundle=new Bundle();
				bundle.putString("id", MyId);
				intent.putExtras(bundle);
				intent.setClass(getApplicationContext(), FindBook.class);
				startActivity(intent);
				Toast.makeText(UserActivity.this, "查找图书", Toast.LENGTH_LONG).show();
			}
		});
	//�Լ��Ľ������
		IsBorrorBook.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO �Զ����ɵķ������
				Intent intent=new Intent();
				Bundle bundle=new Bundle();
				bundle.putString("id", MyId);
				intent.putExtras(bundle);
				intent.setClass(getApplicationContext(), BorrowBook.class);
				startActivity(intent);
				Toast.makeText(UserActivity.this,  "借阅图书", Toast.LENGTH_LONG).show();
			}
		});
	//�޸��Լ��ĵ�¼����
		UpdateMyPassword.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO �Զ����ɵķ������
				Intent intent=new Intent();
				Bundle bundle=new Bundle();
				bundle.putString("id", MyId);
				bundle.putString("password",MyPassword);
				intent.putExtras(bundle);
				intent.setClass(getApplicationContext(), UpdatePassword.class);
				startActivity(intent);
				Toast.makeText(UserActivity.this,  "更新密码", Toast.LENGTH_LONG).show();
			}
		});
	//�˳���¼
		quit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO �Զ����ɵķ������
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user, menu);
		return true;
	}

}
