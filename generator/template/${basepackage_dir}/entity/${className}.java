<#assign className = table.className>  
package ${basepackage}.entity;

/**
 * ${table.sqlName}-->${className} ${table.remarks}
 *
 * @author  ${author}
 * @since   ${since}
 * @version ${now?string('yyyy年MM月dd日')} ${author}
 */
public class ${className}{
	
	<#--/** 序列化版本ID */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	-->
<#list table.columns as column>
	<#--<#if column.columnNameLower != 'serialVersionUID' 
	&& column.columnNameLower != 'id' && column.columnNameLower != 'creatorId' 
	&& column.columnNameLower != 'creator' && column.columnNameLower != 'modifierId' 
	&& column.columnNameLower != 'modifier' && column.columnNameLower != 'isDeleted' 
	&& column.columnNameLower != 'createTime' && column.columnNameLower != 'modifyTime' >
	<#if column.isNotIdOrVersionField>
	/** ${column.remarks} */
	@Column(name = "${column.sqlName}")
	private ${column.simpleJavaType} ${column.columnNameLower};
	
	</#if>
	</#if>-->
	/** ${column.remarks} */
	private ${column.simpleJavaType} ${column.columnNameLower};

</#list>
	<#--
	public ${className}() {
		super();
	}
	
	
	public ${className}(<#list table.columns as column><#if column.columnNameLower != 'serialVersionUID'>${column.simpleJavaType} ${column.columnNameLower}<#if column_has_next>,</#if></#if></#list>) {
		super();
		<#list table.columns as column>
		<#if column.columnNameLower != 'serialVersionUID'>
		this.${column.columnNameLower} = ${column.columnNameLower};
		</#if>
		</#list>
	}
	-->
	<@generateJavaColumns/>
	
}
	

	
<#macro generateJavaColumns>
<#list table.columns as column>
	<#--<#if column.columnNameLower != 'serialVersionUID' 
	&& column.columnNameLower != 'id' && column.columnNameLower != 'creatorId' 
	&& column.columnNameLower != 'creator' && column.columnNameLower != 'modifierId' 
	&& column.columnNameLower != 'modifier' && column.columnNameLower != 'isDeleted' 
	&& column.columnNameLower != 'createTime' && column.columnNameLower != 'modifyTime' >
	</#if>-->
	/**
	 * ${column.remarks}
	 * @param ${column.columnNameLower} 设置 ${column.columnNameLower} 属性值为参数值 ${column.columnNameLower}
	 */
	public void set${column.columnName}(${column.simpleJavaType} ${column.columnNameLower}) {
	<#if column.simpleJavaType=="String">
		this.${column.columnNameLower} = ${column.columnNameLower} == null ? null : ${column.columnNameLower}.trim();
		<#else>
		this.${column.columnNameLower} = ${column.columnNameLower};
	</#if>	
	}
	/**
	 * ${column.remarks}
     * @return 获取${column.columnNameLower}属性值
     */
	public ${column.simpleJavaType} get${column.columnName}() {
		return this.${column.columnNameLower};	
	}
	
</#list>
</#macro>