package com.stepasha.neewsfeedinjector.ui

import com.stepasha.neewsfeedinjector.BasePresenter
import com.stepasha.neewsfeedinjector.R
import com.stepasha.neewsfeedinjector.network.PostApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * The Presenter that will present the Post view.
 * @param postView the Post view to be presented by the presenter
 * @property postApi the API interface implementation
 * @property context the context in which the application is running
 * @property subscription the subscription to the API call
 */

class PostPresenter(postView: PostView) : BasePresenter<PostView>(postView) {
    @Inject
    lateinit var postApi: PostApi

    //API RxJava
    private var subscription: Disposable? = null



    override fun onViewCreated() {
        loadPosts()
    }

    /**
     * Loads the posts from the API and presents them in the view when retrieved, or showss error if
     * any.
     */
    fun loadPosts() {
        view.showLoading()
        subscription = postApi
            .getPosts()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnTerminate { view.hideLoading() }
            .subscribe(
                { postList -> view.updatePosts(postList) },
                { view.showError("This is my custom Error!") }
            )
    }

    override fun onViewDestroyed() {
        subscription?.dispose()
    }
}