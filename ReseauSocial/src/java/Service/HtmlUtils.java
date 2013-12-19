/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Service;

/**
 *
 * @author Cryptaro
 */
public class HtmlUtils {
    
    public static String toHtml(String string){
        StringBuffer sb = new StringBuffer(string.length());
        // true if last char was blank
        boolean lastWasBlankChar = false;
        int len = string.length();
        char c;

        for (int i = 0; i < len; i++)
            {
            c = string.charAt(i);
            if (c == ' ') {
                // blank gets extra work,
                // this solves the problem you get if you replace all
                // blanks with &nbsp;, if you do that you loss 
                // word breaking
                if (lastWasBlankChar) {
                    lastWasBlankChar = false;
                    sb.append("&nbsp;");
                    }
                else {
                    lastWasBlankChar = true;
                    sb.append(' ');
                    }
                }
            else {
                lastWasBlankChar = false;
                //
                // HTML Special Chars
                if (c == '"')
                    sb.append("&quot;");
                else if (c == '&')
                    sb.append("&amp;");
                else if (c == '<')
                    sb.append("&lt;");
                else if (c == '>')
                    sb.append("&gt;");
                else if (c == 'é')
                    sb.append("&eacute;");
                else if (c == 'ê')
                    sb.append("&ecirc;");
                else if (c == 'â')
                    sb.append("&acirc;");
                else if (c == 'ô')
                    sb.append("&ocirc;");
                else if (c == 'ù')
                    sb.append("&ugrave;");
                else if (c == 'û')
                    sb.append("&ucirc;");
                else if (c == 'ï')
                    sb.append("&iuml;");
                else if (c == 'î')
                    sb.append("&icirc;");
                else if (c == 'ç')
                    sb.append("&ccedil;");
                /*else if (c == '\n')
                    // Handle Newline
                    sb.append("&lt;br/&gt;");*/
                else {
                    int ci = 0xffff & c;
                    if (ci < 160 )
                        // nothing special only 7 Bit
                        sb.append(c);
                    else {
                        // Not 7 Bit use the unicode system
                        sb.append("&#");
                        sb.append(new Integer(ci).toString());
                        sb.append(';');
                        }
                    }
                }
            }
        return sb.toString();
    }
}
