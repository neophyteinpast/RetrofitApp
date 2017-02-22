package com.alex.retrofitapp;

import android.content.Context;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.alex.retrofitapp.R;
import com.alex.retrofitapp.adapter.ReposAdapter;
import com.alex.retrofitapp.flow.repos.presenter.ReposPresenter;
import com.alex.retrofitapp.flow.repos.view.ReposView;
import com.alex.retrofitapp.model.Repo;
import com.jakewharton.rxbinding.support.v7.widget.RxSearchView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.android.schedulers.AndroidSchedulers;

public class MainActivity extends AppCompatActivity implements ReposView {

    private ReposPresenter presenter = new ReposPresenter();

    private RecyclerView mRecyclerView;
    private ReposAdapter mReposAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        presenter.onAttach(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mLayoutManager = new LinearLayoutManager(this, mRecyclerView.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mReposAdapter = new ReposAdapter();
        // specify an adapter (see also next example)
        mRecyclerView.setAdapter(mReposAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        RxSearchView.queryTextChanges(searchView)
                .debounce(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .skip(1)
                .subscribe(query ->
                presenter.getRepos(query.toString()));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void showRepos(List<Repo> dataSource) {
        mReposAdapter.setDataSource(dataSource);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }
}
