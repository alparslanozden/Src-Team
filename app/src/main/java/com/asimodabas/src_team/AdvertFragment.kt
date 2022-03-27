package com.asimodabas.src_team

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_advert.*


class AdvertFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_advert, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        advSearchButton.setOnClickListener {

            val action = AdvertFragmentDirections.actionAdvertFragmentToSearchFragment()
            findNavController().navigate(action)
        }
        locationButton.setOnClickListener {
            val action = AdvertFragmentDirections.actionAdvertFragmentToMapsActivity()
            findNavController().navigate(action)
        }

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.account_item){
            val action = AdvertFragmentDirections.actionAdvertFragmentToProfileFragment()
            findNavController().navigate(action)
        }
        return super.onOptionsItemSelected(item)
    }

}