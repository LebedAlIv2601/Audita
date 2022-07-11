package com.vas.feature_search_screen.presentation

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.vas.feature_search_screen.R
import com.vas.feature_search_screen.databinding.FragmentSearchBinding
import com.vas.feature_search_screen.domain.model.UserModel

class SearchFragment : Fragment() {

    private var binding: FragmentSearchBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSearchBinding.inflate(inflater, container, false)

        val adapterListUser = ListUserAdapter()

        binding?.userRecyclerView?.adapter = adapterListUser

        adapterListUser.data = listOf(
            UserModel(1, R.drawable.man1, R.drawable.ic_drum, "#efa297", "musician"),
            UserModel(2, R.drawable.doooora, R.drawable.ic_electric_guitar, "#efa297", "musician"),
            UserModel(3, R.drawable.doooora, R.drawable.ic_guitar, "#efcc97", "musician"),
            UserModel(4, R.drawable.doooora, R.drawable.ic_saxophone, "#acd1c0", "musician"),
            UserModel(5, R.drawable.doooora, R.drawable.ic_piano, "#acd1c0", "musician"),
        )

        binding?.musiciansTextView?.setOnClickListener {
            binding?.apply {
                musiciansTextView.background = ContextCompat
                    .getDrawable(requireContext(), R.drawable.text_bg_click)
                bandTextView.background = ContextCompat
                    .getDrawable(requireContext(), R.drawable.text_bg_not_click)
                studioTextView.background = ContextCompat
                    .getDrawable(requireContext(), R.drawable.text_bg_not_click)
                musiciansTextView.setTextColor(Color.WHITE)
                bandTextView.setTextColor(Color.BLACK)
                studioTextView.setTextColor(Color.BLACK)
            }
            adapterListUser.data = listOf(
                UserModel(1, R.drawable.man1, R.drawable.ic_drum, "#efa297", "musician"),
                UserModel(2, R.drawable.doooora, R.drawable.ic_electric_guitar, "#efa297", "musician"),
                UserModel(3, R.drawable.doooora, R.drawable.ic_guitar, "#efcc97", "musician"),
                UserModel(4, R.drawable.doooora, R.drawable.ic_saxophone, "#acd1c0", "musician"),
                UserModel(5, R.drawable.doooora, R.drawable.ic_piano, "#acd1c0", "musician"),
                UserModel(6, R.drawable.doooora, R.drawable.ic_microphone, "#acd1c0", "musician"),
                UserModel(7, R.drawable.doooora, R.drawable.ic_microphone, "#acd1c0", "musician"),
                UserModel(8, R.drawable.doooora, R.drawable.ic_microphone, "#acd1c0", "musician"),
            )
        }

        binding?.bandTextView?.setOnClickListener {
            binding?.apply {
                bandTextView.background = ContextCompat
                    .getDrawable(requireContext(), R.drawable.text_bg_click)
                musiciansTextView.background = ContextCompat
                    .getDrawable(requireContext(), R.drawable.text_bg_not_click)
                studioTextView.background = ContextCompat
                    .getDrawable(requireContext(), R.drawable.text_bg_not_click)
                bandTextView.setTextColor(Color.WHITE)
                musiciansTextView.setTextColor(Color.BLACK)
                studioTextView.setTextColor(Color.BLACK)
            }
            adapterListUser.data = listOf(
                UserModel(9, R.drawable.doooora, R.drawable.ic_microphone, "#efa297", "band"),
                UserModel(10, R.drawable.doooora, R.drawable.ic_microphone, "#efa297", "band"),
                UserModel(11, R.drawable.doooora, R.drawable.ic_microphone, "#efa297", "band"),
                UserModel(12, R.drawable.doooora, R.drawable.ic_microphone, "#acd1c0", "band"),
            )
        }

        binding?.studioTextView?.setOnClickListener {
            binding?.apply {
                studioTextView.background = ContextCompat
                    .getDrawable(requireContext(), R.drawable.text_bg_click)
                musiciansTextView.background = ContextCompat
                    .getDrawable(requireContext(), R.drawable.text_bg_not_click)
                bandTextView.background = ContextCompat
                    .getDrawable(requireContext(), R.drawable.text_bg_not_click)
                studioTextView.setTextColor(Color.WHITE)
                musiciansTextView.setTextColor(Color.BLACK)
                bandTextView.setTextColor(Color.BLACK)
            }
            adapterListUser.data = listOf(
                UserModel(13, R.drawable.doooora, null, "#efa297", "studio"),
                UserModel(14, R.drawable.doooora, null, "#efa297", "studio"),
                UserModel(15, R.drawable.doooora, null, "#acd1c0", "studio"),
                UserModel(16, R.drawable.doooora, null, "#acd1c0", "studio"),
            )
        }

        adapterListUser.onClickListener = object : ListUserAdapter.OnListUserClickListener{
            override fun onClick(id: Int) {
                Toast.makeText(context, "Click $id", Toast.LENGTH_SHORT).show()
            }
        }

        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}