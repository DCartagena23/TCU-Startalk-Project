package edu.cs.tcu.tcustartalkproject.utils;
import edu.cs.tcu.tcustartalkproject.Chapter.Chapter;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class TokenizerController {


    @PostMapping("/tokenize")
    @ResponseBody
    public Result tokenize2(@RequestBody Chapter chapter){
        List<String> textList = chapter.getText();
        String text = "";
        for (String s : textList) {
            text = text + s;
        }
        org.ansj.domain.Result result = ToAnalysis.parse(text);
        List<Term> terms = result.getTerms();
        List<String> str = new ArrayList<String>();
        if (terms != null && terms.size() != 0) {
            Iterator<Term> iterator = terms.iterator();

            while(iterator.hasNext()) {
                String tmp = ((Term)iterator.next()).getRealName();
                if (!tmp.equals(" ")){

                    str.add(tmp);
                }
            }
            chapter.setText(str);
        }
        return new Result(StatusCode.SUCCESS, "Tokenize 2 Success", str);
    }

}