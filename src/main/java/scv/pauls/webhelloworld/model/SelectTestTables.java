package scv.pauls.webhelloworld.model;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;

/**
 * Created by Pauls on 12.02.2017.
 */
public class SelectTestTables {

    private JdbcTemplate jdbcTemplate;

    public SelectTestTables(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public SelectTestTables() {
    }


    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private List<String> selectTable(String tableName) {
        List<Map<String, Object>> rows = new ArrayList<>();
        List<String> strings = new ArrayList<String>();
        strings.add("Select * from " + tableName + "<br>");
        String sqlQuery = "Select * from " + tableName;
        try {
            rows = jdbcTemplate.queryForList(sqlQuery);
            strings.add(rows.size() + " rows selected <br>");
            if (rows.size() > 0) {
                StringBuilder strb = new StringBuilder();
                for (Map.Entry<String, Object> entry : rows.get(0).entrySet()) strb.append(entry.getKey() + " | ");
                strb.append("<br>");
                strings.add(strb.toString() + "<br>");
                //int i = 1;
                //for (Map<String, Object> fields : rows) {
                for (int i = 0; i < rows.size(); i++) { //прошлись по записям
                    strb.setLength(0);
                    strb.append("rec N " + i + " ");
                    Iterator entries = rows.get(i).entrySet().iterator();
                    while (entries.hasNext()) { //прошлись по полям
                        Map.Entry thisEntry = (Map.Entry) entries.next();
                        Object value = thisEntry.getValue();
                        if (value != null)
                            strb.append(value.toString() + " | ");
                        else
                            strb.append(" null | ");
                    }
                    strings.add(strb.toString() + "<br>");
                }
            } else strings.add("Table is empty!");
        } catch (Exception e) {
            strings.add(e.getMessage() + "<br>");
        } finally {
            return strings;
        }

    }

    public String getRowsAsString(String tableName) {
        StringBuilder sb = new StringBuilder();
        List<String> strings = selectTable(tableName);
        for (String s : strings) {
            sb.append(s);
        }
        return sb.toString();

    }
}
