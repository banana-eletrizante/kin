package com.mk.instagramclean

import android.accessibilityservice.AccessibilityService
import android.os.Handler
import android.os.Looper
import android.view.accessibility.AccessibilityEvent
import java.util.Locale

class InstagramBlockerService : AccessibilityService() {
    private val handler = Handler(Looper.getMainLooper())
    private var lastBlockedAt = 0L

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event == null || event.packageName?.toString() != "com.instagram.android") return
        if (event.eventType != AccessibilityEvent.TYPE_VIEW_CLICKED) return

        val label = buildString {
            event.text?.forEach { append(it).append(' ') }
            event.contentDescription?.let { append(it) }
            event.source?.let { node ->
                append(' ')
                append(node.text ?: "")
                append(' ')
                append(node.contentDescription ?: "")
                node.recycle()
            }
        }.lowercase(Locale.ROOT)

        val prefs = getSharedPreferences("settings", MODE_PRIVATE)
        val isReels = prefs.getBoolean("block_reels", true) &&
            listOf("reels", "vídeos curtos", "short videos").any(label::contains)
        val isExplore = prefs.getBoolean("block_explore", true) &&
            listOf("explorar", "search and explore", "pesquisar", "search").any(label::contains)

        if (isReels || isExplore) {
            val now = System.currentTimeMillis()
            if (now - lastBlockedAt > 700) {
                lastBlockedAt = now
                handler.postDelayed({ performGlobalAction(GLOBAL_ACTION_BACK) }, 120)
            }
        }
    }

    override fun onInterrupt() = Unit
}
