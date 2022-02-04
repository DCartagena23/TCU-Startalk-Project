package edu.cs.tcu.tcustartalkproject.utils;

import edu.cs.tcu.tcustartalkproject.Chapter.Chapter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Controller
public class TransferController {
    @PostMapping("/transfer")
    @ResponseBody
    public Result audio(@RequestBody Chapter chapter) throws IOException {
        List<String> data = chapter.getText();
        String user = data.get(0);
        String test = data.get(1);
        String base64Audio = data.get(2);
        System.out.println(base64Audio);
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decodedByte = decoder.decode(base64Audio.split(",")[1]);
        FileOutputStream fos = new FileOutputStream("./src/main/java/temp/"+user+test+".wav");
        fos.write(decodedByte);
        fos.close();
        return new Result(StatusCode.SUCCESS, "Get Chapter Success", null);
    }
}