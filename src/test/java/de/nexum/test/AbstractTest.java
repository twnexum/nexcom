package de.nexum.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.commons.dbutils.DbUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author Thomas Weckert
 */
public abstract class AbstractTest implements ApplicationContextAware {
	
	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	protected void deleteDatabase() throws Exception {
		
		Connection conn = null;
		Statement stmt = null;
		try {
			
			DataSource dataSource = (DataSource) applicationContext.getBean("dataSource");
			conn = dataSource.getConnection();
			conn.setAutoCommit(true);
			
			stmt = conn.createStatement();
			stmt.execute("DELETE FROM PRODUCTS");
			stmt.execute("DELETE FROM PRICES");
			stmt.execute("DELETE FROM ATTRIBUTES");
			stmt.execute("DELETE FROM INVENTORIES");
			stmt.close();
		} catch (SQLException e) {
			throw new Exception(e);
		} finally {
			DbUtils.closeQuietly(stmt);
			DbUtils.closeQuietly(conn);
		}
	}
	
}
