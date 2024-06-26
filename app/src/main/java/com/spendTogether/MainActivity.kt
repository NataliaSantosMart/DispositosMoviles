package com.spendTogether

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.spendTogether.adapters.GroupAdapter
import com.spendTogether.models.GroupResponse.GroupResponseItem
import com.spendTogether.service.RetrofitServiceFactory
import kotlinx.coroutines.launch
class MainActivity : AppCompatActivity() {

    private var splashScreenStays: Boolean = true
    private val DELAY: Long = 1500L
    private val groupsInit = mutableListOf<GroupResponseItem>()

    override fun onCreate(savedInstanceState: Bundle?) {

            //Lanzamos el Splash
            val screenSplash = installSplashScreen()
            screenSplash.setKeepOnScreenCondition{splashScreenStays}
            Handler(Looper.getMainLooper()).postDelayed({splashScreenStays = false}, DELAY)

            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContentView(R.layout.activity_main)

        //Evento de navegación a pantalla CreateGroupActivity
            val plus_button: FloatingActionButton = findViewById(R.id.plus_button)

            plus_button.setOnClickListener {
                val intent = Intent(this, CreateGroupActivity::class.java)
                startActivity(intent)
            }

        //Capturamos el RecyclerView
            val rvGroups: RecyclerView = findViewById(R.id.rvGroups)

        //Montamos el Recycler de Groups
            val groupsAdapter = GroupAdapter(groupsInit, {adapterOnClick()});
            rvGroups.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            rvGroups.adapter = groupsAdapter

        //Crear la petición
            val apiGroupsService = RetrofitServiceFactory.getApiService();
            lifecycleScope.launch {
                //Hacemos petición
                val data = apiGroupsService.getGroups(("groups"))
                groupsInit.clear()
                groupsInit.addAll(data)
                //Repintar el RecyclerView
                groupsAdapter.notifyDataSetChanged()
            }

        //Evento de clickar en un rv
        rvGroups.setOnClickListener {
            val intent = Intent(this, CreateGroupActivity::class.java)
            startActivity(intent)
        }
    }

    private fun adapterOnClick() {
        val intent = Intent(this, UsersChargesActivity()::class.java)
        startActivity(intent)
    }
    }
