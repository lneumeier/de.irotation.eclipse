package de.irotation.eclipse.jdt.ui;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeRoot;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

public class JumpSourceTestHandler extends AbstractHandler {

  @Override
  public Object execute(final ExecutionEvent event) throws ExecutionException {

    final IEditorPart editorPart = HandlerUtil.getActiveEditor(event);
    if (editorPart != null) {
      final ITypeRoot typeRoot = JavaUI.getEditorInputTypeRoot(editorPart.getEditorInput());
      if (typeRoot != null) {
        final IType type = typeRoot.findPrimaryType();
        if (type != null) {
          final String qualifiedName = type.getFullyQualifiedName();

          String jumpQualifiedName = "";
          if (qualifiedName.endsWith("Test")) {
            jumpQualifiedName = qualifiedName.replace("Test", "");
          } else {
            jumpQualifiedName = qualifiedName + "Test";
          }

          try {
            final IType testType = typeRoot.getJavaProject().findType(jumpQualifiedName);
            if (testType != null) {
              JavaUI.openInEditor(testType);
            }
          } catch (final JavaModelException | PartInitException e) {
          }
        }
      }
    }

    return null;
  }

}
