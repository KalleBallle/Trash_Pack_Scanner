package com.example.packscan.UI


import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.R
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColorInt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.Factory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.packscan.CameraPreview
import com.example.packscan.Data.DatabaseProvider
import com.example.packscan.SingleButtonImagePicker
import com.example.packscan.UI.Navi.NavigationUI
import com.google.android.gms.internal.consent_sdk.zzbh
import java.util.jar.Manifest
import kotlin.contracts.contract


@Composable
fun startScreen(navController: NavController) {

    val context = LocalContext.current
    val db = remember { DatabaseProvider.getDatabase(context) }
    var popUpp by remember { mutableStateOf(false) }

    var buttonEnable by remember { mutableStateOf(true) }

    val figureViewModel: FigureViewModel = viewModel(
        factory = object : Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return FigureViewModel(db.figureDao()) as T
            }
        }
    )
    Box(Modifier.fillMaxSize()) {
        var canUseCamera by remember { mutableStateOf(false) }

        val controller = remember { LifecycleCameraController(context).apply { setEnabledUseCases(
            CameraController.IMAGE_CAPTURE or CameraController.IMAGE_ANALYSIS
        ) } }
        val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arrayOf(
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.READ_MEDIA_IMAGES
            )
        } else {
            arrayOf(
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
        }


        val permissionLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions()
        ) { permissionsResult ->
            if (permissionsResult.values.all { it }) {
                canUseCamera = true
            } else {
                Toast.makeText(context, "Permissions are required to proceed.", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        LaunchedEffect(Unit) {
            permissionLauncher.launch(permissions)
        }

        Box(Modifier.fillMaxSize().padding(10.dp))
        {
            CameraPreview(controller
                , Modifier.fillMaxSize())
            Box(Modifier.fillMaxWidth().padding(12.dp, 18.dp)) {

                Button(
                    onClick = {
                        controller.cameraSelector =
                            if (controller.cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA) {
                                CameraSelector.DEFAULT_FRONT_CAMERA
                            }
                            else
                            {
                                CameraSelector.DEFAULT_BACK_CAMERA
                            }
                    },
                    modifier = Modifier.align(Alignment.CenterStart).size(56.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(Color("#2D6A4F".toColorInt())),
                    contentPadding = PaddingValues(4.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = com.example.packscan.R.drawable.outline_flip_camera_ios_24),
                        contentDescription = null,
                        tint = Color.White, modifier = Modifier.fillMaxSize()
                    )
                }
            }
            Row(Modifier.fillMaxWidth().align(Alignment.BottomCenter).padding(16.dp)
            , horizontalArrangement = Arrangement.SpaceAround)  {

            }


        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {


            Row(Modifier.fillMaxWidth().padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween)
            {
                Box(Modifier.padding(12.dp, 4.dp)) {

                    Button(
                        onClick = {

                        },
                        modifier = Modifier.align(Alignment.CenterEnd).size(56.dp),
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(Color("#2D6A4F".toColorInt())),
                        contentPadding = PaddingValues(8.dp)
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = com.example.packscan.R.drawable.outline_document_search_24),
                            contentDescription = "search info",
                            tint = Color.White, modifier = Modifier.fillMaxSize()
                        )
                    }
                }
                Box(Modifier.padding(12.dp, 4.dp)) {

                    Button(
                        onClick = {

                        },
                        modifier = Modifier.align(Alignment.CenterEnd).size(56.dp),
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(Color("#2D6A4F".toColorInt())),
                        contentPadding = PaddingValues(4.dp)
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = com.example.packscan.R.drawable.outline_add_a_photo_24),
                            contentDescription = "Take photo",
                            tint = Color.White, modifier = Modifier.fillMaxSize()
                        )
                    }
                }
                Box(Modifier.padding(12.dp, 4.dp)) {

                    Button(
                        onClick = {
                            figureViewModel.clearState()
                            popUpp = true
                        },
                        modifier = Modifier.align(Alignment.CenterEnd).size(56.dp),
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(Color("#2D6A4F".toColorInt())),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = com.example.packscan.R.drawable.baseline_add_24),
                            contentDescription = null,
                            tint = Color.White, modifier = Modifier.fillMaxSize()
                        )
                    }
                }


            }
            NavigationUI(navController)
        }
    }

    //add pop upp

    if (popUpp) {
        androidx.compose.ui.window.Dialog(onDismissRequest = { popUpp = false }) {
            Card(modifier = Modifier.padding(16.dp)) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val prefixes = listOf("TP-", "SV-", "GG-")
                    var expanded by remember { mutableStateOf(false) }
                    var selectedPrefix by remember { mutableStateOf("TP-") }
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Figure ID: ")


                        Box {
                            Button(
                                onClick = { expanded = true },
                                contentPadding = PaddingValues(horizontal = 8.dp)
                            ) {
                                Text(selectedPrefix)
                            }
                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false }
                            ) {
                                prefixes.forEach { prefix ->
                                    DropdownMenuItem(
                                        text = { Text(prefix) },
                                        onClick = {
                                            selectedPrefix = prefix
                                            expanded = false
                                        }
                                    )
                                }
                            }
                        }

                        TextField(
                            value = figureViewModel.inputID,
                            onValueChange = { figureViewModel.onIdChange(it)
},
                            singleLine = true,
                            prefix = { Text(selectedPrefix) },

                            modifier = Modifier.weight(1f).padding(start = 4.dp, end = 8.dp)
                        )


                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { figureViewModel.checkFigureWithPrefix(selectedPrefix)
                        buttonEnable = true}) {
                        Text("Check")
                    }

                        Spacer(modifier = Modifier.height(16.dp))



                        when (figureViewModel.figureExists) {

                            true -> {


                                Text(
                                    text = "Figure ${figureViewModel.currFigure?.sculpt  ?: "Unknown"} (${figureViewModel.currFigure?.variant ?: "Unknown"}) found",
                                    color = Color("#2D6A4F".toColorInt()),
                                    modifier = Modifier.padding(top = 12.dp)
                                )




                                figureViewModel.currFigure?.let { nonNullFigure ->
                                    if (nonNullFigure.collected)
                                    {
                                        Text("you have ${nonNullFigure.quantity} copies of ${nonNullFigure.sculpt}(${nonNullFigure.variant}). Add another ?")
                                        Button( onClick = {
                                            figureViewModel.updateCollect(nonNullFigure.id)
                                            buttonEnable = false

                                        }
                                            , enabled = buttonEnable

                                        ) {
                                            Text("Add to collection")
                                        }
                                        Text("Or replace current image and add to collection")
                                    }
                                    else

                                    {
                                        Text("${nonNullFigure.sculpt}(${nonNullFigure.variant}) is new!! ")
                                        Text("Take photo to add to collection")
                            }
                                     SingleButtonImagePicker(figureViewModel, {
                                        popUpp = false
                                    },buttonEnable)


                                }





                            }

                            false -> Text(
                                text = " ID does not exist.",
                                color = Color.Red,
                                modifier = Modifier.padding(top = 12.dp)
                            )

                            null -> {}
                        }
                    }
                }
            }
        }
    }

private fun takePhotoCameraX(controller: LifecycleCameraController,
onPhotoTaken: (Bitmap) -> Unit,context: Context)
{
controller.takePicture(ContextCompat.getMainExecutor(context)
,object : ImageCapture.OnImageCapturedCallback() {
        override fun onCaptureSuccess(image: ImageProxy) {
            super.onCaptureSuccess(image)
            onPhotoTaken(image.toBitmap() ) // de är dem här du anylserar sendan fmen tesor flow
        }

        override fun onError(exception: ImageCaptureException) {
            super.onError(exception)
            Log.e("Camera","Could not take photo")

        }
    }
    )
}
