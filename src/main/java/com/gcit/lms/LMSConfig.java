package com.gcit.lms;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.gcit.lms.dao.*;
import com.gcit.lms.service.*;

@Configuration
public class LMSConfig {
	public String driver = "com.mysql.jdbc.Driver";
	public String url = "jdbc:mysql://gcit.ceeyo6nqjosk.us-east-2.rds.amazonaws.com:3306/library";
	public String username = "gcit";
	public String password = "gcittraining$";
	
	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(driver);
		ds.setUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		
		return ds;
	}
	
	@Bean
	public JdbcTemplate template() {
		return new JdbcTemplate(dataSource());
	}
	
	@Bean
	public PlatformTransactionManager txManager() {
		DataSourceTransactionManager dsManager = new DataSourceTransactionManager();
		dsManager.setDataSource(dataSource());
		return dsManager;
	}
	
	@Bean
	public AuthorDAO adao()	{
		return new AuthorDAO();
	}
	
	@Bean
	public BookDAO bdao()	{
		return new BookDAO();
	}
	
	@Bean
	public BranchDAO branchDao()	{
		return new BranchDAO();
	}
	
	@Bean
	public CopiesDAO cdao()	{
		return new CopiesDAO();
	}
	
	@Bean
	public GenreDAO gdao()	{
		return new GenreDAO();
	}
	
	@Bean
	public LoanDAO ldao()	{
		return new LoanDAO();
	}
	
	@Bean
	public PublisherDAO pdao()	{
		return new PublisherDAO();
	}
	
	@Bean
	public BorrowerDAO borrowerDao()	{
		return new BorrowerDAO();
	}
}
