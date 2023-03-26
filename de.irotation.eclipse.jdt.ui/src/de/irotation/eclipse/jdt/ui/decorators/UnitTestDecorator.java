package de.irotation.eclipse.jdt.ui.decorators;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ResourceLocator;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILightweightLabelDecorator;
import org.eclipse.jface.viewers.LabelProvider;

public class UnitTestDecorator extends LabelProvider implements ILightweightLabelDecorator {

  private static final String CLASS_HAS_TEST_IMG = "icons/class-has-test.png";
  private static final ImageDescriptor overlay = ResourceLocator.imageDescriptorFromBundle(UnitTestDecorator.class, CLASS_HAS_TEST_IMG)
      .orElse(null);

  @Override
  public void decorate(final Object element, final IDecoration decoration) {

    if (element instanceof ICompilationUnit) {

      final ICompilationUnit cu = (ICompilationUnit) element;
      final IType type = cu.findPrimaryType();
      if (type != null) {

        final String qualifiedName = type.getFullyQualifiedName();
        final String testQualifiedName = qualifiedName + "Test";

        try {
          final IType testType = cu.getJavaProject().findType(testQualifiedName);
          if (testType != null && overlay != null) {
            decoration.addOverlay(overlay);
          }
        } catch (final JavaModelException e) {
        }
      }
    }
  }

}
