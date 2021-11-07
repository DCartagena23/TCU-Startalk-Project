package edu.cs.tcu.tcustartalkproject.utils;
import com.github.houbb.segment.support.segment.result.impl.SegmentResultHandlers;
import com.github.houbb.segment.util.SegmentHelper;
import edu.cs.tcu.tcustartalkproject.Chapter.Chapter;
import edu.cs.tcu.tcustartalkproject.utils.Result;
import edu.cs.tcu.tcustartalkproject.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.github.houbb.segment.support.segment.result.impl.SegmentResultHandlers;
import com.github.houbb.segment.util.SegmentHelper;
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

}
