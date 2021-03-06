package com.example.menurestestui;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView txt;
    PopupMenu popupMenu = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = (TextView) findViewById(R.id.txt);
        // 为文本框注册上下文菜单
        registerForContextMenu(txt);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        // 装填R.menu.my_menu对应的菜单，并添加到menu中
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // 创建上下文菜单时触发该方法
    @Override
    public void onCreateContextMenu(ContextMenu menu, View source, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = new MenuInflater(this);
        // 装填R.menu.context对应的菜单，并添加到menu中
        inflater.inflate(R.menu.context, menu);
        menu.setHeaderIcon(R.drawable.tools);
        menu.setHeaderTitle("请选择背景色");
    }

    // 上下文菜单中菜单项被单击时触发该方法
    @Override
    public boolean onContextItemSelected(MenuItem mi) {
        // 勾选该菜单项
        mi.setChecked(true);  // ①
        switch (mi.getItemId()) {
            case R.id.red:
                mi.setChecked(true);
                txt.setBackgroundColor(Color.RED);
                break;
            case R.id.green:
                mi.setChecked(true);
                txt.setBackgroundColor(Color.GREEN);
                break;
            case R.id.blue:
                mi.setChecked(true);
                txt.setBackgroundColor(Color.BLUE);
                break;
        }
        return true;
    }

    @Override
    // 菜单项被单击后的回调方法
    public boolean onOptionsItemSelected(MenuItem mi) {
        if (mi.isCheckable()) {
            // 勾选该菜单项
            mi.setChecked(true);  // ②
        }
        //判断单击的是哪个菜单项，并有针对性地作出响应
        switch (mi.getItemId()) {
            case R.id.font_10:
                txt.setTextSize(10 * 2);
                break;
            case R.id.font_12:
                txt.setTextSize(12 * 2);
                break;
            case R.id.font_14:
                txt.setTextSize(14 * 2);
                break;
            case R.id.font_16:
                txt.setTextSize(16 * 2);
                break;
            case R.id.font_18:
                txt.setTextSize(18 * 2);
                break;
            case R.id.red_font:
                txt.setTextColor(Color.RED);
                mi.setChecked(true);
                break;
            case R.id.green_font:
                txt.setTextColor(Color.GREEN);
                mi.setChecked(true);
                break;
            case R.id.blue_font:
                txt.setTextColor(Color.BLUE);
                mi.setChecked(true);
                break;
            case R.id.plain_item:
                Toast toast = Toast.makeText(MainActivity.this
                        , "您单击了普通菜单项", Toast.LENGTH_SHORT);
                toast.show();
                break;
        }
        return true;
    }

    /**创建PopupMenu创建弹出式菜单的步骤
     * 1.调用new PopupMenu(Context context,View anchor)创建下拉式菜单,anchor代表要激发菜单的组件
     * 2.调用MenuInflater的Infalte()方法将菜单资源填充到popupmenu中
     * 3.调用Popupmenu的show()方法显示弹出式菜单
     *
     * @param button
     */
    public void onPopupButtonClick(View button) {
        // 创建PopupMenu对象
        popupMenu = new PopupMenu(this, button);
        // 将R.menu.context菜单资源项加载到PopupMenu菜单中
        getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        // 为popup菜单的菜单项单击事件绑定监视器
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.exit:
                        // 隐藏该对话框
                        popupMenu.dismiss();
                        break;
                    default:
                        // 使用Toast显示用户单击的菜单项
                        Toast.makeText(MainActivity.this,
                                "您单击了【" + menuItem.getTitle() + "】菜单项",
                                Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
        popupMenu.show();
    }
}
