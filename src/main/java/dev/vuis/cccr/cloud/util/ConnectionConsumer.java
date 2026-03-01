package dev.vuis.cccr.cloud.util;

import java.sql.Connection;
import java.sql.SQLException;

@FunctionalInterface
public interface ConnectionConsumer {
	void accept(Connection connection) throws SQLException;
}
