package webrequest;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.net.URL;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebChapterRequest {
    public String ChapterRequest(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        URLConnection connection = url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String content;
        StringBuilder html = new StringBuilder();

        while ((content = reader.readLine()) != null) {
            html.append(content);
        }
        reader.close();
        System.out.println(html);
        return regex(html);

    }

    private String regex(StringBuilder html) {
        Pattern p = Pattern.compile("(https:\\\\/\\\\/)+(\\S)+(.jpg)");
        Matcher m = p.matcher(html);

        if (m.find()) {
            return m.group().replace("\\", "");
        }

        p = Pattern.compile("(https:\\\\/\\\\/)+(\\S)+(.png)");
        m = p.matcher(html);
        if (m.find()) {
            return m.group().replace("\\", "");
        }

        return "not found";
    }
}
