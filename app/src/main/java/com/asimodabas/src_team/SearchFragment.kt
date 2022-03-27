package com.asimodabas.src_team

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SearchFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
        auth = Firebase.auth
        db=Firebase.firestore

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.account_item){
            val action = SearchFragmentDirections.actionSearchFragmentToProfileFragment()
            findNavController().navigate(action)
        }
        return super.onOptionsItemSelected(item)
    }

    fun getData(){

        db.collection("Records").addSnapshotListener { value, error ->
            if (error!=null){

                Toast.makeText(context,error.localizedMessage,Toast.LENGTH_LONG).show()

            }else{

                if (value !=null){
                    if (!value.isEmpty){

                        val documents = value.documents
                        for (document in documents){
                            //casting
                            val name = document.get("name") as String
                            val surname = document.get("surname") as String

                        }
                    }
                }
            }
        }

    }

}