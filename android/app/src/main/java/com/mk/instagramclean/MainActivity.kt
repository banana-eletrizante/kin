package com.mk.instagramclean

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.provider.Settings
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {
    private val prefs by lazy { getSharedPreferences("settings", MODE_PRIVATE) }
    private val ink = Color.rgb(26, 31, 44)
    private val muted = Color.rgb(111, 119, 137)
    private val purple = Color.rgb(112, 83, 245)
    private val bg = Color.rgb(246, 247, 251)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = bg
        window.navigationBarColor = bg
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        val page = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(bg)
        }
        val scroll = ScrollView(this)
        val content = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(22), dp(24), dp(22), dp(28))
        }
        scroll.addView(content)
        page.addView(scroll, LinearLayout.LayoutParams(-1, 0, 1f))

        val brandRow = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
        }
        val mark = TextView(this).apply {
            text = "ic"
            textSize = 19f
            typeface = Typeface.DEFAULT_BOLD
            gravity = Gravity.CENTER
            setTextColor(Color.WHITE)
            background = rounded(purple, 16)
        }
        brandRow.addView(mark, LinearLayout.LayoutParams(dp(46), dp(46)))
        val brandText = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(12), 0, 0, 0)
        }
        brandText.addView(label("INSTAGRAM CLEAN", 12f, muted, true))
        brandText.addView(label("Seu Instagram, mais leve.", 17f, ink, true))
        brandRow.addView(brandText, LinearLayout.LayoutParams(0, -2, 1f))
        val version = TextView(this).apply {
            text = "BETA"
            textSize = 10f
            typeface = Typeface.DEFAULT_BOLD
            setTextColor(purple)
            setPadding(dp(10), dp(6), dp(10), dp(6))
            background = rounded(Color.rgb(235, 231, 255), 20)
        }
        brandRow.addView(version)
        content.addView(brandRow)
        content.addView(space(24))

        val hero = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(20), dp(20), dp(20), dp(20))
            background = GradientDrawable(
                GradientDrawable.Orientation.TL_BR,
                intArrayOf(Color.rgb(107, 83, 241), Color.rgb(76, 62, 194))
            ).apply { cornerRadius = dp(22).toFloat() }
        }
        hero.addView(label("FOCO NO QUE IMPORTA", 11f, Color.rgb(225, 220, 255), true))
        hero.addView(space(8))
        hero.addView(label("Menos distrações.\nMais conexão.", 25f, Color.WHITE, true))
        hero.addView(space(8))
        hero.addView(label("Mantenha o feed, os Stories e as conversas. Oculte os atalhos que tiram seu foco.", 14f, Color.rgb(238, 235, 255), false))
        content.addView(hero)
        content.addView(space(22))

        content.addView(label("SEUS BLOQUEIOS", 12f, muted, true))
        content.addView(space(10))
        val settingsCard = card()
        addToggle(settingsCard, "Reels", "Bloqueia o atalho de vídeos curtos", "block_reels", true)
        settingsCard.addView(separator())
        addToggle(settingsCard, "Explorar", "Bloqueia a área de descoberta", "block_explore", true)
        content.addView(settingsCard)
        content.addView(space(20))

        content.addView(label("CONFIGURAÇÃO", 12f, muted, true))
        content.addView(space(10))
        val setupCard = card()
        setupCard.setPadding(dp(16), dp(16), dp(16), dp(16))
        setupCard.addView(label("Ative o serviço de acessibilidade", 16f, ink, true))
        setupCard.addView(space(6))
        setupCard.addView(label("O Android precisa dessa permissão para reconhecer os atalhos dentro do Instagram. Neste protótipo, o processamento acontece localmente.", 13f, muted, false))
        setupCard.addView(space(14))
        val enable = Button(this).apply {
            text = "Abrir configurações de acessibilidade"
            isAllCaps = false
            textSize = 14f
            setTextColor(Color.WHITE)
            background = rounded(purple, 14)
            setOnClickListener { startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)) }
        }
        setupCard.addView(enable, LinearLayout.LayoutParams(-1, dp(50)))
        content.addView(setupCard)
        content.addView(space(14))

        val open = Button(this).apply {
            text = "Abrir Instagram"
            isAllCaps = false
            textSize = 15f
            typeface = Typeface.DEFAULT_BOLD
            setTextColor(purple)
            background = rounded(Color.rgb(235, 231, 255), 14)
            setOnClickListener {
                val launch = packageManager.getLaunchIntentForPackage("com.instagram.android")
                if (launch != null) startActivity(launch)
                else Toast.makeText(this@MainActivity, "Instale o Instagram primeiro.", Toast.LENGTH_LONG).show()
            }
        }
        content.addView(open, LinearLayout.LayoutParams(-1, dp(52)))
        content.addView(space(18))
        content.addView(label("Instagram Clean não é afiliado à Meta. O bloqueio é experimental e pode variar conforme a versão do Instagram.", 11f, muted, false))
        setContentView(page)
    }

    private fun addToggle(parent: LinearLayout, title: String, description: String, key: String, default: Boolean) {
        val row = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            setPadding(dp(16), dp(15), dp(14), dp(15))
        }
        val texts = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL }
        texts.addView(label(title, 16f, ink, true))
        texts.addView(space(4))
        texts.addView(label(description, 12f, muted, false))
        row.addView(texts, LinearLayout.LayoutParams(0, -2, 1f))
        val toggle = Switch(this).apply {
            isChecked = prefs.getBoolean(key, default)
            setOnCheckedChangeListener { _, checked -> prefs.edit().putBoolean(key, checked).apply() }
        }
        row.addView(toggle)
        parent.addView(row)
    }

    private fun card() = LinearLayout(this).apply {
        orientation = LinearLayout.VERTICAL
        background = rounded(Color.WHITE, 20)
        elevation = dp(2).toFloat()
    }
    private fun separator() = View(this).apply {
        setBackgroundColor(Color.rgb(239, 240, 245))
        layoutParams = LinearLayout.LayoutParams(-1, dp(1))
    }
    private fun label(value: String, size: Float, color: Int, bold: Boolean) =
        TextView(this).apply {
            text = value
            textSize = size
            setTextColor(color)
            if (bold) typeface = Typeface.DEFAULT_BOLD
            setLineSpacing(dp(2).toFloat(), 1f)
        }
    private fun space(height: Int) = View(this).apply {
        layoutParams = LinearLayout.LayoutParams(1, dp(height))
    }
    private fun rounded(color: Int, radius: Int) = GradientDrawable().apply {
        setColor(color)
        cornerRadius = dp(radius).toFloat()
    }
    private fun dp(value: Int) = (value * resources.displayMetrics.density).toInt()
}
