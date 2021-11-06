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

@Controller
public class TokenizerController {

    @PostMapping("/tokenize")
    @ResponseBody
    public Result tokenize(@RequestBody Chapter chapter) {
        List<String> resultList = SegmentHelper.segment(chapter.getText(), SegmentResultHandlers.word());
        ListIterator<String> resultIterator = resultList.listIterator();
        String str = "";
        while (resultIterator.hasNext()) {
            str = str+ " " +resultIterator.next();
        }
        chapter.setText(str);
        return new Result(StatusCode.SUCCESS, "Tokenize Success", chapter);
    }

}
