package com.snowcap.pokedex.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jakewharton.rxbinding4.view.clicks
import com.jakewharton.rxbinding4.widget.textChanges
import com.snowcap.pokedex.R
import com.snowcap.pokedex.models.Trainer
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_login.*
import java.util.concurrent.TimeUnit


class LoginFragment : Fragment() {
    private val disposables = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        disposables.add(
            loginButton.clicks()
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe {
                    val action = LoginFragmentDirections.actionLoginFragmentToPkmListFragment(
                        Trainer(
                            trainerTextEditInput.text.toString(),
                            "Male"
                        )
                    )
                    findNavController().navigate(action)
                }
        )

        disposables.add(
            trainerTextEditInput.textChanges()
                .skipInitialValue()
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    val areValidFields: Boolean = !TextUtils.isEmpty(trainerTextEditInput.text)
                    loginButton.isEnabled = areValidFields
                    trainerTextInputLayout.error =
                        if (!areValidFields) "Campo Requerido" else null

                })

    }
}