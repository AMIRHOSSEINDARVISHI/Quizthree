package code.with.cal.quizthree

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import code.with.cal.quizthree.databinding.FragmentListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListFragment:Fragment(R.layout.fragment_list) {
    lateinit var listBinding: FragmentListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listBinding = FragmentListBinding.bind(view)

        val list = ListAdapter(this)

        listBinding.recyclerviewList.adapter = list

        NetworkManager.userService.loadUser().enqueue(
            object : Callback<List<ListUser>>{
                override fun onResponse(
                    call: Call<List<ListUser>>,
                    response: Response<List<ListUser>>
                ) {
                    list.setDataList(response.body()!!)
                }

                override fun onFailure(call: Call<List<ListUser>>, t: Throwable) {
                    Log.i("Throwable", t.toString())
                }

            }
        )

    }

}