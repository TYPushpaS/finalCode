package com.tyss.talenthunt.dto;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.stereotype.Component;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

@Component
public class CustomIdGenerator implements IdentifierGenerator {

	static RequirmentBean addreq;
	
	private final String DEFAULT_SEQUENCE_NAME = "hibernate_sequence";
	Scanner sc = new Scanner(System.in);
	Serializable result = null;
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	
	public boolean generator(RequirmentBean req) {
		addreq=req;
		return true;
	}
	
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		try {
			connection=(Connection) session.connection();
			statement = (Statement) connection.createStatement();
			try {
				int next_val = 100;
				statement.executeUpdate("UPDATE " + DEFAULT_SEQUENCE_NAME + " SET next_val=next_val+1");
				resultSet = statement.executeQuery("SELECT next_val FROM  " + DEFAULT_SEQUENCE_NAME);
			} catch (Exception e) {

				System.out.println("In catch, cause : Table is not available.");

				statement.execute("CREATE table " + DEFAULT_SEQUENCE_NAME + " (next_val INT NOT NULL)");
				statement.executeUpdate("INSERT INTO " + DEFAULT_SEQUENCE_NAME + " VALUES(0)");
				statement.executeUpdate("UPDATE " + DEFAULT_SEQUENCE_NAME + " SET next_val=LAST_INSERT_ID(next_val+1)");
				resultSet = statement.executeQuery("SELECT next_val FROM  " + DEFAULT_SEQUENCE_NAME);
			}
			if (resultSet.next()) {

				System.out.println(resultSet.getInt(1));
				String client = addreq.getClient().replaceAll("\\s+", "");
				String technology = addreq.getTechnology().replaceAll("\\s+", "");
				//double yoe=addreq.getYoe().replaceAll("\\s+","");
				
				String prefix = "TY_" + client +"_"+technology +"_";
				int nextValue = resultSet.getInt(1);
				String suffix = String.format("%04d", nextValue);
				result = prefix.concat(suffix);
				System.out.println("Custom generated sequence is : " + result);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
