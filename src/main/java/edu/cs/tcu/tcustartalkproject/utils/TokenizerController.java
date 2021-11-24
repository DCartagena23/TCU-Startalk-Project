package edu.cs.tcu.tcustartalkproject.utils;
import edu.cs.tcu.tcustartalkproject.Chapter.Chapter;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class TokenizerController {
    /**
     * Method to tokenize text in a chapter
     * @param chapter Chapter object
     * @return Result object that contains status code, message, and tokens
     */
    @PostMapping("/tokenize")
    @ResponseBody
    public Result tokenize2(@RequestBody Chapter chapter){
        // Get Text
        List<String> textList = chapter.getText();
        String text = "";
        // Concat texts into one large string
        for (String s : textList) {
            text = text + s;
        }
        // Parsing Text
        org.ansj.domain.Result result = ToAnalysis.parse(text);
        List<Term> terms = result.getTerms();
        List<String> str = new ArrayList<String>();
        if (terms != null && terms.size() != 0) {
            // Iterate through terms if terms not null
            Iterator<Term> iterator = terms.iterator();

            while(iterator.hasNext()) {
                String tmp = ((Term)iterator.next()).getRealName();
                // Concat term if it is not equal to ' '
                if (!tmp.equals(" ")){

                    str.add(tmp);
                }
            }
            // Chapter set text
            chapter.setText(str);
        }
        return new Result(StatusCode.SUCCESS, "Tokenize Success", str);
    }
}