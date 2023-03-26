package de.irotation.eclipse.jdt.ui.property;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeRoot;
import org.eclipse.jdt.internal.ui.javaeditor.EditorUtility;
import org.eclipse.jdt.internal.ui.javaeditor.JavaEditor;

public class JumpPropertyTester extends PropertyTester {

  @Override
  public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {

    if (receiver instanceof JavaEditor) {

      final JavaEditor editor = (JavaEditor) receiver;
      final ITypeRoot typeRoot = EditorUtility.getEditorInputJavaElement(editor, false);

      if (typeRoot != null) {
        final IType type = typeRoot.findPrimaryType();
        if (type != null) {
          final String qualifiedName = type.getFullyQualifiedName();

          if ("isTestClass".equals(property)) {
            return qualifiedName.endsWith("Test");
          } else if ("isClass".equals(property)) {
            return !qualifiedName.endsWith("Test");
          }
        }
      }
    }

    return false;
  }

}
