package com.lama.loanmanagementsystem.model;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDeserializer extends JsonDeserializer<Date> {
    private static final String[] Supported_formats ={"yyyy-MM-dd","dd-MM-yyyy","dd/MM/yyyy","dd.MM.yyyy"};
    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String datestring = jsonParser.getText();
        for(String format : Supported_formats){
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat(format);
                return dateFormat.parse(datestring);
            }
            catch (ParseException e){

            }
        }
        throw new IOException("unsupported date format:"+datestring);
    }
}
