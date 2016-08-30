package com.xsn.bigeball.view.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.xsn.bigeball.R;
import com.xsn.bigeball.model.AllType;
import com.xsn.bigeball.presenter.MainPresenter;
import com.xsn.bigeball.view.iView.IMainView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainPresenter> implements IMainView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    View view1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view1 = view;
                Snackbar.make(view, "正在加载数据，retrofit+mvp+rxJava学习", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                presenter.getData();
//                view.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        presenter.getData();
//                    }
//                });
            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initPresenter() {
        presenter = new MainPresenter(this, this);
        presenter.init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgress() {
        Toast.makeText(MainActivity.this, "正在请求后台", Toast.LENGTH_LONG).show();
    }

    @Override
    public void hideProgress() {
        Toast.makeText(MainActivity.this, "请求结束", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "请求结束");
    }

    @Override
    public void showErrorView() {
        Toast.makeText(MainActivity.this, "请求后台失败", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showAllTypes(AllType allTypes) {
        Toast.makeText(MainActivity.this, allTypes.getResultJson().get(0).getTypeName(), Toast.LENGTH_LONG).show();
        Snackbar.make(view1, "请求成功。" + allTypes.getResultJson().get(0).getTypeName(), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        Log.i(TAG, "请求成功");
    }

    @Override
    public void showAllNames(String name) {
        Snackbar.make(view1, "请求成功。" + name, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        Log.i(TAG, name);
    }

    @Override
    public void initView() {

    }
}
