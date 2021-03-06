package org.jetbrains.idea.devkit.references.extensions;

import com.intellij.codeInsight.documentation.DocumentationManager;
import com.intellij.lang.documentation.DocumentationProvider;
import com.intellij.psi.PsiElement;
import com.intellij.testFramework.TestDataPath;
import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase;
import org.jetbrains.idea.devkit.DevkitJavaTestsUtil;

@TestDataPath("$CONTENT_ROOT/testData/references/extensions")
public class ExtensionPointDocumentationProviderTest extends LightJavaCodeInsightFixtureTestCase {

  @Override
  public String getBasePath() {
    return DevkitJavaTestsUtil.TESTDATA_PATH + "references/extensions";
  }

  private PsiElement getOriginalElement() {
    return myFixture.getFile().findElementAt(myFixture.getEditor().getCaretModel().getOffset());
  }

  public void testBeanClassExtensionPointDocumentation() {
    myFixture.configureByFiles("beanClassExtensionPointDocumentation.xml",
                               "bar/MyExtensionPoint.java", "bar/MyExtension.java");

    final PsiElement docElement =
      DocumentationManager.getInstance(getProject()).findTargetElement(myFixture.getEditor(),
                                                                       myFixture.getFile());
    DocumentationProvider provider = DocumentationManager.getProviderFromElement(docElement);

    String epDefinition = "[" + getModule().getName() + "]" +
                          "<br/><b>foo.bar</b> (beanClassExtensionPointDocumentation.xml)<br/>" +
                          "<a href=\"psi_element://bar.MyExtensionPoint\"><code>MyExtensionPoint</code></a><br/>" +
                          "<a href=\"psi_element://bar.MyExtension\"><code>MyExtension</code></a>";
    assertEquals(epDefinition,
                 provider.getQuickNavigateInfo(docElement, getOriginalElement()));

    assertEquals(
      "<div class='definition'><pre><b>foo.bar</b><br>beanClassExtensionPointDocumentation.xml<div class='definition'><pre>bar<br>public interface <b>MyExtensionPoint</b></pre></div><div class='content'>\n" +
      "   MyExtensionPoint JavaDoc.\n" +
      " </div><table class='sections'><p></table><table class='sections'><tr><td valign='top' class='section'><p>implementationClass:</td><td valign='top'><a href=\"psi_element://bar.MyExtension\"><code>MyExtension</code></a></td><tr><td valign='top' class='section'><p>&lt;tagName&gt;:</td><td valign='top'><a href=\"psi_element://java.lang.Integer\"><code>Integer</code></a></td></table></pre></div><div class='content'><h2>Extension Point Implementation</h2><div class='definition'><pre>bar<br>public interface <b>MyExtension</b></pre></div><div class='content'>\n" +
      "   My Extension Javadoc.\n" +
      " </div><table class='sections'><p></table></div>",
      provider.generateDoc(docElement, getOriginalElement()));
  }

  public void testInterfaceExtensionPointDocumentation() {
    myFixture.configureByFiles("interfaceExtensionPointDocumentation.xml",
                               "bar/MyExtension.java");

    final PsiElement docElement =
      DocumentationManager.getInstance(getProject()).findTargetElement(myFixture.getEditor(),
                                                                       myFixture.getFile());
    DocumentationProvider provider = DocumentationManager.getProviderFromElement(docElement);

    String epDefinition = "[" + getModule().getName() + "]" +
                          "<br/><b>foo.bar</b> (interfaceExtensionPointDocumentation.xml)<br/>" +
                          "<a href=\"psi_element://bar.MyExtension\"><code>MyExtension</code></a>";
    assertEquals(epDefinition,
                 provider.getQuickNavigateInfo(docElement, getOriginalElement()));

    assertEquals(
      "<div class='definition'><pre><b>foo.bar</b><br>interfaceExtensionPointDocumentation.xml</pre></div><div class='content'><h2>Extension Point Implementation</h2><div class='definition'><pre>bar<br>public interface <b>MyExtension</b></pre></div><div class='content'>\n" +
      "   My Extension Javadoc.\n" +
      " </div><table class='sections'><p></table></div>",
      provider.generateDoc(docElement, getOriginalElement()));
  }
}