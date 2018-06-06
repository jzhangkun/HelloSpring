package com.kun.hello.util;

import com.kun.hello.dao.jpa.MyQuery;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryUtil {

    public static final Pattern DOT_ONLY = Pattern.compile("\\.");
    public static final Pattern STAR_ONLY = Pattern.compile("\\*");
    public static final Pattern DOT_AND_STAT = Pattern.compile("\\.\\*");
    public static final Pattern DOT_OR_STAT = Pattern.compile("[.*]");


    public static void convertQueryWithParam(Class clazz, EntityManager em, Map<String, String[]> param) {
        MyQuery query = MyQuery.forClass(clazz, em);
        if (param.containsKey("_or")) {
            param.remove("_or");
        } else {
            for (Map.Entry<String, String[]> entry: param.entrySet()) {
                String key = entry.getKey();
                String[] values = entry.getValue();
                for (int i = 0; i < values.length; i++) {
                    //if (values[i].indexOf(".") == -1 && values[i].indexOf("*") == -1) {
                    if (!DOT_OR_STAT.matcher(values[i]).find()) {
                        query.eq(key, values[i]);
                        System.out.println(key + "=" + values[i]);
                    } else {
                        query.like(key, convertRegexp(values[i]));
                        System.out.println(key + "~" + convertRegexp(values[i]));
                    }
                }
            }
        }
        System.out.println(query.getPredicates().size());
        CriteriaQuery criteriaQuery = query.newCriteriaQuery();
        System.out.println(criteriaQuery.getRestriction().getExpressions().toString());
    }

    public static String convertRegexp(String string) {
        Matcher dotStartMatcher = DOT_AND_STAT.matcher(string);
        if (dotStartMatcher.find()) {
            string = dotStartMatcher.replaceAll("%");
        }
        Matcher dotOnlyMatcher = DOT_ONLY.matcher(string);
        if (dotOnlyMatcher.find()) {
            string = dotOnlyMatcher.replaceAll("_");
        }
        Matcher starOnlyMatcher = STAR_ONLY.matcher(string);
        if (starOnlyMatcher.find()) {
            string = starOnlyMatcher.replaceAll("%");
        }
        return string;
    }

    public static String convertRegexp2(String string) {
        if (string.matches("\\.\\*")) {
            string.replaceAll("\\.\\*", "%");
        }
        if (string.indexOf(".") != -1) {
            string.replace(".", "_");
        }
        if (string.indexOf("*") != -1) {
            string.replace("*", "%");
        }
        return string;
    }

    public static void main (String... strings) {
        String string = "abc.*ef..*";
        String replace = convertRegexp(string);

        System.out.println("replaced = " + replace);

        Matcher dotOrStarMatcher = DOT_OR_STAT.matcher(string);
        if (dotOrStarMatcher.find()) {
            System.out.println("find matched");
        }
    }
}
