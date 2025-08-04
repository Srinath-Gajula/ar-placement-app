package com.example.drillar.ui.screens

import android.view.MotionEvent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.drillar.model.drills
import com.google.ar.core.Config
import com.google.ar.core.Frame
import com.google.ar.core.Plane
import com.google.ar.core.TrackingFailureReason
import dev.romainguy.kotlin.math.Float3
import io.github.sceneview.ar.ARScene
import io.github.sceneview.ar.arcore.createAnchorOrNull
import io.github.sceneview.ar.arcore.getUpdatedPlanes
import io.github.sceneview.ar.arcore.isValid
import io.github.sceneview.ar.rememberARCameraNode
import io.github.sceneview.node.CubeNode
import io.github.sceneview.node.Node
import io.github.sceneview.ar.node.AnchorNode
import io.github.sceneview.rememberCollisionSystem
import io.github.sceneview.rememberEngine
import io.github.sceneview.rememberMaterialLoader
import io.github.sceneview.rememberModelLoader
import io.github.sceneview.rememberNodes
import io.github.sceneview.rememberOnGestureListener
import io.github.sceneview.rememberView

@Composable
fun ARPlacementScreen(drillName: String) {
    val drill = drills.find { it.name == drillName } ?: drills.first()

    val engine = rememberEngine()
    val modelLoader = rememberModelLoader(engine = engine)
    val materialLoader = rememberMaterialLoader(engine = engine)
    val cameraNode = rememberARCameraNode(engine = engine)
    val childNodes = rememberNodes()
    val view = rememberView(engine = engine)
    val collisionSystem = rememberCollisionSystem(view = view)
    val planeRenderer = remember { mutableStateOf(true) }
    val frame = remember { mutableStateOf<Frame?>(null) }

    Box(modifier = Modifier.fillMaxSize()) {

        ARScene(
            modifier = Modifier.fillMaxSize(),
            childNodes = childNodes,
            engine = engine,
            view = view,
            modelLoader = modelLoader,
            collisionSystem = collisionSystem,
            planeRenderer = planeRenderer.value,
            cameraNode = cameraNode,
            materialLoader = materialLoader,
            onTrackingFailureChanged = { },
            onSessionUpdated = { _, updatedFrame -> frame.value = updatedFrame },
            sessionConfiguration = { session, config ->
                config.depthMode =
                    if (session.isDepthModeSupported(Config.DepthMode.AUTOMATIC)) Config.DepthMode.AUTOMATIC else Config.DepthMode.DISABLED
                config.lightEstimationMode = Config.LightEstimationMode.ENVIRONMENTAL_HDR
            },
            onGestureListener = rememberOnGestureListener(
                onSingleTapConfirmed = { e: MotionEvent, _: Node? ->
                    if (childNodes.isEmpty()) {
                        val hitResult = frame.value?.hitTest(e.x, e.y)?.firstOrNull {
                            it.isValid(depthPoint = false, point = false)
                        }
                        val anchor = hitResult?.createAnchorOrNull()
                        anchor?.let {
                            val color = Color(drill.color)
                            val cube = CubeNode(
                                engine = engine,
                                size = Float3(0.2f, 0.2f, 0.2f),
                                center = Float3(0f, 0f, 0f),
                                materialInstance = materialLoader.createColorInstance(color)
                            )
                            val anchorNode = AnchorNode(engine, anchor)
                            anchorNode.addChildNode(cube)
                            childNodes += anchorNode
                        }
                    }
                }
            )
        )


        Text(
            text = "Tap on ground to place drill marker",
            color = Color.White,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 24.dp)
        )
    }
}