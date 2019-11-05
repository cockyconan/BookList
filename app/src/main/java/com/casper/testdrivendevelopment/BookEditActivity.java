package com.casper.testdrivendevelopment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class BookEditActivity extends AppCompatActivity {
    private int EditPosition;//把位置也传递了
    private EditText editTextGoodName,editTextGoodPrice;//用来传递名称和价格
    private Button buttonOk,buttonCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_edit);

        EditPosition=getIntent().getIntExtra("position",0);//把位置参数接收一下。
        editTextGoodName=(EditText)findViewById(R.id.editText_book_title);
        //editTextGoodPrice=(EditText)findViewById(R.id.editText_book_price);
        buttonOk=(Button)findViewById(R.id.button_ok);
        buttonCancel=(Button)findViewById(R.id.button_cancel);

        //double goodPrice = getIntent().getDoubleExtra("good_price",-1);//如果没有收到，则goodPrice默认值为-1
        String goodName = getIntent().getStringExtra("good_name");//前面的字符串是另一个界面传递出来时命名的
        if(goodName!= null)//这里加个判断是因为如果没有收到对方传来的值，则这个string对象默认为空对象
        {
            editTextGoodName.setText(goodName);
            //editTextGoodPrice.setText(goodPrice+"");

        }
        buttonCancel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                BookEditActivity.this.finish();//取消按钮的话，不需要进行任何改变，直接关闭窗口即可
            }
        });
        buttonOk.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent();//点击了ok，新建一个intent传递消息
                intent.putExtra("good_name",editTextGoodName.getText().toString().trim());//trim函数清除除了单词之间之外的所有空白

                intent.putExtra("position",EditPosition);//把位置参数传回去
                setResult(RESULT_OK,intent);//设置状态，方便另一个界面进行相应的操作
                BookEditActivity.this.finish();//关闭窗口
            }



        });
    }
}
