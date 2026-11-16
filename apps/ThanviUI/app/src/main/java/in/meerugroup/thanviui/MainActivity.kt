package in.meerugroup.thanviui

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.View
import android.widget.*

class MainActivity : Activity() {
    private val navy = Color.rgb(7, 14, 31)
    private val panel = Color.rgb(17, 28, 51)
    private val panel2 = Color.rgb(23, 37, 64)
    private val gold = Color.rgb(218, 181, 92)
    private val white = Color.rgb(245, 246, 250)
    private val muted = Color.rgb(157, 169, 190)
    private lateinit var content: LinearLayout
    private lateinit var nav: LinearLayout
    private var current = "Home"
    private val selectedTiles = mutableSetOf("Wi-Fi", "Bluetooth")
    private var brightness = 68

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = navy
        window.navigationBarColor = navy
        window.decorView.systemUiVisibility = 0
        buildShell()
        showScreen("Home")
    }

    private fun bg(color: Int, radius: Float = 22f, stroke: Int = 0): GradientDrawable =
        GradientDrawable().apply {
            setColor(color)
            cornerRadius = radius * resources.displayMetrics.density
            if (stroke != 0) setStroke((1 * resources.displayMetrics.density).toInt(), stroke)
        }

    private fun dp(v: Int) = (v * resources.displayMetrics.density).toInt()

    private fun buildShell() {
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(navy)
        }
        val header = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            setPadding(dp(22), dp(18), dp(22), dp(12))
        }
        val brand = TextView(this).apply {
            text = "THANVI"
            textSize = 20f
            typeface = Typeface.create("sans-serif", Typeface.BOLD)
            setTextColor(gold)
            letterSpacing = .16f
        }
        val edition = TextView(this).apply {
            text = "UI  •  STAGE 02"
            textSize = 10f
            setTextColor(muted)
            gravity = Gravity.CENTER_VERTICAL
            setPadding(dp(10), 0, 0, 0)
        }
        header.addView(brand)
        header.addView(edition)
        val spacer = Space(this)
        header.addView(spacer, LinearLayout.LayoutParams(0, 1, 1f))
        val dot = TextView(this).apply {
            text = "●"
            textSize = 13f
            setTextColor(gold)
        }
        header.addView(dot)
        root.addView(header)

        content = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(18), dp(8), dp(18), dp(14))
        }
        val scroll = ScrollView(this).apply {
            isFillViewport = true
            addView(content)
        }
        root.addView(scroll, LinearLayout.LayoutParams(-1, 0, 1f))

        nav = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
            setPadding(dp(8), dp(9), dp(8), dp(14))
            background = bg(Color.rgb(11, 21, 40), 20f)
        }
        root.addView(nav, LinearLayout.LayoutParams(-1, -2))
        setContentView(root)
        updateNav()
    }

    private fun updateNav() {
        nav.removeAllViews()
        listOf("Home", "Lock", "Control", "Settings").forEach { name ->
            val item = TextView(this).apply {
                text = when (name) {
                    "Home" -> "⌂\nHome"
                    "Lock" -> "◷\nLock"
                    "Control" -> "☷\nControl"
                    else -> "⚙\nSettings"
                }
                gravity = Gravity.CENTER
                textSize = 11f
                setTextColor(if (current == name) gold else muted)
                setPadding(dp(5), dp(4), dp(5), dp(4))
                setOnClickListener { showScreen(name) }
            }
            nav.addView(item, LinearLayout.LayoutParams(0, -2, 1f))
        }
    }

    private fun showScreen(screen: String) {
        current = screen
        content.removeAllViews()
        when (screen) {
            "Home" -> home()
            "Lock" -> lock()
            "Control" -> control()
            else -> settings()
        }
        updateNav()
    }

    private fun addText(parent: LinearLayout, text: String, size: Float, color: Int,
                        bold: Boolean = false, top: Int = 0) {
        val tv = TextView(this).apply {
            this.text = text
            textSize = size
            setTextColor(color)
            if (bold) typeface = Typeface.create("sans-serif", Typeface.BOLD)
            if (top > 0) setPadding(0, dp(top), 0, 0)
        }
        parent.addView(tv)
    }

    private fun section(text: String) {
        addText(content, text.uppercase(), 11f, gold, true, 20)
    }

    private fun card(parent: LinearLayout, pad: Int = 18): LinearLayout {
        val box = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(pad), dp(pad), dp(pad), dp(pad))
            background = bg(panel, 22f, Color.rgb(42, 57, 82))
        }
        val lp = LinearLayout.LayoutParams(-1, -2)
        lp.topMargin = dp(12)
        parent.addView(box, lp)
        return box
    }

    private fun pill(parent: LinearLayout, label: String, detail: String) {
        val row = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            setPadding(dp(14), dp(14), dp(14), dp(14))
            background = bg(panel2, 16f)
        }
        val texts = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL }
        addText(texts, label, 15f, white, true)
        addText(texts, detail, 12f, muted, false, 4)
        row.addView(texts, LinearLayout.LayoutParams(0, -2, 1f))
        parent.addView(row, LinearLayout.LayoutParams(-1, -2).apply { topMargin = dp(8) })
    }

    private fun home() {
        addText(content, "Good afternoon", 14f, muted, false, 10)
        addText(content, "Your space.\nYour control.", 31f, white, true, 5)
        val hero = card(content, 22)
        addText(hero, "TUESDAY  •  NOVEMBER 17", 10f, gold, true)
        addText(hero, "12:11", 54f, white, true, 12)
        addText(hero, "THANVI OS  /  PRIVATE BY DESIGN", 10f, muted, false, 2)
        section("Quick access")
        val quick = card(content)
        pill(quick, "◈  Security Center", "Review privacy and device protection")
        pill(quick, "▣  Private Vault", "Private storage concept preview")
        pill(quick, "⟳  System Updates", "Update status interface preview")
        section("Your apps")
        val apps = card(content)
        val grid = listOf("Phone", "Messages", "Camera", "Gallery", "Files", "Browser")
        grid.chunked(3).forEach { line ->
            val row = LinearLayout(this).apply { orientation = LinearLayout.HORIZONTAL }
            line.forEach { name ->
                val cell = TextView(this).apply {
                    text = "◉\n$name"
                    gravity = Gravity.CENTER
                    textSize = 12f
                    setTextColor(white)
                    setPadding(dp(5), dp(16), dp(5), dp(16))
                }
                row.addView(cell, LinearLayout.LayoutParams(0, -2, 1f))
            }
            apps.addView(row)
        }
    }

    private fun lock() {
        addText(content, "LOCK SCREEN PREVIEW", 11f, gold, true, 12)
        val preview = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(dp(20), dp(50), dp(20), dp(50))
            background = GradientDrawable(
                GradientDrawable.Orientation.TL_BR,
                intArrayOf(Color.rgb(20, 35, 65), Color.rgb(8, 13, 29), Color.rgb(55, 40, 30))
            ).apply { cornerRadius = dp(28).toFloat() }
        }
        addText(preview, "TUESDAY, NOVEMBER 17", 11f, gold, true)
        addText(preview, "12:11", 62f, white, true, 16)
        addText(preview, "THANVI OS", 12f, muted, true, 5)
        addText(preview, "◉", 30f, gold, false, 45)
        addText(preview, "Fingerprint area preview", 12f, muted, false, 6)
        content.addView(preview, LinearLayout.LayoutParams(-1, dp(440)).apply { topMargin = dp(14) })
        val note = card(content)
        addText(note, "Preview only", 15f, gold, true)
        addText(note, "This screen does not replace Android Keyguard or perform biometric authentication.", 12f, muted, false, 6)
    }

    private fun control() {
        addText(content, "Control Center", 28f, white, true, 8)
        addText(content, "Quick controls • prototype", 13f, muted, false, 5)
        val tiles = card(content)
        val names = listOf("Wi-Fi", "Bluetooth", "Airplane", "Torch", "Location", "Do Not Disturb")
        names.chunked(2).forEach { pair ->
            val row = LinearLayout(this).apply { orientation = LinearLayout.HORIZONTAL }
            pair.forEach { name ->
                val active = selectedTiles.contains(name)
                val tile = TextView(this).apply {
                    text = (if (active) "●  " else "○  ") + name
                    textSize = 14f
                    typeface = Typeface.DEFAULT_BOLD
                    setTextColor(if (active) navy else white)
                    gravity = Gravity.CENTER_VERTICAL
                    setPadding(dp(13), dp(20), dp(8), dp(20))
                    background = bg(if (active) gold else panel2, 16f)
                    setOnClickListener {
                        if (selectedTiles.contains(name)) selectedTiles.remove(name)
                        else selectedTiles.add(name)
                        showScreen("Control")
                    }
                }
                val lp = LinearLayout.LayoutParams(0, -2, 1f)
                lp.setMargins(dp(4), dp(4), dp(4), dp(4))
                row.addView(tile, lp)
            }
            tiles.addView(row)
        }
        val brightnessCard = card(content)
        addText(brightnessCard, "Brightness", 15f, white, true)
        val value = TextView(this).apply {
            text = "$brightness%"
            textSize = 12f
            setTextColor(gold)
            gravity = Gravity.END
        }
        brightnessCard.addView(value)
        val seek = SeekBar(this).apply {
            max = 100
            progress = brightness
            progressTintList = android.content.res.ColorStateList.valueOf(gold)
            thumbTintList = android.content.res.ColorStateList.valueOf(gold)
            setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(s: SeekBar?, p: Int, user: Boolean) {
                    brightness = p
                    value.text = "$p%"
                }
                override fun onStartTrackingTouch(s: SeekBar?) {}
                override fun onStopTrackingTouch(s: SeekBar?) {}
            })
        }
        brightnessCard.addView(seek)
        val disclaimer = card(content)
        addText(disclaimer, "Prototype controls", 14f, gold, true)
        addText(disclaimer, "These switches only change their appearance in this demo. They do not change real device radios, torch, location or system brightness.", 12f, muted, false, 6)
    }

    private fun settings() {
        addText(content, "Settings", 29f, white, true, 8)
        addText(content, "Make your device yours.", 13f, muted, false, 4)
        val groups = listOf(
            "CONNECTIONS" to listOf("Wi-Fi and network", "Bluetooth and devices", "SIM and mobile network"),
            "PERSONALIZATION" to listOf("Display and brightness", "Wallpaper and style", "Sound and vibration"),
            "PRIVACY" to listOf("Permission manager", "Privacy dashboard", "Camera and microphone access"),
            "SECURITY" to listOf("Screen lock", "THANVI Shield", "THANVI Vault"),
            "SYSTEM" to listOf("System update", "About THANVI OS")
        )
        groups.forEach { (heading, entries) ->
            section(heading)
            val group = card(content, 15)
            entries.forEachIndexed { index, entry ->
                val row = TextView(this).apply {
                    text = "›   $entry"
                    textSize = 14f
                    setTextColor(white)
                    setPadding(dp(4), dp(15), dp(4), dp(15))
                    if (index != entries.lastIndex) {
                        setBackgroundColor(Color.TRANSPARENT)
                    }
                    setOnClickListener {
                        Toast.makeText(this@MainActivity, "$entry — UI preview only", Toast.LENGTH_SHORT).show()
                    }
                }
                group.addView(row)
                if (index != entries.lastIndex) {
                    val divider = View(this).apply { setBackgroundColor(Color.rgb(43, 55, 76)) }
                    group.addView(divider, LinearLayout.LayoutParams(-1, dp(1)))
                }
            }
        }
        val footer = card(content)
        addText(footer, "THANVI OS", 14f, gold, true)
        addText(footer, "Intelligence. Security. Freedom.\\nStage 2 interface concept • v0.2.0", 11f, muted, false, 6)
    }
}
