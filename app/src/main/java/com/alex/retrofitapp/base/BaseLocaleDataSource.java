package com.alex.retrofitapp.base;

import android.content.Context;

import io.realm.Realm;

/**
 * Created by 430 on 08.02.2017.
 */

public abstract class BaseLocaleDataSource implements BaseDataSource{

    protected Realm realm = null;

    @Override
    public void init(Context context) {
        realm = Realm.getDefaultInstance();
    }
}
