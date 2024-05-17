package org.example.parser;
// NO COMMENTS ITS JUST PURE MAGIC
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.table.DefaultTableModel;
import java.io.IOException;

public class HTMLParser {

    public DefaultTableModel parseURL(String url) throws IOException {
        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Tag", "Attribute", "Content"}, 0);

        Document document = Jsoup.connect(url).get();

        // Extract and add <title>
        String title = document.title();
        tableModel.addRow(new Object[]{"title", "", title});

        // Extract and add <meta>
        Elements metas = document.select("meta");
        for (Element meta : metas) {
            String name = meta.hasAttr("name") ? meta.attr("name") : meta.attr("property");
            String content = meta.attr("content");
            tableModel.addRow(new Object[]{"meta", name, content});
        }

        // Extract and add <img>
        Elements images = document.select("img");
        for (Element img : images) {
            String src = img.attr("src");
            String alt = img.attr("alt");
            tableModel.addRow(new Object[]{"img", "src", src});
            tableModel.addRow(new Object[]{"img", "alt", alt});
        }

        // Extract and add <link>
        Elements links = document.select("link");
        for (Element link : links) {
            String rel = link.attr("rel");
            String href = link.attr("href");
            if ("canonical".equals(rel)) {
                tableModel.addRow(new Object[]{"link", "rel=canonical", href});
            } else {
                tableModel.addRow(new Object[]{"link", rel, href});
            }
        }

        // Extract and add <a>
        Elements anchors = document.select("a");
        for (Element anchor : anchors) {
            String href = anchor.attr("href");
            String text = anchor.text();
            tableModel.addRow(new Object[]{"a", "href", href});
            tableModel.addRow(new Object[]{"a", "text", text});
        }

        // Extract and add headings (h1, h2, h3, h4, h5, h6)
        Elements headings = document.select("h1, h2, h3, h4, h5, h6");
        for (Element heading : headings) {
            String tagName = heading.tagName();
            String content = heading.text();
            tableModel.addRow(new Object[]{tagName, "", content});
        }

        return tableModel;
    }
}
