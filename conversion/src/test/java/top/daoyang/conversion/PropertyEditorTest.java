package top.daoyang.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

public class PropertyEditorTest {

    @Test
    public void propertyEditor() {

        PropertyEditor string2Property = new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                Properties properties = new Properties();
                try {
                    properties.load(new StringReader(text));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                setValue(properties);
            }
        };

        string2Property.setAsText("name=deyangye");

        Properties properties = (Properties) string2Property.getValue();

        Assertions.assertEquals("deyangye", properties.get("name"));
    }
}
