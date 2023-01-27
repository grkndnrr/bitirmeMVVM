package com.seniorproject.src.user.view.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seniorproject.R
import com.seniorproject.appModules.network.errors.ErrorStates
import com.seniorproject.appModules.network.errors.StudentNotFound
import com.seniorproject.appModules.network.response.SuccessResponse
import com.seniorproject.appModules.presenter.Presenter
import com.seniorproject.databinding.BridgeUserViewLoginBinding
import com.seniorproject.src.user.Admin
import com.seniorproject.src.user.Login
import com.seniorproject.src.user.Register
import com.seniorproject.src.user.UserBridge
import com.seniorproject.src.user.model.data.request.LoginModel
import com.seniorproject.src.user.model.data.response.LoginResponse

class LoginPresenter: Presenter< LoginViewModel, BridgeUserViewLoginBinding >() {
    private val viewModel: LoginViewModel by injectActivityVIewModels()
    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as UserBridge).component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = binding<BridgeUserViewLoginBinding>(
            inflater = inflater,
            resId = R.layout.bridge_user_view_login,
            container = container).apply {
            lifecycleOwner = this@LoginPresenter
        }
        return viewBinding.root
    }

    override fun observeOn() {
        viewBinding.buttonGiris.setOnClickListener{


            compositeDisposable.add(
                viewModel.onLogin(
                    LoginModel(
                        viewBinding.editTextOgrenciNo.text.toString(),
                        viewBinding.editTextSifre.text.toString()
                    )
                )
                    .subscribe {  }
            )
        }
        viewModel.queryStatus.observe( viewLifecycleOwner ){repositoryStatus: ErrorStates ->
            when( repositoryStatus ){
                is SuccessResponse<*> -> {
                    viewModel.setStudentSession(
                        ( repositoryStatus.data as LoginResponse ).token
                    )
                    Log.d("SUCCES_LOGIN","login oldu")
                }
                is StudentNotFound -> {
                    Log.d("Credentials Error","login failed")
                }
            }
        }
        viewBinding.buttonAdmin.setOnClickListener {
            ( activity as UserBridge ).navigation( Admin() )
        }
        viewBinding.buttonRegister.setOnClickListener {
            ( activity as UserBridge ).navigation( Register() )
        }
    }

    override fun initUI() {

    }

}