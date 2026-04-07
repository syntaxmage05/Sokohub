package com.example.sokohub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ViewFlipper
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.hotwire.turbo.activities.TurboActivity
import dev.hotwire.turbo.delegates.TurboActivityDelegate

class MainActivity : AppCompatActivity(), TurboActivity {
    override lateinit var delegate: TurboActivityDelegate
    lateinit var tabBar: BottomNavigationView
    private lateinit var tabSwitcher: ViewFlipper
    private val tabsViewModel: TabsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configureTurboDelegates()
        configureTabs()
    }
    private fun configureTurboDelegates() {
        delegate =
            TurboActivityDelegate(this, tabsViewModel.tabs.first().id)
        tabsViewModel.tabs.forEach {
            delegate.registerNavHostFragment(it.id)
        }
    }
    private fun configureTabs() {
        tabSwitcher = findViewById(R.id.tabSwitcher)
        tabBar = findViewById(R.id.tabBar)
        tabBar.setOnItemSelectedListener {
            tabSwitcher.displayedChild =
                tabsViewModel.indexedTabForId(it.itemId)!!.index
            delegate.currentNavHostFragmentId = it.itemId
            delegate.refresh(false)
            return@setOnItemSelectedListener true
        }
    }
}