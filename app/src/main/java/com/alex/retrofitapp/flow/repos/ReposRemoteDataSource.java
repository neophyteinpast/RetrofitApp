package com.alex.retrofitapp.flow.repos;

import com.alex.retrofitapp.base.ReposService;
import com.alex.retrofitapp.model.Repo;

import java.util.List;

import com.alex.retrofitapp.base.BaseRemoteDataSource;
import rx.Single;

/**
 * Created by 430 on 08.02.2017.
 */

public class ReposRemoteDataSource extends BaseRemoteDataSource implements ReposDataSource{

    @Override
    public Single<List<Repo>> getRepos(String user) {
        return reposService.getRepos(user);
    }

    @Override
    public void clearRepos() {

    }
}
