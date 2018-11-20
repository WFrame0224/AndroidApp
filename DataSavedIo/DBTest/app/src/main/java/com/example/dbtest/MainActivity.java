package com.example.dbtest;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    /**
     * 使用SQLiteDataBase进行数据库操作的步骤：
     *  1）获取SQLiteDatabase对象，它代表了与数据库的连接
     *  2）调用SQLiteDatabase的方法来执行SQL语句
     *  3）操作SQL语句的执行结果，比如用SimpleCursorAdapter封装Cursor
     *  4)关闭SQLiteDatabase,回收资源
     */
    SQLiteDatabase db;
    Button bn = null;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 创建或打开数据库（此处需要绝对路径）
        db = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString() + "/my.db3",null);
        listView = (ListView)findViewById(R.id.show);
        bn = (Button)findViewById(R.id.ok);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 获取用户输入
                String title = ((EditText)findViewById(R.id.title)).getText().toString();
                String content = ((EditText)findViewById(R.id.content)).getText().toString();
                try {
                    insertData(db,title,content);
                    Cursor cursor = db.rawQuery("select * from news_inf",null);
                    inflateList(cursor);
                }catch (SQLException se){
                    // 执行DDL创建数据表
                    db.execSQL("create table news_inf(_id integer"
                    + " primary key autoincrement,"
                    + " news_title varchar(50),"
                    + " news_content varchar(255))");
                    // 执行insert语句插入数据
                    insertData(db,title,content);
                    // 执行查询
                    Cursor cursor = db.rawQuery("select * from news_inf",null);
                    inflateList(cursor);
                }
            }
        });
    }

    private void inflateList(Cursor cursor) {
        // 填充SimpleCursorAdapter
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                MainActivity.this,
                R.layout.line,cursor,
                new String[]{"news_title","news_content"},
                new int[]{R.id.my_title,R.id.my_content},CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        // 显示数据
        listView.setAdapter(adapter);
    }

    private void insertData(SQLiteDatabase db, String title, String content) {
        // 执行插入语句
        db.execSQL("insert into news_inf values(null ,? , ?)",new String[]{title,content});
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        // 退出程序时关闭SQLiteDatabase
        if (db != null && db.isOpen()){
            db.close();
        }
    }
}
