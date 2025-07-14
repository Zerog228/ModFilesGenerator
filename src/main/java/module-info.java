module zerog.modfilesgenerator {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.apache.commons.text;
    requires org.apache.commons.lang3;
    requires org.apache.commons.io;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires spring.core;
    requires spring.jcl;

    exports zerog.modfilesgenerator;
    exports zerog.modfilesgenerator.enums;
    exports zerog.modfilesgenerator.annotations;
}