package com.dianping.cip.mybatisgen.generator.plugins;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;

/**
 * 
 * @author mobangwei
 * 
 */
public class RenameMethodNamePlugin extends PluginAdapter {

    private static final String EXAMPLE_DEFAULT = "Example";

    /**
         * 
         */
    public RenameMethodNamePlugin() {}

    @Override
    public void initialized(IntrospectedTable introspectedTable) {
        if (introspectedTable.hasPrimaryKeyColumns()) {
            List<IntrospectedColumn> primaryKeyColumns = introspectedTable.getPrimaryKeyColumns();
            IntrospectedColumn column = primaryKeyColumns.get(0);
            String pkAlias = properties.getProperty("pkAlias");
            Boolean isPkAlias = StringUtils.isNotBlank(pkAlias) && Boolean.valueOf(pkAlias);
            String PK_NAME = isPkAlias ? StringUtils.capitalize(column.getActualColumnName()) : "PrimaryKey";
            String exampleAlias = (String) properties.get("exampleAlias");
            String EXAMPLE_NAME = StringUtils.isBlank(exampleAlias) ? EXAMPLE_DEFAULT : exampleAlias;
            introspectedTable.getSelectByExampleStatementId();
            introspectedTable.setCountByExampleStatementId("countBy" + EXAMPLE_NAME); //$NON-NLS-1$
            introspectedTable.setDeleteByExampleStatementId("deleteBy" + EXAMPLE_NAME); //$NON-NLS-1$
            introspectedTable.setDeleteByPrimaryKeyStatementId("deleteBy" + PK_NAME); //$NON-NLS-1$
            introspectedTable.setInsertStatementId("insert"); //$NON-NLS-1$
            introspectedTable.setInsertSelectiveStatementId("insertSelective"); //$NON-NLS-1$
            introspectedTable.setSelectAllStatementId("selectAll"); //$NON-NLS-1$
            introspectedTable.setSelectByExampleStatementId("selectBy" + EXAMPLE_NAME); //$NON-NLS-1$
            introspectedTable.setSelectByExampleWithBLOBsStatementId("selectBy" + EXAMPLE_NAME + "WithBLOBs"); //$NON-NLS-1$
            introspectedTable.setSelectByPrimaryKeyStatementId("selectBy" + PK_NAME); //$NON-NLS-1$
            introspectedTable.setUpdateByExampleStatementId("updateBy" + EXAMPLE_NAME); //$NON-NLS-1$
            introspectedTable.setUpdateByExampleSelectiveStatementId("updateBy" + EXAMPLE_NAME + "Selective"); //$NON-NLS-1$
            introspectedTable.setUpdateByExampleWithBLOBsStatementId("updateBy" + EXAMPLE_NAME + "WithBLOBs"); //$NON-NLS-1$
            introspectedTable.setUpdateByPrimaryKeyStatementId("updateBy" + PK_NAME); //$NON-NLS-1$
            introspectedTable.setUpdateByPrimaryKeySelectiveStatementId("updateBy" + PK_NAME + "Selective"); //$NON-NLS-1$
            introspectedTable.setUpdateByPrimaryKeyWithBLOBsStatementId("updateBy" + PK_NAME + "WithBLOBs"); //$NON-NLS-1$
            introspectedTable.setBaseResultMapId("BaseResultMap"); //$NON-NLS-1$
            introspectedTable.setResultMapWithBLOBsId("ResultMapWithBLOBs"); //$NON-NLS-1$
            introspectedTable.setExampleWhereClauseId("common.Example_Where_Clause"); //$NON-NLS-1$
            introspectedTable.setBaseColumnListId("Base_Column_List"); //$NON-NLS-1$
            introspectedTable.setBlobColumnListId("Blob_Column_List"); //$NON-NLS-1$
            introspectedTable.setMyBatis3UpdateByExampleWhereClauseId("common.Update_By_Example_Where_Clause"); //$NON-NLS-1$
            if (!EXAMPLE_NAME.equals(EXAMPLE_DEFAULT)) {
                introspectedTable.setExampleType(introspectedTable.getExampleType().replaceAll(EXAMPLE_DEFAULT, EXAMPLE_NAME));
            }
        }
    }

    @Override
    public boolean validate(List<String> warnings) {
        // TODO Auto-generated method stub
        return true;
    }
}
