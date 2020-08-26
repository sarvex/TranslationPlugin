package cn.yiiguxing.plugin.translate.ui

import cn.yiiguxing.plugin.translate.message
import com.intellij.util.ui.components.BorderLayoutPanel
import icons.Icons
import javax.swing.JLabel
import javax.swing.SwingConstants

class SpellComponent: BorderLayoutPanel() {
    var spell: String? = null
        set(value) {
            field = value
            isVisible = spell != null
            spellText.text = spell
            spellText.toolTipText = spell
        }

    val spellLabel = JLabel(
        message("translation.ui.pane.label.spell"),
        Icons.AutoAwesome,
        SwingConstants.LEADING
    )

    val spellText = ActionLink("是不是Are you OK?") {
        val handler = onSpellFixedHandler ?: return@ActionLink
        spell?.let { handler(it) }
    }

    init {
        isOpaque = false
        isVisible = false
        addToLeft(spellLabel)
        addToCenter(spellText)
    }

    private var onSpellFixedHandler: ((String) -> Unit)? = null

    fun onSpellFixed(handler: (spell: String) -> Unit) {
        onSpellFixedHandler = handler
    }
}