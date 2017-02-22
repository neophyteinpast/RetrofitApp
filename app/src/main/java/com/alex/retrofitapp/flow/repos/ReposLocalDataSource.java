package com.alex.retrofitapp.flow.repos;

import com.alex.retrofitapp.base.BaseLocaleDataSource;
import com.alex.retrofitapp.model.Repo;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Single;

/**
 * Created by 430 on 08.02.2017.
 */

public class ReposLocalDataSource extends BaseLocaleDataSource implements ReposDataSource{

    @Override
    public Single<List<Repo>> getRepos(String user) {
        return Single.create(subscriber -> {
            realm.executeTransaction(innerRealm -> {
                RealmResults<Repo> results = innerRealm.where(Repo.class).equalTo("name", user).findAll();
                if (results == null) {
                    subscriber.onError(new Exception("vse ploho"));
                } else {
                    List<Repo> repos = innerRealm.copyFromRealm(results);
                    if (repos != null) {
                        subscriber.onSuccess(repos);
                    } else {
                        subscriber.onError(new Exception("vse ploho2"));
                    }
                }
            });
        });
    }

    @Override
    public void clearRepos() {
    }

    public Single saveRepos(final List<Repo> list) {

        realm.executeTransaction(realm -> {
            realm.copyToRealmOrUpdate(list);
        });
        return Single.just(list);
    }
}
