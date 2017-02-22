package com.alex.retrofitapp.base;

import com.alex.retrofitapp.model.Repo;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Single;

/**
 * Created by 430 on 08.02.2017.
 */

public interface ReposService {

    @GET("/users/{user}/repos")
    Single<List<Repo>> getRepos(@Path("user") String user);
}

