package com.example.Genesis.combat.arCore

import android.graphics.Point
import android.media.AudioAttributes
import android.media.SoundPool
import android.net.Uri
import android.os.Bundle
import android.view.Display
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.Genesis.R
import com.example.Genesis.combat.monster.Dragon
import com.example.Genesis.combat.monster.Orc
import com.google.ar.core.Anchor
import com.google.ar.core.Pose
import com.google.ar.core.Session
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.Camera
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.Scene
import com.google.ar.sceneform.math.Quaternion
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.*
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import java.util.*
import java.util.function.Consumer
import kotlin.properties.Delegates


class arCore : AppCompatActivity() {
    private lateinit var scene: Scene
    private lateinit var camera: Camera
    private var bulletRenderable: ModelRenderable? = null
    private var shouldStartTimer = true
    private var balloonsLeft = 20
    private var point: Point? = null
    private var balloonsLeftTxt: TextView? = null
    private lateinit var soundPool: SoundPool
    private var num by Delegates.notNull<Int>()
    private lateinit var orc : Orc
    private lateinit var dragon : Dragon
    private lateinit var playerHealth : TextView
    private lateinit var monsterHealth : TextView
    private var sound = 0
    private lateinit var arFragment : CustomArFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ar_core)
        val display: Display = getWindowManager().getDefaultDisplay()
        point = Point()
        display.getRealSize(point)
        loadSoundPool()
        arFragment = getSupportFragmentManager().findFragmentById(R.id.arFragment) as CustomArFragment
        scene = arFragment.arSceneView.scene
        camera = scene.getCamera()
        val session: Session? = arFragment.arSceneView.session
        val pos = floatArrayOf(0f, 0f, 0f)
        val rotation = floatArrayOf(0f, 0f, 0f, 1f)
        val anchor: Anchor? = session?.createAnchor(Pose(pos, rotation))
        val anchorNode = AnchorNode(anchor)
        anchorNode.setParent(arFragment.arSceneView.scene)
        monsterHealth = findViewById<TextView>(R.id.monsterHP);
        playerHealth = findViewById<TextView>(R.id.playerHP);
        val random = Random()
        num = random.nextInt(2);
        if(num != 1)
        {
            dragon = Dragon(addDragon(anchorNode))
            monsterHealth.text = "Monster Health: " + dragon.maxHealth
            playerHealth.text = "Player Health: " + 100
        }
        else {
            orc = Orc(addOrc(anchorNode))
            monsterHealth.text = "Monster Health: " + orc.maxHealth
            playerHealth.text = "Player Health: " + 100
        }
        buildBulletModel()
        val shoot: Button = findViewById(R.id.shootButton)


        shoot.setOnClickListener { v: View? ->
            shoot()
       }
    }

    private fun loadSoundPool() {
        val audioAttributes = AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .setUsage(AudioAttributes.USAGE_GAME)
            .build()
        soundPool = SoundPool.Builder()
            .setMaxStreams(1)
            .setAudioAttributes(audioAttributes)
            .build()
        sound = soundPool.load(this, R.raw.blop_sound, 1)
    }

    private fun shoot() {
        val ray = camera!!.screenPointToRay(point!!.x / 2f, point!!.y / 2f)
        val node = Node()
        var test : Boolean = true
        node.renderable = bulletRenderable
        scene!!.addChild(node)
        Thread(Runnable {
            var i = 0
            while (test) {
                runOnUiThread {
                    val vector3 = ray.getPoint(i * 0.1f)
                    node.worldPosition = vector3
                    val nodeInContact = scene!!.overlapTest(node)
                    if (nodeInContact != null) {
                        if(test) {
                            if(num != 1)
                            {
                                if(dragon.health >= 10)
                                {
                                    dragon.health = dragon.health - 10;
                                }
                                monsterHealth.text = "Monster Health: " + dragon.health
                                if (dragon.health <= 0) {
                                    scene!!.removeChild(nodeInContact)
                                    var toast: Toast =
                                        Toast.makeText(this, "You win", Toast.LENGTH_SHORT)
                                    toast.show()
                                }
                            }
                            else {
                                if(orc.health >= 10)
                                {
                                    orc.health = orc.health - 10;
                                }
                                monsterHealth.text = "Monster Health: " + orc.health
                                if (orc.health <= 0) {
                                    scene!!.removeChild(nodeInContact)
                                    var toast: Toast =
                                        Toast.makeText(this, "You win", Toast.LENGTH_SHORT)
                                    toast.show()
                                }
                            }
                        }
                        test = false
                        scene!!.removeChild(node)
                        soundPool!!.play(
                            sound, 1f, 1f, 1, 0
                            , 1f
                        )
                    }
                }
                i++
                try {
                    Thread.sleep(10)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            runOnUiThread {}
        }).start()
    }


    private fun buildBulletModel() {
        Texture
            .builder()
            .setSource(this, R.drawable.texture)
            .build()
            .thenAccept(Consumer<Texture> { texture: Texture? ->
                MaterialFactory
                    .makeOpaqueWithTexture(this, texture)
                    .thenAccept { material: Material? ->
                        bulletRenderable = ShapeFactory
                            .makeSphere(
                                0.01f,
                                Vector3(0f, 0f, 0f),
                                material
                            )
                    }
            })
    }
    private fun addOrc(anchorNode: AnchorNode) : TransformableNode
    {
        var node = TransformableNode(arFragment.transformationSystem)
        ModelRenderable
            .builder()
            .setSource(this, Uri.parse("orc.sfb"))
            .build()
            .thenAccept(Consumer<ModelRenderable> { renderable: ModelRenderable? ->
                node.name = "test"
                node.renderable = renderable
                node.getScaleController().setMaxScale(2f);
                node.getScaleController().setMinScale(1f);
                node.setParent(anchorNode)
                scene!!.addChild(node)
                val random = Random()
                val x = 0
                val y = -1
                var z = 3
                z = -z
                node.localRotation = Quaternion.multiply(node.localRotation, Quaternion(Vector3.up(), -90f))
                node.worldPosition = Vector3(
                    x.toFloat(),
                    y.toFloat(),
                    z.toFloat()
                )
            })
        return node
    }

    private fun addDragon(anchorNode: AnchorNode) : TransformableNode
    {
        var node = TransformableNode(arFragment.transformationSystem)
        ModelRenderable
            .builder()
            .setSource(this, Uri.parse("dragon.sfb"))
            .build()
            .thenAccept(Consumer<ModelRenderable> { renderable: ModelRenderable? ->
                node.name = "test"
                node.renderable = renderable
                node.getScaleController().setMaxScale(4f);
                node.getScaleController().setMinScale(3f);
                node.setParent(anchorNode)
                scene!!.addChild(node)
                val random = Random()
                val x = 0
                val y = -1
                var z = 3
                z = -z
                node.localRotation = Quaternion.multiply(node.localRotation, Quaternion(Vector3.up(), -90f))
                node.worldPosition = Vector3(
                    x.toFloat(),
                    y.toFloat(),
                    z.toFloat()
                )
            })
        return node
    }

}
