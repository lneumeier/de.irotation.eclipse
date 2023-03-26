package de.irotation.eclipse.jdt.core;

import java.util.Map;

import org.eclipse.jdt.internal.formatter.DefaultCodeFormatter;
import org.eclipse.jdt.internal.formatter.DefaultCodeFormatterOptions;
import org.eclipse.jface.text.IRegion;
import org.eclipse.text.edits.TextEdit;

public class LFCodeFormatter extends DefaultCodeFormatter {

  public LFCodeFormatter() {
    super();
  }

  public LFCodeFormatter(DefaultCodeFormatterOptions options) {
    super(options);
  }

  public LFCodeFormatter(Map<String, String> options) {
    super(options);
  }

  public LFCodeFormatter(DefaultCodeFormatterOptions defaultCodeFormatterOptions, Map<String, String> options) {
    super(defaultCodeFormatterOptions, options);
  }

  @Override
  public TextEdit format(int kind, String source, int offset, int length, int indentationLevel, String lineSeparator) {
    return super.format(kind, source, offset, length, indentationLevel, "\n");
  }

  @Override
  public TextEdit format(int kind, String source, IRegion[] regions, int indentationLevel, String lineSeparator) {
    return super.format(kind, source, regions, indentationLevel, "\n");
  }

}
