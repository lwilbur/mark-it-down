import java.lang.String;

public class Converter {
    public static String convertString(String markdownStr) {
        markdownStr = convertGeneralFormatting(markdownStr);
        markdownStr = convertItalics(markdownStr);
        markdownStr = convertBold(markdownStr);
        markdownStr = convertHeaders(markdownStr);
        // TODO: add more functions for all supported markdown characteristics
        return markdownStr;
    }

    private static String convertGeneralFormatting(String str) {
      String altered = null;
      return altered;
    }

    private static String convertItalics(String str) {
      char italicsMarker = '*';
      String italicsMarkerString = "*";
      //html tags to insert to convert markdown to html
      String startTag = "<html><em>";
      String endTag = "</em></html>";

      //if nothing to be italicized, just return string untouched
      if(str.contains(italicsMarkerString) == false){
        return str;
      }
      else
      {
        int ii = 0;
        int jj = 0;
        int placeholder = 0;
        boolean lookingForEndMark = true;

        while(ii < str.length())
        {
          if(str.charAt(ii) == (italicsMarker))
          {
            jj = ii; //need to save start mark place

            //need to find end of italics section
            while(lookingForEndMark)
            {
              ii++;
              if(str.charAt(ii) == (italicsMarker))
              {
                placeholder = ii;
                //break loop - found end mark at ii
                lookingForEndMark = false;
              }
              else if(str.length() - 1 == ii)
              {
                //break loop - misinput no end mark
                return str + "IT WAS A MISINPUT CALM DOWN IT WAS A MISINPUT!!!!";
              }
            }
            ii = placeholder;
            lookingForEndMark = true; //need to be true to loop again

            //have found both marks now - start altering string at this place
            str = str.substring(0, jj) + startTag + str.substring(jj + 1, ii) + endTag + str.substring(ii+1);
            ii = startTag.length() - 1 + endTag.length() - 1 + str.substring(jj + 1, ii).length();
          }

          ii++;
        }
      }

      String altered = str;
      return altered;
    }

    private static String convertBold(String str) {
        String altered = null;
        return altered;
    }

    private static String convertHeaders(String str) {
        String altered = null;
        return altered;
    }
}
