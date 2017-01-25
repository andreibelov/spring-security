package edu.teldir.security.dao.impl;

import edu.teldir.security.dao.SecurityObjectDao;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 15.04.12
 */
public class SecurityObjectDaoImpl extends SimpleJdbcDaoSupport implements SecurityObjectDao {
    public Collection<String> getRoles() {
        return getSimpleJdbcTemplate()
                .query("select distinct authority from authorities order by authority", new StringRowMapper());
    }

    public Collection<String> getUsernames() {
        return getSimpleJdbcTemplate()
                .query("select username from users where enabled = 1 order by username", new StringRowMapper());
    }

    private class StringRowMapper implements RowMapper<String> {
        public String mapRow(ResultSet rs, int i) throws SQLException {
            return rs.getString(1);
        }
    }
}
