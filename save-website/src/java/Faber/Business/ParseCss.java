package Faber.Business;

import com.steadystate.css.parser.CSSOMParser;
import org.w3c.css.sac.InputSource;
import org.w3c.dom.css.CSSStyleSheet;
import org.w3c.dom.css.CSSRuleList;
import org.w3c.dom.css.CSSRule;
import org.w3c.dom.css.CSSStyleRule;
import org.w3c.dom.css.CSSStyleDeclaration;
import java.io.*;
 
 
public class ParseCss 
{
     public boolean Parse(String cssfile) 
     {
 
         FileOutputStream out = null; 
         PrintStream ps = null; 
         boolean rtn = false;
 
         try
         {
 
                // cssfile accessed as a resource, so must be in the pkg (in src dir).
                //InputStream stream = oParser.getClass().getResourceAsStream(cssfile);
                 InputStream stream = new FileInputStream("F:\\hoctap\\java\\save-website\\save-website\\save-website\\web\\css\\abc.css");
 
                 // overwrites and existing file contents
                 out = new FileOutputStream("log.txt");
 
//                 if (out != null)
//                 {
//                     //log file
//                     ps = new PrintStream( out );
//                     System.setErr(ps); //redirects stderr to the log file as well
// 
//                 } else {
// 
//                     return rtn; 
// 
//                }
 
 
                InputSource source = new InputSource(new InputStreamReader(stream));
                String hb = source.getURI();
                CSSOMParser parser = new CSSOMParser();
                // parse and create a stylesheet composition
                CSSStyleSheet stylesheet = parser.parseStyleSheet(source, null, null);
 
                //ANY ERRORS IN THE DOM WILL BE SENT TO STDERR HERE!!
                // now iterate through the dom and inspect.
 
                CSSRuleList ruleList = stylesheet.getCssRules();
 
               for (int i = 0; i < ruleList.getLength(); i++) 
               {
                 CSSRule rule = ruleList.item(i);
                 if (rule instanceof CSSStyleRule) 
                 {
                     CSSStyleRule styleRule=(CSSStyleRule)rule;
                     CSSStyleDeclaration styleDeclaration = styleRule.getStyle();
 
                     for (int j = 0; j < styleDeclaration.getLength(); j++)                        // loop
                     {
                          String property = styleDeclaration.item(j);
                          String a = property;
                          a = styleDeclaration.getPropertyCSSValue(property).getCssText();
                          a=  styleDeclaration.getPropertyPriority(property);                                  
                     }
 
 
 
                  }// end of StyleRule instance test
                } // end of ruleList loop
 
               if (out != null) out.close();
               if (stream != null) stream.close();
               rtn = true;
            }
            catch (IOException ioe)
            {
                System.err.println ("IO Error: " + ioe);
            }
            catch (Exception e)
            {
                System.err.println ("Error: " + e);
 
            }
            finally
            {
                if (ps != null) ps.close(); 
            }
 
            return rtn;
 
    }}