package br.com.darioklein.ecopass.utils;

import java.text.Normalizer;

public class StringUtils {

    public static String normalize(String s) {
        return Normalizer.normalize(s, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").toLowerCase();
    }
}
