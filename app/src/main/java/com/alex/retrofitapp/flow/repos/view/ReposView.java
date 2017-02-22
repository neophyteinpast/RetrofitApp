package com.alex.retrofitapp.flow.repos.view;

import android.content.Context;

import com.alex.retrofitapp.model.Repo;

import java.util.List;

/**
 * Created by 430 on 13.02.2017.
 */

public interface ReposView {
    void showRepos(List<Repo> repos);
    Context getContext();
}
