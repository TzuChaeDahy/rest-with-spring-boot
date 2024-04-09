package br.com.tzuchaedahy.restwithspringboot.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

import static br.com.tzuchaedahy.restwithspringboot.util.MediaType.APPLICATION_YAML;

public class YamlJackson2HttpMessageConverter extends AbstractJackson2HttpMessageConverter {
    public YamlJackson2HttpMessageConverter() {
        super(new YAMLMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL), MediaType.parseMediaType(APPLICATION_YAML));
    }
}
