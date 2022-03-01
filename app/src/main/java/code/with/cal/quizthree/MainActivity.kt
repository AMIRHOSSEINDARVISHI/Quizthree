package code.with.cal.quizthree

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import code.with.cal.quizthree.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var bindingMain: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindingMain = ActivityMainBinding.inflate(layoutInflater)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerViewMain) as NavHostFragment

        navController = navHostFragment.navController

        bindingMain.bottomNavigationViewMain.setOnItemSelectedListener { result ->
            when (result.itemId) {
                R.id.profile_menu -> {
                    navController.popBackStack()
                    navController.navigate(R.id.profileFragment)
                }
                R.id.search_menu -> {
                    navController.popBackStack()
                    navController.navigate(R.id.searchFragment)
                }
            }
            true
        }


    }
}