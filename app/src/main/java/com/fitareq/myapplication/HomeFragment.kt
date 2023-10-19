package com.fitareq.myapplication

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fitareq.myapplication.databinding.FragmentHomeBinding
import kotlin.random.Random

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var handler: Handler
    private var initialPrice = 0
    private var initialPercentage = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        handler = Handler(Looper.getMainLooper())
        handler.postDelayed(runnable, 5000)


        return binding.root
    }

    private val runnable = object : Runnable {
        override fun run() {
            val random = Random.nextInt(1, 500)
            initialPercentage = calculatePercentage(initialPrice, random)
            initialPrice = random
            if (activity is MainActivity) {
                (activity as MainActivity).apply {
                    price = initialPrice
                    percentage = initialPercentage
                }
            }
            binding.apply {
                price.text = getString(R.string.price, random.toString())
                gpPbPriceCard1.text = getString(R.string.gp_pb_price, random.toString())
                gpPbPriceCard2.text = getString(R.string.gp_pb_price, random.toString())
                percentCard1.text =
                    getString(R.string.price_change_percent, initialPercentage.toString())
                percentCard2.text =
                    getString(R.string.price_change_percent, initialPercentage.toString())
            }

            handler.postDelayed(this, 5000)
        }

    }

    private fun calculatePercentage(initialPrice: Int, random: Int): Double {
        val change = random - initialPrice
        val initial = if (initialPrice == 0) 1 else initialPrice
        return (change.toDouble() / initial.toDouble()) * 100
    }
}