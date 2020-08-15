package com.example.dinnerdecider.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dinnerdecider.R
import com.example.dinnerdecider.model.CustomViewAdapter
import com.example.dinnerdecider.model.CustomViewModel
import com.example.dinnerdecider.util.DialogBox
import kotlinx.android.synthetic.main.fragment_custom_categories.view.*
import java.util.ArrayList


class CustomCategories : Fragment(){
    private lateinit var viewModel: CustomViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_custom_categories, container, false)
        val btnRandom: Button = v.btn_random
        val customView: RecyclerView = v.recycler_custom
        val customAmount: EditText = v.edtxt_number
        viewModel = ViewModelProvider(this).get(CustomViewModel::class.java)

        viewModel.setList(2)
        val customAdapter = CustomViewAdapter(viewModel.getCustom(), context)

        customView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = customAdapter
        }

        customAmount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                val text = customAmount.text.toString()
                if(text != "" && text != "0" && text != "1"){
                    viewModel.setList(Integer.parseInt(customAmount.text.toString()))
                    customAdapter.customList = viewModel.getCustom()
                }
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
        
        btnRandom.setOnClickListener {
            if(viewModel.isConnected()){
                if(viewModel.allFieldsFilled()){
                    val bundle = Bundle()
                    val list = viewModel.getCustom().map { it.title }
                    bundle.putStringArrayList(ChooseCategories.key, list as ArrayList<String>)

                    NavHostFragment.findNavController(this)
                        .navigate(R.id.action_customCategories_to_spinWheel, bundle)
                }
                else Toast.makeText(context, resources.getString(R.string.msg_custom_error),
                        Toast.LENGTH_SHORT).show()
            }
            else DialogBox().showDialogBox(resources.getString(R.string.title_internet),
                    resources.getString(R.string.detail_internet), context)
        }
        return v
    }
}