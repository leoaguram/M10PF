package com.dental_flamingos.converter;

import java.beans.PropertyEditorSupport;

public class MayusculasConverter extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(text.toUpperCase());
    }
}
