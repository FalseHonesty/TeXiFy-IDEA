package nl.rubensten.texifyidea.formatting

import com.intellij.formatting.Spacing
import com.intellij.psi.codeStyle.CodeStyleSettings
import nl.rubensten.texifyidea.LatexLanguage
import nl.rubensten.texifyidea.psi.LatexTypes.*
import nl.rubensten.texifyidea.settings.LatexCodeStyleSettings

/**
 *
 * @author Sten Wessel
 */
fun createSpacingBuilder(settings: CodeStyleSettings): LatexSpacingBuilder {
    val latexSettings = settings.getCustomSettings(LatexCodeStyleSettings::class.java)!!
    val latexCommonSettings = settings.getCommonSettings(LatexLanguage.INSTANCE)!!

    return rules(latexCommonSettings) {

        simple {
            between(NORMAL_TEXT_WORD, NORMAL_TEXT_WORD).spaces(1)
            around(ENVIRONMENT_CONTENT).lineBreakInCode()
        }

        custom {
            fun commentSpacing(minSpaces: Int): Spacing {
                // TODO: blank lines settings
                if (latexCommonSettings.KEEP_FIRST_COLUMN_COMMENT) {
                    return Spacing.createKeepingFirstColumnSpacing(minSpaces, Int.MAX_VALUE, latexCommonSettings.KEEP_LINE_BREAKS, latexCommonSettings.KEEP_BLANK_LINES_IN_CODE)
                }
                return Spacing.createSpacing(minSpaces, Int.MAX_VALUE, 0, latexCommonSettings.KEEP_LINE_BREAKS, latexCommonSettings.KEEP_BLANK_LINES_IN_CODE)
            }

            inPosition(right = COMMENT_TOKEN).spacing(commentSpacing(0))
        }
    }
}
