package com.example.lab3

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
//Shared preferences - это облегченный класс хранилища
class SharedPreferenceManager(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    fun saveCounter(counter: Int) { //сохранение счетчика
        val editor = sharedPreferences.edit()
        editor.putInt("counter", counter)
        editor.apply()
    }
    fun getCounter(): Int { //получение значения счетчика
        return sharedPreferences.getInt("counter", 0)
    }
}
//фрагмент
class MainFragment : Fragment() {
    companion object { fun newInstance() = MainFragment() }

    private lateinit var counterTextView: TextView
    private lateinit var amountEditText: EditText
    private lateinit var viewModel: MainViewModel
    private lateinit var sharedPreferenceManager: SharedPreferenceManager

    //создание вьюшки
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        counterTextView = view.findViewById(R.id.counterTextView)!!

        val increaseButton: Button = view.findViewById(R.id.increaseButton)!!
        val decreaseButton: Button = view.findViewById(R.id.decreaseButton)!!
        val nextButton: Button = view.findViewById(R.id.nextButton)!!

        increaseButton.setOnClickListener { //сложение
            viewModel.increaseCounter(1)
        }
        decreaseButton.setOnClickListener { //вычитание
            viewModel.decreaseCounter(1)
        }
        nextButton.setOnClickListener { goToSecondActivity() } //вторая активность

        sharedPreferenceManager = SharedPreferenceManager(requireContext())

        return view
    }

    //сохранение информации о состоянии активности
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("counter", viewModel.counter.value ?: 0)
    }

    //переход на вторую активность
    private fun goToSecondActivity() {
        val intent = Intent(activity, SecondActivity::class.java)
    // Сохранение значения счетчика в Shared Preferences
        val currentCounter = viewModel.counter.value ?: 0
        sharedPreferenceManager.saveCounter(currentCounter)

        intent.putExtra("counter_value", currentCounter)
        startActivity(intent)
    }

    //создание активности
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.counter.observe(viewLifecycleOwner) { count ->
            counterTextView.text = count.toString()
        }
        // Восстановление значения счетчика из Shared Preferences
        val savedCounter = sharedPreferenceManager.getCounter()
        viewModel.setCounter(savedCounter)

        if (savedInstanceState != null) {
            val savedCounter = savedInstanceState.getInt("counter", 0)
            viewModel.setCounter(savedCounter)
        }
    }
}
