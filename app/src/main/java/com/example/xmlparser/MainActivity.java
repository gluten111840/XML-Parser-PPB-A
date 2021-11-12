package com.example.xmlparser;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {
    TextView tv1;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1=findViewById(R.id.textView1);

        try {
            InputStream is = getAssets().open("file.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);

            Element element=doc.getDocumentElement();
            element.normalize();
            NodeList nList = doc.getElementsByTagName("gempa");

            for (int i=0; i<nList.getLength(); i++) {

                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element2 = (Element) node;
                    tv1.setText(tv1.getText()+"\nTanggal : " + getValue("Tanggal", element2)+"\n");
                    tv1.setText(tv1.getText()+"Jam : " + getValue("Jam", element2)+"\n");
                    tv1.setText(tv1.getText()+"Koordinat : " + getValue("coordinates", element2)+"\n");
                    tv1.setText(tv1.getText()+"Magnitude : " + getValue("Magnitude", element2)+"\n");
                    tv1.setText(tv1.getText()+"Kedalaman : " + getValue("Kedalaman", element2)+"\n");
                    tv1.setText(tv1.getText()+"Wilayah : " + getValue("Wilayah", element2)+"\n");
                    tv1.setText(tv1.getText()+"Potensi : " + getValue("Potensi", element2)+"\n");
                    tv1.setText(tv1.getText()+"-----------------------");
                }
            }

        } catch (Exception e) {e.printStackTrace();}

    }
    private static String getValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }
}
