package nl.rubensten.texifyidea.psi

import com.intellij.psi.tree.IElementType
import nl.rubensten.texifyidea.LatexLanguage
import org.jetbrains.annotations.NonNls

/**
 * @author Sten Wessel
 */
class LatexElementType(@NonNls debugName: String) : IElementType(debugName, LatexLanguage.INSTANCE)