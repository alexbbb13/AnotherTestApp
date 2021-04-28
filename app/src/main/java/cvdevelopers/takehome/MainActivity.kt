package cvdevelopers.takehome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cvdevelopers.githubstalker.R
import cvdevelopers.takehome.ui.UserListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as LuminaryTakeHomeApplication).appComponent.inject(this)
        supportFragmentManager.beginTransaction().replace(R.id.activity_fragment_container,UserListFragment()).commit()
    }
}
