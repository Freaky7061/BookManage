package com.example.mybookmanagesystem;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class UserInfo extends Activity {
Button AddUserInfoBtn,UserViewBtn,returnBeforeBtn,returnLoginBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_info);
		AddUserInfoBtn=(Button)findViewById(R.id.button1);
		UserViewBtn=(Button)findViewById(R.id.button2);
		returnBeforeBtn=(Button)findViewById(R.id.button3);
		returnLoginBtn=(Button)findViewById(R.id.button4);
	//�����û���Ϣ
		AddUserInfoBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO �Զ����ɵķ������
				Intent intent=new Intent(UserInfo.this,AddUserInfo.class);
				startActivity(intent);
				Toast.makeText(UserInfo.this,"进入添加用户信息", Toast.LENGTH_LONG).show();
			}
		});
	//�鿴�û���Ϣ
		UserViewBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO �Զ����ɵķ������
				Intent intent=new Intent(UserInfo.this,ViewUserInfo.class);
				startActivity(intent);
				Toast.makeText(UserInfo.this, "进入查看用户信息", Toast.LENGTH_LONG).show();
			}
		});
	//������һ��
		returnBeforeBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO �Զ����ɵķ������
			Intent intent=new Intent(UserInfo.this,AdminActivity.class);
			startActivity(intent);
			Toast.makeText(UserInfo.this,"进入管理员界面", Toast.LENGTH_LONG).show();
			}
		});
	//���ص���¼����
		returnLoginBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO �Զ����ɵķ������
			Intent intent=new Intent(UserInfo.this,Login.class);
			startActivity(intent);
			Toast.makeText(UserInfo.this,"进入登录界面", Toast.LENGTH_LONG).show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_info, menu);
		return true;
	}

}
