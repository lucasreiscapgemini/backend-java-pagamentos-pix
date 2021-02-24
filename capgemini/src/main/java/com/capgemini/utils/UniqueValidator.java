package com.capgemini.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

import javax.sql.DataSource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.capgemini.exception.CapgeminiException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

public class UniqueValidator implements ConstraintValidator<Unique, Object> {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private MessageSource messageSource;

	private String table;
	private String column;

	@Override
	public void initialize(Unique constraint) {
		this.table = constraint.table();
		this.column = constraint.column();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		try (Connection con = this.dataSource.getConnection()) {
			PreparedStatement stmt = con
					.prepareStatement("select null from " + this.table + " where " + this.column + " = ?;");
			stmt.setObject(1, value);
			ResultSet rs = stmt.executeQuery();
			return !rs.next();
		} catch (SQLException e) {
			String message = this.messageSource.getMessage("unique.sql.error", new Object[] { this.column, this.table },
					Locale.getDefault());
            CapgeminiException.handleInternalException(message);
			return false;
		}
	}
}