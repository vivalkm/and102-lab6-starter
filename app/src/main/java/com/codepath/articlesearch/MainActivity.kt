package com.codepath.articlesearch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.codepath.articlesearch.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.serialization.json.Json

fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}

private const val TAG = "MainActivity/"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val navbar = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val articalFragment = ArticleListFragment()
        val bookFragment = BestSellerBooksFragment()

        navbar.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            // Call helper method to swap the FrameLayout with the fragment
            when (item.itemId) {
                R.id.action_articles -> replaceFragment(articalFragment)
                R.id.action_books -> replaceFragment(bookFragment)
            }
            true
        }

        // Set default selection
        navbar.selectedItemId = R.id.action_books

    }

    private fun replaceFragment(articleListFragment: ArticleListFragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.article_frame_layout, articleListFragment)
        fragmentTransaction.commit()
    }

    private fun replaceFragment(bookFragment: BestSellerBooksFragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.article_frame_layout, bookFragment)
        fragmentTransaction.commit()
    }
}