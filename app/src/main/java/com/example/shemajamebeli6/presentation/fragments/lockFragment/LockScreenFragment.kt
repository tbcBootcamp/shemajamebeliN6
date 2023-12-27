package com.example.shemajamebeli6.presentation.fragments.lockFragment

import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shemajamebeli6.adapter.DotsAdapter
import com.example.shemajamebeli6.adapter.NumbersAdapter
import com.example.shemajamebeli6.databinding.FragmentLockScreenBinding
import com.example.shemajamebeli6.model.DotModel
import com.example.shemajamebeli6.model.NumberModel
import com.example.shemajamebeli6.presentation.fragments.BaseFragment


class LockScreenFragment : BaseFragment<FragmentLockScreenBinding>(
    FragmentLockScreenBinding::inflate
) {

    private lateinit var dotsAdapter: DotsAdapter
    private lateinit var numbersAdapter: NumbersAdapter
    private var passcodeList = mutableListOf<Int>()
    private var dotList = mutableListOf<DotModel>()
    private var numberList = mutableListOf<Int>()

    override fun listeners() {
        setupAdapters()
        provideNumberList()
        passCodeToList()
        provideDotsLists(dotList)
    }

    private fun provideDotsLists(list: List<DotModel>) {
        dotList = MutableList(4) { DotModel(it, "gray") }
        dotsAdapter.submitList(list)
    }

    private fun resetLists() {
        numberList = mutableListOf()
        dotList = MutableList(4) { DotModel(it, "gray") }
    }

    private fun passCodeToList() {
        PASSCODE.forEach {
            passcodeList.add(it.toString().toInt())
        }
    }

    private fun onNumberAdapterClick(model: NumberModel) {
        check(model)
        when {
            numberList.size == 4 -> {
                if (numberList.toString() == passcodeList.toString()) {
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                    resetLists()
                } else {
                    resetLists()
                }
            }
            (numberList.size < 5) -> {
            }
        }
        provideDotsLists(dotList)
    }

    private fun check(model: NumberModel) {
        when (model.icon) {
            "delete" -> {
                if (numberList.size > 0) {
                    numberList.removeLast()
                    deleteDotColor()
                }
            }
            "touchId" -> {
                Toast.makeText(requireContext(), "wash your hands", Toast.LENGTH_SHORT).show()
            }
            else -> {
                 changeDotsColor(model)
            }
        }
    }

    private fun changeDotsColor(model: NumberModel) {
        model.icon.toIntOrNull()?.let {
            numberList.add(it)
            for (i in 0 until numberList.size) {
                dotList[i] = DotModel(i, "green")
            }
        }
    }


//    private fun deleteDotColor() {
//        if (numberList.size >= 0) {
//            dotList[numberList.size] = DotModel(numberList.size, "gray")
//            dotsAdapter.submitList(dotList)
//        }
//    }

    private fun deleteDotColor() {
        if (numberList.isNotEmpty()) {
            dotList[numberList.size - 1] = DotModel(numberList.size - 1, "gray")
            dotsAdapter.submitList(dotList)
        }

    }


    private fun setupAdapters() {
        with(binding) {
            dotsAdapter = DotsAdapter()
            rvDots.adapter = dotsAdapter
            rvDots.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            numbersAdapter = NumbersAdapter { onNumberAdapterClick(it) }
            rvNumbers.adapter = numbersAdapter
            rvNumbers.layoutManager = GridLayoutManager(requireContext(), 3)
        }
    }

    private fun provideNumberList() {
        val listOfNumbers = listOf(
            NumberModel(1, "1"), NumberModel(2, "2"), NumberModel(3, "3"),
            NumberModel(4, "4"), NumberModel(5, "5"), NumberModel(6, "6"),
            NumberModel(7, "7"), NumberModel(8, "8"), NumberModel(9, "9"),
            NumberModel(10, "touchId"), NumberModel(11, "0"), NumberModel(12, "delete")
        )
        numbersAdapter.submitList(listOfNumbers)
    }

    companion object {
        const val PASSCODE = "0934"
    }
}