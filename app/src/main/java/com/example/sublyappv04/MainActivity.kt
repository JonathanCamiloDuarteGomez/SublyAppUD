package com.example.sublyappv04

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {lateinit var navegation: BottomNavigationView

    private  val mOnNavMenu = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when(item.itemId){
            R.id.itemFramentHome -> {
                supportFragmentManager.commit {
                    replace<HomeFragment>(R.id.frameContainer) //contenedor del main.xml
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.itemFramentCamisas -> {
                supportFragmentManager.commit {
                    replace<CamisasFragment>(R.id.frameContainer) //contenedor del main.xml
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.itemFramentForros -> {
                supportFragmentManager.commit {
                    replace<ForrosFragment>(R.id.frameContainer) //contenedor del main.xml
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
                return@OnNavigationItemSelectedListener true
            }
        }

        false }
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navegation = findViewById(R.id.navMenu)//el navMenu es el menu en la carpeta menu
        navegation.setOnNavigationItemSelectedListener(mOnNavMenu)//este es el que esta al comienzo de esta pag
        supportFragmentManager.commit {
            replace<HomeFragment>(R.id.frameContainer) //contenedor del main.xml
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
    }
}