package com.alex.retrofitapp.flow.repos;

import com.alex.retrofitapp.base.BaseDataSource;
import com.alex.retrofitapp.model.Repo;

import java.util.List;

import rx.Single;

/**
 * Created by 430 on 08.02.2017.
 */

public interface ReposDataSource extends BaseDataSource{

    Single<List<Repo>> getRepos(String user);

    void clearRepos();
}
