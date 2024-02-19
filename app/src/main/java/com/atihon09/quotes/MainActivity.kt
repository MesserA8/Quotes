package com.atihon09.quotes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.atihon09.quotes.adapters.CategoryAdapter
import com.atihon09.quotes.adapters.ContentManager
import com.atihon09.quotes.databinding.ActivityMainBinding
import com.atihon09.quotes.fragments.QuotesFragment
import com.yandex.mobile.ads.banner.BannerAdSize
import com.yandex.mobile.ads.common.AdRequest
import com.yandex.mobile.ads.common.MobileAds
import com.yandex.mobile.ads.instream.MobileInstreamAds

class MainActivity : AppCompatActivity(), CategoryAdapter.Listener {

    private lateinit var binding: ActivityMainBinding
    private var adapter: CategoryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //yandex ads
        MobileAds.initialize(this) {}
        // Включаем предзагрузку рекламы до ее показа
        MobileInstreamAds.setAdGroupPreloading(true)
        initRcView()

        binding.rcViewCat.set3DItem(true)
        binding.rcViewCat.setAlpha(true)
        binding.rcViewCat.setInfinite(true)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, QuotesFragment(), "quotesFragmentTag")
            .commit()

        binding.banner.setAdUnitId("R-M-2357258-1") //"demo-banner-yandex"/R-M-2357258-1
        binding.banner.setAdSize(BannerAdSize.stickySize(this, 350))
        val adRequest = AdRequest.Builder().build()
        binding.banner.loadAd(adRequest)
    }

    private fun initRcView() = with(binding) {
        adapter = CategoryAdapter(this@MainActivity)
        rcViewCat.layoutManager = LinearLayoutManager(
            this@MainActivity,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        rcViewCat.adapter = adapter
        adapter?.submitList(ContentManager.list)
    }

    override fun onClick(pos: Int) {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (fragment is QuotesFragment) {
            fragment.onClick(pos)
        }
    }
}