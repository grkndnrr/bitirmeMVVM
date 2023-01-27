package com.seniorproject.appModules.bridge

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.seniorproject.appModules.router.Router
import com.seniorproject.R
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


open class Bridge<Component: Any, NavigationStatus: Any> : AppCompatActivity(){
    @Inject
    lateinit var router: Router<NavigationStatus>
    @Inject
    lateinit var compositeDisposable: CompositeDisposable

    lateinit var component: Component

    fun <T: Fragment> startNavigation(factory: () -> T) {
        val entity: T = factory()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_holder, entity)
            .commit()
    }

    fun navigation(navigationStatus: NavigationStatus, containerViewId: Int = R.id.fragment_holder, bundle: Bundle? = null) = router.navigationLogic(activity = this, navigationStatus, containerViewId, bundle)
    override fun onBackPressed() =  router.navigationLogicOnBackPressed(this, supportFragmentManager.fragments.last(), R.id.fragment_holder)
    override fun onStop() =  compositeDisposable.clear().run{super.onStop()}
    override fun onDestroy() =  compositeDisposable.dispose().run { super.onDestroy() }

}