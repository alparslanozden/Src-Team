package com.asimodabas.src_team

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_advert.*
import kotlinx.android.synthetic.main.fragment_create.*

class CreateFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)

        auth = Firebase.auth
        firestore = Firebase.firestore

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fcreateButton.setOnClickListener {

            if (
                nameEditText.text.toString() != null &&
                surnameEditText.text.toString() != null &&
                emailEditText.text.toString() != null &&
                passwordEditText.text.toString() != null
            ) {
                firebaseSaver()

                auth.createUserWithEmailAndPassword(
                    emailEditText.text.toString(),
                    passwordEditText.text.toString()
                ).addOnSuccessListener {

                    val action = CreateFragmentDirections.actionCreateFragmentToSecondFragment()
                    findNavController().navigate(action)

                }.addOnFailureListener {
                    Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    fun firebaseSaver() {

        val user = auth.currentUser
        user?.let {

            val name = nameEditText.text.toString()
            val surname = surnameEditText.text.toString()
            val email = it.email
            val gender = radioGroup.checkedRadioButtonId
            val date = FieldValue.serverTimestamp()
            //  val address = editTextTextPersonName2.text.toString()
            //  val clock = editTextTextPersonName4.text.toString()
            //  val Activtiydate = editTextTextPersonName3.text.toString()
            //  val SearchActivity = editTextTextPersonName5.text.toString()
            //  val Notes = editTextTextPersonName6.text.toString()

            val dataMap = HashMap<String, Any>()
            dataMap.put("name", name)
            dataMap.put("surname", surname)
            dataMap.put("email", email!!)
            dataMap.put("gender", gender)
            dataMap.put("date", date)
            // dataMap.put("adres",address)
            // dataMap.put("clock",clock)
            // dataMap.put("Activtiydate",Activtiydate)
            // dataMap.put("SearchActivity",SearchActivity)
            // dataMap.put("Notes",Notes)

            firestore.collection("Records").add(dataMap).addOnSuccessListener {

                nameEditText.setText("")
                surnameEditText.setText("")
                emailEditText.setText("")
                passwordEditText.setText("")
                // editTextTextPersonName2.setText("")
                // editTextTextPersonName4.setText("")
                // editTextTextPersonName6.setText("")
                // editTextTextPersonName3.setText("")
                // editTextTextPersonName5.setText("")
                radioGroup.clearCheck()

            }.addOnFailureListener {
                Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.account_item) {
            val action = CreateFragmentDirections.actionCreateFragmentToProfileFragment()
            findNavController().navigate(action)
        }
        return super.onOptionsItemSelected(item)
    }

}