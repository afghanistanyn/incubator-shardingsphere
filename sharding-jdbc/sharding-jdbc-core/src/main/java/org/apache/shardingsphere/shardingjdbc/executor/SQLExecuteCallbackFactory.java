/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.shardingjdbc.executor;

import org.apache.shardingsphere.core.constant.DatabaseType;
import org.apache.shardingsphere.core.executor.StatementExecuteUnit;
import org.apache.shardingsphere.core.executor.sql.execute.SQLExecuteCallback;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * SQL execute callback factory.
 *
 * @author yangyi
 */
public final class SQLExecuteCallbackFactory {
    
    /**
     * Get update callback.
     *
     * @param databaseType database type
     * @param isExceptionThrown is exception thrown
     * @return update callback
     */
    public static SQLExecuteCallback<Integer> getPreparedUpdateSQLExecuteCallback(final DatabaseType databaseType, final boolean isExceptionThrown) {
        return new SQLExecuteCallback<Integer>(databaseType, isExceptionThrown) {
            
            @Override
            protected Integer executeSQL(final StatementExecuteUnit statementExecuteUnit) throws SQLException {
                return ((PreparedStatement) statementExecuteUnit.getStatement()).executeUpdate();
            }
        };
    }
    
    /**
     * Get execute callback.
     *
     * @param databaseType database type
     * @param isExceptionThrown is exception thrown
     * @return execute callback
     */
    public static SQLExecuteCallback<Boolean> getPreparedSQLExecuteCallback(final DatabaseType databaseType, final boolean isExceptionThrown) {
        return new SQLExecuteCallback<Boolean>(databaseType, isExceptionThrown) {
            
            @Override
            protected Boolean executeSQL(final StatementExecuteUnit statementExecuteUnit) throws SQLException {
                return ((PreparedStatement) statementExecuteUnit.getStatement()).execute();
            }
        };
    }
}
