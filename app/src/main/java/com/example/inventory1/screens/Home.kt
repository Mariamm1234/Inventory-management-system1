package com.example.inventory1.screens

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.inventory1.MenuFragments.Edit_productFragment
import com.example.inventory1.MenuFragments.Excel_exportFragment
import com.example.inventory1.MenuFragments.Excel_importFragment
import com.example.inventory1.MenuFragments.Remove_productFragment
import com.example.inventory1.MenuFragments.Search_productFragment
import com.example.inventory1.MenuFragments.Show_productsFragment
import com.example.inventory1.MenuFragments.UnitsFragment
import com.example.inventory1.MenuFragments.recently_added_productsFragment
import com.example.inventory1.R
import com.example.inventory1.screens.ui.main.SectionsPagerAdapter
import com.example.inventory1.databinding.ActivityHomeBinding
import com.example.inventory1.screens.ui.main.AllProductsFragment
import com.example.inventory1.screens.ui.main.CompletedProductsFragment
import com.google.android.material.navigation.NavigationView
import io.github.g00fy2.quickie.QRResult
import io.github.g00fy2.quickie.ScanCustomCode
import io.github.g00fy2.quickie.ScanQRCode
import io.github.g00fy2.quickie.config.BarcodeFormat
import io.github.g00fy2.quickie.config.ScannerConfig
import io.github.g00fy2.quickie.content.QRContent

class Home : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityHomeBinding

    private var selectedBarcodeFormat = BarcodeFormat.FORMAT_ALL_FORMATS

    private val scanQrCode = registerForActivityResult(ScanQRCode(), ::showSnackbar)
    private val scanCustomCode = registerForActivityResult(ScanCustomCode(), ::showSnackbar)
    private lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
//        binding.navLayout.visibility=View.INVISIBLE



        drawerLayout = findViewById(R.id.drawer_layout)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.view_pager, AllProductsFragment()).commit()
            navigationView.setCheckedItem(R.id.nav_home)
        }







        val fab: FloatingActionButton = binding.fab
        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
            scanCustomCode.launch(
                ScannerConfig.build {
                    setBarcodeFormats(listOf(selectedBarcodeFormat)) // set interested barcode formats
                    setOverlayStringRes(R.string.scan_barcode) // string resource used for the scanner overlay
                    setOverlayDrawableRes(R.drawable.ic_scan_barcode) // drawable resource used for the scanner overlay
                    setHapticSuccessFeedback(false) // enable (default) or disable haptic feedback when a barcode was detected
                    setShowTorchToggle(true) // show or hide (default) torch/flashlight toggle button
                    setShowCloseButton(true) // show or hide (default) close button
                    setHorizontalFrameRatio(2.2f) // set the horizontal overlay ratio (default is 1 / square frame)
                    setUseFrontCamera(false) // use the front camera
                }
            )
        }

        if (intent.extras?.getBoolean(OPEN_SCANNER) == true) scanQrCode.launch(null)
    }
    private fun showSnackbar(result: QRResult) {
        val text = when (result) {
            is QRResult.QRSuccess -> {
                result.content.rawValue
                // decoding with default UTF-8 charset when rawValue is null will not result in meaningful output, demo purpose
                    ?: result.content.rawBytes?.let { String(it)
                    }.orEmpty()
            }
            QRResult.QRUserCanceled -> "User canceled"
            QRResult.QRMissingPermission -> "Missing permission"
            is QRResult.QRError -> "${result.exception.javaClass.simpleName}: ${result.exception.localizedMessage}"
        }
print(text+"555555555\n")
        Snackbar.make(binding.root, text, Snackbar.LENGTH_INDEFINITE).apply {
            view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)?.run {
                maxLines = 5
                setTextIsSelectable(true)
            }
            if (result is QRResult.QRSuccess) {
                val content = result.content
                if (content is QRContent.Url) {
                    setAction(R.string.open_action) { openUrl(content.url) }
                    return@apply
                }
            }
            setAction(R.string.ok_action) { }
        }.show()
    }
    private fun openUrl(url: String) {
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        } catch (ignored: ActivityNotFoundException) {
            // no Activity found to run the given Intent
        }
    }
    companion object {
        const val OPEN_SCANNER = "open_scanner"
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        binding.tabs.visibility=View.INVISIBLE
        binding.navLayout.visibility=View.VISIBLE
        binding.viewPager.visibility=View.GONE
        when (item.itemId) {
            R.id.nav_home -> {
                binding.navLayout.visibility=View.GONE
                binding.viewPager.visibility=View.VISIBLE
                binding.tabs.visibility=View.VISIBLE
                supportFragmentManager.beginTransaction()
                .replace(R.id.nav_layout, AllProductsFragment()).commit()

           }
            R.id.nav_addproduct -> supportFragmentManager.beginTransaction()
                .replace(R.id.nav_layout, recently_added_productsFragment()).commit()
            R.id.nav_removeproduct -> supportFragmentManager.beginTransaction()
                .replace(R.id.nav_layout, Remove_productFragment()).commit()
            R.id.nav_editproduct -> supportFragmentManager.beginTransaction()
                .replace(R.id.nav_layout, Edit_productFragment()).commit()
//            R.id.nav_searchproduct -> supportFragmentManager.beginTransaction()
//                .replace(R.id.nav_layout, Search_productFragment()).commit()
            R.id.nav_showproduct -> supportFragmentManager.beginTransaction()
                .replace(R.id.nav_layout, Show_productsFragment()).commit()
            R.id.excel_import -> supportFragmentManager.beginTransaction()
                .replace(R.id.nav_layout, Excel_importFragment()).commit()
            R.id.excel_export-> supportFragmentManager.beginTransaction()
                .replace(R.id.nav_layout, Excel_exportFragment()).commit()
            R.id.nav_units->supportFragmentManager.beginTransaction()
                .replace(R.id.nav_layout,UnitsFragment()).commit()
            R.id.nav_logout -> Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show()
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
    override fun onBackPressed() {
        super.onBackPressed()
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}