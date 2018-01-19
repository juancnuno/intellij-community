/*
 * Copyright 2000-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
 */
package com.intellij.lang.documentation;

public interface DocumentationMarkup {
  String DEFINITION_START = "<div class='definition'><pre>";
  String DEFINITION_END = "</pre></div>";
  String CONTENT_START = "<div class='content'>";
  String CONTENT_END = "</div>";
  String SECTIONS_START = "<table class='sections'>";
  String SECTIONS_END = "</table>";
  String SECTION_START = "<tr><td valign='top' class='section'><p>";
  String SECTION_SEPARATOR = "</td><td>";
  String SECTION_END = "</td>";
}
