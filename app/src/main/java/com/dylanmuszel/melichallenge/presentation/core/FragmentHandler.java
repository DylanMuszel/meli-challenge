package com.dylanmuszel.melichallenge.presentation.core;

import androidx.annotation.NonNull;

import javax.inject.Inject;

/**
 * This class is responsible of the communication between the fragment and the presenter.
 * It's coded on Java because Kotlin Generics don't allow to do this.
 *
 * @param <T> The type of the {@link BasePresenter} attached to the view.
 */
public class FragmentHandler<T extends BasePresenter> {

    /**
     * The presenter [T] that will be attached to the view.
     * It'll be injected.
     */
    @NonNull
    private final T mPresenter;

    /**
     * Constructor that allows injection.
     *
     * @param presenter to be attached to the view.
     */
    @Inject
    FragmentHandler(@NonNull final T presenter) {
        mPresenter = presenter;
    }

    /**
     * Invoked on view created.
     * On this step the view will be attached.
     *
     * @param view the view created.
     */
    void onCreate(@NonNull final Object view) {
        mPresenter.onCreate(view);
    }

    /**
     * Invoked on view destroy.
     * On this step the view will be detached.
     */
    void onDestroy() {
        mPresenter.onDestroy();
    }

    /**
     * @return the {@link T} presenter instance.
     */
    @NonNull
    T getPresenter() {
        return mPresenter;
    }
}
