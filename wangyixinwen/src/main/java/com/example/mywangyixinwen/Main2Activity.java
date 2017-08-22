package com.example.mywangyixinwen;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.umeng.socialize.UMShareAPI;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import cn.sharesdk.onekeyshare.OnekeyShare;

public class Main2Activity extends AppCompatActivity implements ThemeManager.OnThemeChangeListener,BlankFragment.CallBackValue {
    private DrawerLayout drawerLayout;

    private ActionBarDrawerToggle toggle;
    private MenuInflater menuInflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //初始化控件
        initView();
        //初始化ActionBar
        initActionBar();
        //初始化ViewPager;
        ThemeManager.registerThemeChangeListener(this);
    }

    //初始化ActionBar,设置左上角按钮，当点击是弹出左边的抽屉Fragment
    private void initActionBar() {
        //获取ActionBar对象
        ActionBar actionBar=getSupportActionBar();
        //给左上角添加一个返回的图标，参数true为加上这个图标（在android4.0及以上默认false）
        actionBar.setDisplayHomeAsUpEnabled(true);
        //这个类提供了一种方便的方式来绑定功能 DrawerLayout和ActionBar来实现推荐的导航抽屉设计
        //第一个参数上下文,第二个DrawerLayout控件第三个资源文件（只是为了人性化，照顾盲人，点击时会发出声音）
        toggle = new ActionBarDrawerToggle(Main2Activity.this, drawerLayout, R.string.open, R.string.close);
        //将抽屉指示器的状态与链接的DrawerLayout同步其状态
        toggle.syncState();
        // ActionBar关联DrawerLayout
        drawerLayout.addDrawerListener(toggle);
    }
    //设置左上角及右上角图标具有的点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_search://点击右侧弹出框
                //点击弹出右侧滑菜单
               drawerLayout.openDrawer(GravityCompat.END);
                //不管左侧滑菜单是否打开   将其直接关闭
                drawerLayout.closeDrawer(GravityCompat.START);
                //如果右侧滑菜单是打开状态
                if (drawerLayout.isDrawerOpen(GravityCompat.END)){
                    //再次点击右上角关闭
                    drawerLayout.closeDrawer(GravityCompat.END);
                }
                //如果左侧滑菜单是打开状态
                if (drawerLayout.isDrawerOpen(GravityCompat.START)){
                    //点击右上角把两边都关闭
                    drawerLayout.closeDrawer(GravityCompat.START);
                    drawerLayout.closeDrawer(GravityCompat.END);
                }
                break;
            case R.id.tianqi:
                Toast.makeText(Main2Activity.this,"天气",Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(Main2Activity.this,Main5Activity.class));
                break;
            case R.id.lixian:
                Toast.makeText(Main2Activity.this,"离线",Toast.LENGTH_SHORT).show();
                break;
            case R.id.yejian:
                drawerLayout.setBackgroundColor(getResources().getColor(ThemeManager.getCurrentThemeRes(this, R.color.backgroundColor)));
                Toast.makeText(Main2Activity.this,"夜间",Toast.LENGTH_SHORT).show();
                //夜间模式
                ThemeManager.setThemeMode(ThemeManager.getThemeMode() == ThemeManager.ThemeMode.DAY
                        ? ThemeManager.ThemeMode.NIGHT : ThemeManager.ThemeMode.DAY);
                break;
            case R.id.fenxiang:
                Toast.makeText(Main2Activity.this,"分享",Toast.LENGTH_SHORT).show();
                //第三方分享
                showShare();
                break;
            case R.id.saoyisao:
                Toast.makeText(Main2Activity.this,"扫一扫",Toast.LENGTH_SHORT).show();
                //在代码中执行打开扫描二维码界面操作,
                // 这里的5是为了方便我们接收的onActivityResult分别进行处理.
                //二维码扫一扫
                Intent intent = new Intent(Main2Activity.this, CaptureActivity.class);
                startActivityForResult(intent,5);
                break;
            case R.id.yuyin:
                Toast.makeText(Main2Activity.this,"语音",Toast.LENGTH_SHORT).show();
                //第三方讯飞语音
                startActivity(new Intent(Main2Activity.this,Main4Activity.class));
                break;
        }

        if (toggle.onOptionsItemSelected(item)){
            //如果右侧滑菜单是打开状态
            if (drawerLayout.isDrawerOpen(GravityCompat.END)){
                //点击左上角关闭两个侧滑菜单
                drawerLayout.closeDrawer(GravityCompat.START);
                drawerLayout.closeDrawer(GravityCompat.END);
            }

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //初始化控件
    private void initView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

    }
   //填充ActionBar布局（Menu）
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //获得填充工具对象
        menuInflater = getMenuInflater();
        //填充actionbar界面显示
        menuInflater.inflate(R.menu.actionbar,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public void initTheme() {
        // 设置标题栏颜色
        ActionBar bar=getSupportActionBar();
        if(bar != null){
            bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(ThemeManager.getCurrentThemeRes(Main2Activity.this, R.color.colorPrimary))));
        }
        // 设置状态栏颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setStatusBarColor(getResources().getColor(ThemeManager.getCurrentThemeRes(Main2Activity.this, R.color.colorPrimary)));
        }
    }

    @Override
    public void onThemeChanged() {
        initTheme();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ThemeManager.unregisterThemeChangeListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(Main2Activity.this).onActivityResult(requestCode, resultCode, data);
        if (requestCode == 5) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(Main2Activity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 5){
            // camear 权限回调
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                // 表示用户授权
                Toast.makeText(Main2Activity.this, " user Permission" , Toast.LENGTH_SHORT).show();
                camear();
            } else {
                //用户拒绝权限
                Toast.makeText(Main2Activity.this, " no Permission" , Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void camear(){
        try {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不     调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(getString(R.string.app_name));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

        // 启动分享GUI
        oks.show(this);
    }

    @Override
    public void SendManagerValue() {
        drawerLayout.closeDrawer(GravityCompat.START);
    }
}








