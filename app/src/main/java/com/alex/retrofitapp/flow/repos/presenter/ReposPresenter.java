package com.alex.retrofitapp.flow.repos.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.alex.retrofitapp.flow.repos.ReposDataSource;
import com.alex.retrofitapp.flow.repos.ReposRepository;
import com.alex.retrofitapp.flow.repos.view.ReposView;
import com.alex.retrofitapp.model.Repo;

import java.util.List;

import rx.Single;
import rx.internal.util.SubscriptionList;

/**
 * Created by 430 on 13.02.2017.
 */

public class ReposPresenter implements ReposDataSource {

    protected ReposRepository reposRepository = new ReposRepository();
    protected ReposView reposView;
    protected SubscriptionList subscriptionList = new SubscriptionList();

    public void onAttach(ReposView view) {
        reposView = view;
        reposRepository.init(view.getContext());
    }

    public void onDetach(){
        subscriptionList.unsubscribe();
    }

    @Override
    public Single<List<Repo>> getRepos(String user) {
        reposRepository.getRepos(user)
                .subscribe(subscriptionList -> reposView.showRepos(subscriptionList), trowable -> {Toast.makeText(reposView.getContext(), "sdsd", Toast.LENGTH_SHORT).show(); });
        return null;
    }

    @Override
    public void clearRepos() {
        reposRepository.clearRepos();
    }

    @Override
    public void init(Context context) {
    }
}
