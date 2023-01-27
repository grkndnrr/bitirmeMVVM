package com.seniorproject.src.user.view.register

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.seniorproject.R
import com.seniorproject.appModules.network.errors.ErrorStates
import com.seniorproject.appModules.network.errors.StudentExists
import com.seniorproject.appModules.network.response.SuccessResponse
import com.seniorproject.appModules.presenter.Presenter
import com.seniorproject.databinding.BridgeUserViewRegisterBinding
import com.seniorproject.src.user.UserBridge
import com.seniorproject.src.user.model.data.request.RegisterModel
import com.seniorproject.src.user.model.data.response.RegisterResponse

class RegisterPresenter: Presenter<RegisterViewModel, BridgeUserViewRegisterBinding>() {
    private val viewModel: RegisterViewModel by injectActivityVIewModels()
    private var selection: Int = 0;
    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as UserBridge).component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = binding<BridgeUserViewRegisterBinding>(
            inflater = inflater,
            resId = R.layout.bridge_user_view_register,
            container = container).apply {
                lifecycleOwner = this@RegisterPresenter
        }
        return viewBinding.root
    }

    override fun observeOn(){

        viewBinding.radioGroup.setOnCheckedChangeListener { radioGroup, i ->

            when( i ){
                R.id.firstSch -> selection = 1
                R.id.secondSch -> selection = 2
            }
        }

        viewBinding.buttonKayit.setOnClickListener{
            if ( selection == 0 ){
                Toast.makeText(context, "Örgün Seçiniz", Toast.LENGTH_SHORT).show()
            }
            else {
                compositeDisposable.add(
                    viewModel.onRegister(
                        RegisterModel(
                            viewBinding.editTextOgrenciNo.text.toString(),
                            viewBinding.editTextSifre.text.toString(),
                            selection
                        )
                    )
                        .subscribe {  }
                )
            }

        }
        viewModel.queryStatus.observe( viewLifecycleOwner ){repositoryStatus: ErrorStates ->
            when( repositoryStatus ){
                is SuccessResponse<*> -> {
                    viewModel.setStudentSession(
                        ( repositoryStatus.data as RegisterResponse).token
                    )

                    Log.d("SUCCES_REGISTER","Kayit basarili")
                }
                is StudentExists -> {
                    Toast.makeText(context, "Öğrenci kayıtlı", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

}