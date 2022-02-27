package edu.cs.tcu.tcustartalkproject.utils;

import edu.cs.tcu.tcustartalkproject.VocabWord.VocabWord;
import edu.cs.tcu.tcustartalkproject.VocabWord.VocabWordService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component        // Comment this line after running it once.
public class VocabWordInitializer implements CommandLineRunner {
    private final VocabWordService vocabWordService;

    @Autowired
    public VocabWordInitializer(VocabWordService vocabWordService) {
        this.vocabWordService = vocabWordService;
    }

    private void loadData() throws IOException {
        CCCEDictParser parser = new CCCEDictParser();
        File file = new File("cedict_ts.u8");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            if (!line.startsWith("#")){
                StringTokenizer st = new StringTokenizer(line, " ");
                CCCEDictParser.Entry entry = parser.getEntry(line);
                VocabWord vocabWord = new VocabWord();
                vocabWord.setId(entry.getSimplifiedChineseWord());
                vocabWord.setPinyin(entry.getMandarinPinyin());
                vocabWord.setDesc(entry.getEnglishDefinitions());
                vocabWordService.save(vocabWord);
            }
        }
    }

    @Override
    public void run(String... args) throws Exception {
        //this.loadData();
    }
}


class CCCEDictParser {
    private final String[] vowelAOE = {"āáǎàa", "ōóǒòo", "ēéěèe",
            "ĀÁǍÀA",// 愛爾蘭 爱尔兰 [Ai4 er3 lan2] /Ireland/
            "ŌÓǑÒO",// 噢運會 噢运会 [O1 yun4 hui4] /see 奧運會|奥运会[Ao4 yun4 hui4]/
            "ĒÉĚÈE" // 鄂爾多斯|鄂尔多斯[E4 er3 duo1 si1], Inner Mongolia/
    };
    private final String[] vowelIUV = {"īíǐìi", "ūúǔùu", "ǖǘǚǜü"};
    private final char[] toneArray = {'1', '2', '3', '4', '5'};
    private final String entryRegex = "^(.+) (.+) \\[(.+)\\] /(.*)/$";
    private final String pinyinRegex = "\\[(.+?)\\]";

    /**
     * Get the entry object from a line of CC-CEDICT format file.
     */
    public Entry getEntry(String line) {
        Matcher matcher = Pattern.compile(entryRegex).matcher(line);
        Entry entry = new Entry();
        while (matcher.find()) {
            for (int i = 0; i <= matcher.groupCount(); i++) {
                String gs = matcher.group(i);
                if (i == 1) {
                    entry.setTraditionalChineseWord(gs);
                } else if (i == 2) {
                    entry.setSimplifiedChineseWord(gs);
                } else if (i == 3) {
                    entry.setMandarinPinyin(arrayToString(convertPinyin(gs)));
                } else if (i == 4) {
                    List<String> list = new ArrayList<String>();
                    String[] eds = gs.split("/");
                    // convert Pinyin in the definition
                    for (String ed : eds) {
                        Map<String, String> map = new HashMap<String, String>();
                        Matcher pym = Pattern.compile(pinyinRegex).matcher(ed);
                        while (pym.find()) {
                            for (int j = 0; j <= pym.groupCount(); j++) {
                                String pyg = pym.group(j);
                                if (j > 0) {
                                    map.put(pyg, arrayToString(convertPinyin(pyg)));
                                }
                            }
                        }
                        for (String key : map.keySet()) {
                            ed = ed.replace(key, map.get(key));
                        }
                        list.add(ed);
                    }
                    entry.setEnglishDefinitions(String.join("\n", list));
                }
            }
        }
        return entry;
    }

    /**
     * Converts the CC-CEDICT pinyin to accented pinyin.
     * <pre>
     * First mark: a o e,
     * if not found then mark: i u ü,
     * if i and u exist at the same time, mark the last one.
     * </pre>
     *
     * @param pinyin e.g. tian1 r5 dong1 xian1 an4 nu:3 er2 liu2 hui1 ma ma5 ma3 r5 lu:2 yun2 Zhen1 · Ao4 si1 ting1
     * @return tiānr dōng xiān àn nǚ ér liú huī ma ma mǎr lǘ yún Zhēn · Ào sī tīng
     */
    public String[] convertPinyin(String pinyin) {
        // https://cc-cedict.org/wiki/format:syntax
        pinyin = pinyin.replace(" r5", "r").replace("u:", "ü");

        String[] pys = pinyin.split(" ");
        String[] rst = new String[pys.length];
        int rstI = -1;
        for (String py : pys) {
            char[] pyc = py.toCharArray();
            int pyToneI = -1;
            int toneI = -1;
            for (int i = 0; toneI == -1 && i < pyc.length; i++) {
                for (int j = 0; j < toneArray.length; j++) {
                    if (pyc[i] == toneArray[j]) {
                        pyToneI = i;
                        toneI = j;
                        break;
                    }
                }
            }
            boolean done = false;
            if (toneI != -1) {
                for (int i = 0; !done && i < pyc.length; i++) {
                    for (String aoe : vowelAOE) {
                        if (aoe.charAt(aoe.length() - 1) == pyc[i]) {
                            pyc[i] = aoe.charAt(toneI);
                            done = true;
                            break;
                        }
                    }
                }
                for (int i = pyc.length; !done && i > 0; i--) {
                    for (String iuv : vowelIUV) {
                        if (iuv.charAt(iuv.length() - 1) == pyc[i - 1]) {
                            pyc[i - 1] = iuv.charAt(toneI);
                            done = true;
                            break;
                        }
                    }
                }
                char[] dest = new char[pyc.length - 1];
                System.arraycopy(pyc, 0, dest, 0, pyToneI);
                System.arraycopy(pyc, pyToneI + 1, dest, pyToneI, pyc.length - (pyToneI + 1));
                rst[++rstI] = String.valueOf(dest);
            } else {
                rst[++rstI] = py;
            }
        }
        return rst;
    }

    /**
     * Array to string.
     *
     * @param arr e.g. ["a", "b", "c"]
     * @return a b c
     */
    public String arrayToString(String[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i != arr.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    /**
     * CC-CEDICT entry object.
     */
    public static class Entry {
        private String traditionalChineseWord;
        private String simplifiedChineseWord;
        private String mandarinPinyin;
        private String englishDefinitions;

        public String getTraditionalChineseWord() {
            return traditionalChineseWord;
        }

        public void setTraditionalChineseWord(String traditionalChineseWord) {
            this.traditionalChineseWord = traditionalChineseWord;
        }

        public String getSimplifiedChineseWord() {
            return simplifiedChineseWord;
        }

        public void setSimplifiedChineseWord(String simplifiedChineseWord) {
            this.simplifiedChineseWord = simplifiedChineseWord;
        }

        public String getMandarinPinyin() {
            return mandarinPinyin;
        }

        public void setMandarinPinyin(String mandarinPinyin) {
            this.mandarinPinyin = mandarinPinyin;
        }

        public String getEnglishDefinitions() {
            return englishDefinitions;
        }

        public void setEnglishDefinitions(String englishDefinitions) {
            this.englishDefinitions = englishDefinitions;
        }
    }
}
