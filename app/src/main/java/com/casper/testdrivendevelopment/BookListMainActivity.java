package com.casper.testdrivendevelopment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BookListMainActivity extends AppCompatActivity {
    private ArrayList<Book> allbooks;
    private ListView listViewbook;
    private BookArrayAdapter DaAdapter;
    private Fragment_BookList fragment_bookList_set;
    private FileDataSource fileDataSource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list_main);

        initialbook();

        DaAdapter =new BookArrayAdapter(this,R.layout.book_list_view, allbooks);
        BookFragmentPagerAdapter myPageAdapter= new BookFragmentPagerAdapter(getSupportFragmentManager());
        ArrayList<Fragment> datas = new ArrayList<Fragment>();
        datas.add(new Fragment_BookList(DaAdapter));
        datas.add(new Web_news_Fragment());
        datas.add(new BaiduMap_fragment());
        myPageAdapter.setDatas(datas);

        ArrayList<String> titles= new ArrayList<String>();
        titles.add("图书");
        titles.add("新闻");
        titles.add("卖家");
        myPageAdapter.setTitles(titles);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(myPageAdapter);
        tabLayout.setupWithViewPager(viewPager);
        //DaAdapter声明要放在外面！！！

        //


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode)
        {
            case 901:
                if(resultCode==RESULT_OK){
                    int position=data.getIntExtra("position",0);//这里肯定在这个程序中只是接收，因为editmainactivity已经完成操作了
                    String name=data.getStringExtra("good_name");


                    allbooks.add(position, new Book(name,R.drawable.a4));
                    DaAdapter.notifyDataSetChanged();

                    Toast.makeText(this, R.string.createsuccess, Toast.LENGTH_SHORT).show();
                    break;
                }
            case 902:
                if(resultCode==RESULT_OK){
                    int position=data.getIntExtra("position",0);
                    String name=data.getStringExtra("good_name");


                    Book Abook =allbooks.get(position);
                    Abook.setTitle(name);


                    DaAdapter.notifyDataSetChanged();

                    Toast.makeText(this, R.string.altersuccess, Toast.LENGTH_SHORT).show();
                    break;
                }

        }
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //上面view v就是传进来的控件，可以加个判断v是否等于listviewsuper，
        // 但是这里只为一个控件创建了场景菜单，所有不用判断，但如果为多个视图创建了创景菜单，
        // 加个判断语句就可为不同的视图创建不同的场景菜单
        //menuinfo转为
        if(true)
        {
            int itemposiion=((AdapterView.AdapterContextMenuInfo)menuInfo).position;
            menu.setHeaderTitle(allbooks.get(itemposiion).getTitle());//设定场景菜单的标题
            menu.add(0, 1, 0, R.string.create);
            menu.add(0, 2, 0, R.string.delete);
            menu.add(0, 3, 0, R.string.about);
            menu.add(0,4,0,R.string.alter);
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {//菜单出来，就调用这个来相应相关事件
        AdapterView.AdapterContextMenuInfo menuInfo;
        switch(item.getItemId()) {
            case 1:
                menuInfo=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
                Intent intent =new Intent(BookListMainActivity.this,BookEditActivity.class);//这个intent是在当前这个activity和goodeditactivity交换信息的。
                intent.putExtra("position",menuInfo.position);
                startActivityForResult(intent,901);//在这里提示上面的重载函数，为901

                break;
            case 2:
                menuInfo=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
                final int itempos=menuInfo.position;
                Boolean DeleteorNot=false;
                new AlertDialog.Builder(this)
                        .setIcon(R.drawable.skull)
                        .setTitle(R.string.delete_watchout)
                        .setMessage(R.string.delete_ask)
                        .setPositiveButton(R.string.delete_sureanswer, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                allbooks.remove(itempos);//删除menuinfo的position位置的数据
                                DaAdapter.notifyDataSetChanged();//刷新一下界面
                                Toast.makeText(BookListMainActivity.this,R.string.delete_success,Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton(R.string.delete_negativeanswer,new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(BookListMainActivity.this,R.string.delete_negativeanswer_curse,Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create().show();



                Toast.makeText(this, R.string.delete_selected_toast, Toast.LENGTH_SHORT).show();
                break;
            case 3:
                menuInfo=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
                int itemposiion=((AdapterView.AdapterContextMenuInfo)menuInfo).position;
                Toast.makeText(this,"This is "+allbooks.get(itemposiion).getTitle(),Toast.LENGTH_SHORT).show();
                break;
            case 4:
                menuInfo=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
                Intent intent1 =new Intent(BookListMainActivity.this,BookEditActivity.class);
                Book good =allbooks.get(menuInfo.position);//这里因为要传送goodprice等信息，必须先获取good这个项目

                intent1.putExtra("good_name",good.getTitle());
                intent1.putExtra("position",menuInfo.position);
                startActivityForResult(intent1,902);
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fileDataSource.save();//传对象，所以无需另外再吧allbooks传回去
    }

    private void initialbook() {
        //allbooks=new ArrayList<Book>();
        //allbooks.add(new Book("软件项目管理案例教程（第4版）", R.drawable.book_2));
        //allbooks.add(new Book("创新工程实践", R.drawable.book_no_name));
        //allbooks.add(new Book("信息安全数学基础（第2版）", R.drawable.book_1));
        fileDataSource = new FileDataSource(this);
        allbooks=fileDataSource.load();//java的对象传递其实就是传指针
        if(allbooks.size()==0)
        {
            allbooks.add(new Book("no book yet", R.drawable.book_no_name));
        }

    }

    protected ArrayList<Book> getListBooks()
    {
        return allbooks;
    }

}
