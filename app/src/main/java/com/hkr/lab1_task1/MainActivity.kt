package com.hkr.lab1_task1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.hkr.lab1_task1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    /** Application logic **/

    private lateinit var binding: ActivityMainBinding

    // Variables

    private val messages = mutableListOf(
        "Hi! I'm Paul, Thanks for checking out my app",
        "I come from Australia but I have now lived in Sweden for 8 years",
        "Before studying computer science, I was a carpenter for over 10 years",
        "I have three beautiful daughters who are 15, 12, and 3 years old.",
        "I first started programming as a hobby by learning web development.",
        "I have three citizenship's, Australia, Great Britain and Sweden",
        "I can speak two languages, English and Swedish",
        "I would love to be able to share my knowledge and teach others programming in the future",
        "My favourite music is by Red hot chili peppers, John Butler and Incubus",
        "I will be doing my Thesis with Axis communications. The topic of the thesis will be 'Cross-platform strategies for mobile development'",
    )

    private val currentMessage: MutableLiveData<String> = MutableLiveData(messages[0])

    // Methods

    private fun setCurrentMessage() {
        val randomIndex = (messages.indices).random()
        currentMessage.value = messages[randomIndex]
    }

    /** Lifecycle Methods **/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(savedInstanceState != null){
            with(savedInstanceState) {
                currentMessage.value = getString(CURRENT_MESSAGE)
            }
        }

        // Generate the binding object from the activity_main.xml view hierarchy
        binding = ActivityMainBinding.inflate(layoutInflater)

        // Set the view to be the activity_main xml layout.
        setContentView(binding.root)

        Log.i("Lifecycle", "onCreate was called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("Lifecycle", "onRestart was called")
    }

    override fun onStart() {
        super.onStart()

        // Observer Lambda function that executes when currentMessage value changes.
        val messageObserver = Observer<String> { message ->
            binding.messageTextView.text = message
        }

        // Observe the currentMessage variable for changes
        currentMessage.observe(this, messageObserver)

        // Attach click listener to the button to update the currentMessage variable
        binding.newMessageButton.setOnClickListener {
            setCurrentMessage()
        }
        Log.i("Lifecycle", "onStart was called")
    }

    override fun onResume() {
        super.onResume()
        Log.i("Lifecycle", "onResume was called")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Lifecycle", "onPause was called")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Lifecycle", "onStop was called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Lifecycle", "onDestroy was called")
    }

    override fun onSaveInstanceState(outState: Bundle) {

        outState?.run {
            putString(CURRENT_MESSAGE, currentMessage.value)
        }

        super.onSaveInstanceState(outState)
    }

    companion object {
        val CURRENT_MESSAGE = "currentMessage"
    }
}