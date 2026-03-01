package dev.vuis.cccr.cloud.util;

import java.sql.Connection;
import java.sql.SQLException;

@FunctionalInterface
public interface ConnectionFunction<R> {
	R apply(Connection connection) throws SQLException;
}
