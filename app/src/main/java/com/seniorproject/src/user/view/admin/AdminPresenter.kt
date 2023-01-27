package com.seniorproject.src.user.view.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seniorproject.R
import com.seniorproject.appModules.network.errors.ErrorStates
import com.seniorproject.appModules.network.errors.LecturerNotFound
import com.seniorproject.appModules.network.errors.StudentNotFound
import com.seniorproject.appModules.network.response.SuccessResponse
import com.seniorproject.appModules.presenter.Presenter
import com.seniorproject.databinding.BridgeUserViewAdminBinding
import com.seniorproject.src.user.UserBridge
import com.seniorproject.src.user.model.data.request.AdminModel
import com.seniorproject.src.user.model.data.request.LoginModel
import com.seniorproject.src.user.model.data.response.AdminResponse
import com.seniorproject.src.user.model.data.response.LoginResponse

class AdminPresenter: Presenter< AdminViewModel, BridgeUserViewAdminBinding >() {
    private val viewModel: AdminViewModel by injectActivityVIewModels()
    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as UserBridge).component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = binding<BridgeUserViewAdminBinding>(
            inflater = inflater,
            resId = R.layout.bridge_user_view_admin,
            container = container).apply {
            lifecycleOwner = this@AdminPresenter
        }
        return viewBinding.root
    }

    override fun observeOn() {

        viewBinding.buttonGiris.setOnClickListener{
            compositeDisposable.add(
                viewModel.onAdmin(
                    AdminModel(
                        viewBinding.editTextAdminNo.text.toString(),
                        viewBinding.editTextSifre.text.toString()
                    )
                )
                    .subscribe {  }
            )
        }



        viewModel.queryStatus.observe( viewLifecycleOwner ){repositoryStatus: ErrorStates ->
            when( repositoryStatus ){
                is SuccessResponse<*> -> {
                    viewModel.setLecturerSession(
                        ( repositoryStatus.data as AdminResponse).token
                    )
                    Log.d("SUCCES_LOGIN","login oldu")
                }
                is LecturerNotFound -> {
                    Log.d("Credentials Error","login failed")
                }
            }
        }
    }

    override fun initUI() {

    }

}