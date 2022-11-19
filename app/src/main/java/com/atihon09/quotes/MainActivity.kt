package com.atihon09.quotes

//import android.os.CountDownTimer
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.atihon09.quotes.adapters.CategoryAdapter
import com.atihon09.quotes.adapters.ContentManager
import com.atihon09.quotes.adapters.MainConst
import com.atihon09.quotes.databinding.ActivityMainBinding
import com.google.android.gms.ads.*
import kotlin.random.Random

class MainActivity : AppCompatActivity(), CategoryAdapter.Listener, Animation.AnimationListener {
    private lateinit var binding: ActivityMainBinding
    private var adapter: CategoryAdapter? = null
    private var posM: Int = 0
    private lateinit var inAnimation: Animation
    private lateinit var outAnimation: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        inAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha_in)
        outAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha_out)
        outAnimation.setAnimationListener(this)
        initAdMob()
        (application as AppMainState).showAdIfAvailable(this) {}
        initRcView()
        binding.imageBg.setOnClickListener {
            binding.apply {
                tvMessage.startAnimation(outAnimation)
                tvName.startAnimation(outAnimation)
            }
           // getResult()
        }
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

    override fun onResume() {
        super.onResume()
        binding.adView.resume()

    }

    override fun onPause() {
        super.onPause()
        binding.adView.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.adView.destroy()
    }

    private fun initAdMob() {
        MobileAds.initialize(this)
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
    }

    private fun getMessage() = with(binding) {
        tvMessage.startAnimation(inAnimation)
        tvName.startAnimation(inAnimation)
        val currentArray = resources.getStringArray(MainConst.arrayList[posM])
        val message = currentArray[Random.nextInt(currentArray.size)]
        val messageList = message.split("|")
        tvMessage.text = messageList[0]
        tvName.text = messageList[1]
        imageBg.setImageResource(MainConst.imageList[Random.nextInt(4)])
    }

    override fun onClick(pos: Int) {
        binding.apply {
            tvMessage.startAnimation(outAnimation)
            tvName.startAnimation(outAnimation)
        }
        posM = pos
        // getResult()
    }

    override fun onAnimationStart(animation: Animation?) {

    }

    override fun onAnimationEnd(animation: Animation?) {
        getMessage()
    }

    override fun onAnimationRepeat(animation: Animation?) {

    }
}