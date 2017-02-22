package com.alex.retrofitapp.flow.repos;

import android.content.Context;
import android.util.Log;

import com.alex.retrofitapp.model.Repo;

import java.util.List;

import rx.Single;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by 430 on 13.02.2017.
 */

public class ReposRepository implements ReposDataSource {

    private ReposLocalDataSource localeSource = new ReposLocalDataSource();
    private ReposRemoteDataSource remoteSource = new ReposRemoteDataSource();

    @Override
    public Single<List<Repo>> getRepos(String user) {
        return remoteSource.getRepos(user)
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(list -> localeSource.saveRepos(list))
                .onErrorResumeNext(localeSource.getRepos(user));
    }

    @Override
    public void clearRepos() {
        localeSource.clearRepos();
    }

    @Override
    public void init(Context context) {
        localeSource.init(context);
        remoteSource.init(context);
    }
}
