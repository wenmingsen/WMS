<#assign className = table.className>  
<#assign classNameLower = table.className?uncap_first>
package ${basepackage}.mapper;

import java.util.List;

import ${basepackage}.entity.${className};

/**
 * ${className}Mapper ${table.remarks}
 *
 * @author  ${author}
 * @since   ${since}
 * @version ${now?string('yyyy年MM月dd日')} ${author}
 */
public interface ${className}Mapper{
	
	/**
	 * 保存
	 * 
	 * @param ${classNameLower} ${table.remarks}
	 */
	void insert(${className} ${classNameLower});

	/**
	 * 批量保存
	 * 
	 * @param ${classNameLower} ${table.remarks}
	 */
	void insertList(List<${className}> ${classNameLower}List);

	/**
	 * 通过主键删除单条记录
	 * 
	 * @param <#list table.columns as column><#if !column.isNotIdOrVersionField>${column.columnNameLower} ${table.remarks} 主键ID</#if></#list>
	 */
	void delete(String <#list table.columns as column><#if !column.isNotIdOrVersionField>${column.columnNameLower}</#if></#list>);
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param ${classNameLower} ${table.remarks}
	 */
	void update(${className} ${classNameLower});

	/**
	 * 更新非空字段
	 * <p>通过主键更新记录</p>
	 * 
	 * @param ${classNameLower} ${table.remarks}
	 */
	void updateIfNotNull(${className} ${classNameLower});
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param <#list table.columns as column><#if !column.isNotIdOrVersionField>${column.columnNameLower} ${table.remarks} 主键ID</#if></#list>
	 * @return ${table.remarks} 单条记录
	 */
	${className} selectByPrimaryKey(String <#list table.columns as column><#if !column.isNotIdOrVersionField>${column.columnNameLower}</#if></#list>);

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param ${classNameLower} ${table.remarks}
	 * @return ${table.remarks} 记录集
	 */
	List<${className}> selectList(${className} ${classNameLower});

}