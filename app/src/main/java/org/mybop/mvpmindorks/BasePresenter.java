package org.mybop.mvpmindorks;

import android.support.annotation.NonNull;

public abstract class BasePresenter<V extends MvpView, M extends MvpModel> implements MvpPresenter<V, M> {

    @NonNull
    private V view;

    @NonNull
    private M dataManager;

    public BasePresenter(@NonNull V view, @NonNull M dataManager) {
        this.view = view;
        this.dataManager = dataManager;
    }

    @NonNull
    public V getView() {
        return view;
    }

    @NonNull
    public M getDataManager() {
        return dataManager;
    }
}
