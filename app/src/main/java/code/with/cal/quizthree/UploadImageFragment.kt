package code.with.cal.quizthree

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import code.with.cal.quizthree.databinding.FragmentUploadImageBinding
import java.io.ByteArrayOutputStream

class UploadImageFragment : Fragment(R.layout.fragment_upload_image) {

    lateinit var uploadBinding: FragmentUploadImageBinding
    lateinit var navController: NavController
    private lateinit var picture: String

    private val args: UploadImageFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val profileLoginNavHostFragment =
            activity!!.supportFragmentManager.findFragmentById(R.id.fragmentContainerViewMain) as NavHostFragment

        uploadBinding = FragmentUploadImageBinding.bind(view)

        navController = profileLoginNavHostFragment.navController

        val takePicture =
            registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { result ->
                uploadBinding.imageviewUser.setImageBitmap(result)

                val stream = ByteArrayOutputStream()
                result!!.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                val imageByteArray = stream.toByteArray()
                picture = Base64.encodeToString(imageByteArray, Base64.DEFAULT)
            }

        val findPicture =
            registerForActivityResult(ActivityResultContracts.GetContent()) { result ->
                uploadBinding.imageviewUser.setImageURI(result)

                val path = uploadBinding.imageviewUser.drawable.toBitmap()

                val stream = ByteArrayOutputStream()
                path.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                val imageByteArray = stream.toByteArray()
                picture = Base64.encodeToString(imageByteArray, Base64.DEFAULT)
            }

        uploadBinding.buttonOpenCamera.setOnClickListener {
            takePicture.launch()
        }

        uploadBinding.buttonOpenFile.setOnClickListener {
            findPicture.launch("image/*")
        }

        val token = args.token

        uploadBinding.buttonSendImage.setOnClickListener {
            val decodedByte = Base64.decode(picture, 0)
            val image = BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.size)
            NetworkManager.userService.imageUrl(token, image)
        }

    }
}