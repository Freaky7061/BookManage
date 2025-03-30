package com.example.mybookmanagesystem;

import java.util.List;
import Adapter.UserAdaper;
import Bean.User;
import DB.UserBean;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ViewUserInfo extends Activity {
ListView list;//�벼���е�listView�����
Button quit;
UserBean userBean=new UserBean(ViewUserInfo.this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_user_info);
		list=(ListView)findViewById(R.id.listView1);
		quit=(Button)findViewById(R.id.button1);
	//�˳��鿴��������Ӧ
		quit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO �Զ����ɵķ������
				Intent intent=new Intent(ViewUserInfo.this,UserInfo.class);
				startActivity(intent);
				Toast.makeText(ViewUserInfo.this, "返回用户信息管理界面", Toast.LENGTH_LONG).show();
			}
		});
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_user_info, menu);
		return true;
	}

	
	@Override
	protected void onStart() {
		// TODO �Զ����ɵķ������
		super.onStart();
		List<User>users=userBean.showUserInfo();
		UserAdaper myAdapter=new UserAdaper(ViewUserInfo.this,R.layout.userlist,users);
		list.setAdapter(myAdapter);
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO �Զ����ɵķ������
				User user=(User)arg0.getItemAtPosition(arg2);
				final String id=user.getId();
				final String name=user.getName();
				final String classname=user.getClassName();
				final String password=user.getPassword();
				final String phonenumber=user.getPhonenumber();
				AlertDialog.Builder builder=new AlertDialog.Builder(ViewUserInfo.this);
				builder.setTitle("是否要编辑用户信息");
				builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						Intent intent=new Intent();
						Bundle bundel=new Bundle();
						bundel.putString("id", id);
						bundel.putString("name",name);
						bundel.putString("classname",classname);
						bundel.putString("password", password);
						bundel.putString("phonenumber", phonenumber);
						intent.setClass(getApplicationContext(),UpdateUserInfo.class);
						intent.putExtras(bundel);
						startActivity(intent);
					}});
				builder.setNegativeButton("取消",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						User user=new User(id,name,classname,password,phonenumber);
							userBean.deleteUserInfo(user);
						Toast.makeText(getApplicationContext(), "用户删除成功", Toast.LENGTH_LONG).show();
						onStart();
					}});
				builder.show();
				
			}
		});
	}
}
