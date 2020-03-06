package dj.mybatis.comfig;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by dj on 2020/2/23.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        DataSourceType.DataBaseType dataBaseType = DataSourceType.getDataBaseType();
        return dataBaseType;
    }

}