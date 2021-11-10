package edu.cs.tcu.tcustartalkproject.utils;
import com.github.houbb.segment.support.segment.result.impl.SegmentResultHandlers;
import com.github.houbb.segment.util.SegmentHelper;
import edu.cs.tcu.tcustartalkproject.Chapter.Chapter;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.StringTokenizer;

@Controller
public class TokenizerController {

    @PostMapping("/tokenize")
    @ResponseBody
    public Result tokenize(@RequestBody Chapter chapter) {
        StringTokenizer st = new StringTokenizer(chapter.getText(),"\n");
        String finalString = "";
        String temp = "";
        while (st.hasMoreTokens()){
            List<String> resultList = SegmentHelper.segment(st.nextToken().replaceAll("\\s", ""), SegmentResultHandlers.word());
            ListIterator<String> resultIterator = resultList.listIterator();
            String str = "";
            while (resultIterator.hasNext()) {
                temp = resultIterator.next();
                if (!resultIterator.hasNext()){
                    str = str + temp;
                }
                else str = str+temp+ " ";
            }
            if (!st.hasMoreTokens()){
                finalString = finalString + str;
            }
            else finalString = finalString + str + "\n";
        }
        chapter.setText(finalString);
        return new Result(StatusCode.SUCCESS, "Tokenize Success", chapter);
    }

    @PostMapping("/tokenize2")
    @ResponseBody
    public Result tokenize2(@RequestBody Chapter chapter){
        String text = chapter.getText();
        org.ansj.domain.Result result = ToAnalysis.parse(text);
        List<Term> terms = result.getTerms();

        if (terms != null && terms.size() != 0) {
            Iterator<Term> iterator = terms.iterator();
            StringBuilder sb = new StringBuilder(((Term)iterator.next()).getRealName());

            while(iterator.hasNext()) {
                String tmp = ((Term)iterator.next()).getRealName();
                if (!tmp.equals(" ")){
                    sb.append(',');
                    sb.append(tmp);
                }
            }
            chapter.setText(sb.toString());
        }
        return new Result(StatusCode.SUCCESS, "Tokenize 2 Success", chapter);
    }

}