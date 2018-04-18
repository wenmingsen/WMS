<#assign className = table.className>  
<#assign classNameLower = table.className?uncap_first>
package ${basepackage}.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ${basepackage}.entity.${className};
import ${basepackage}.mapper.${className}Mapper;

/**
 * ${className}Service ${table.remarks}
 *
 * @author  ${author}
 * @since   ${since}
 * @version ${now?string('yyyy年MM月dd日')} ${author}
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class ${className}Service{
	
	/**${table.remarks}${classNameLower}Mapper接口*/
	@Autowired
	private ${className}Mapper ${classNameLower}Mapper;

	/**
	 * 保存
	 * 
	 * @param ${classNameLower} ${table.remarks}
	 */
	public void insert(${className} ${classNameLower}) throws Exception{
		${classNameLower}Mapper.insert(${classNameLower});
	}

	/**
	 * 批量保存
	 * 
	 * @param ${classNameLower} ${table.remarks}
	 */
	public void insertList(List<${className}> ${classNameLower}List) throws Exception{
		${classNameLower}Mapper.insertList(${classNameLower}List);
	}

	/**
	 * 通过主键删除单条记录
	 * 
	 * @param <#list table.columns as column><#if !column.isNotIdOrVersionField>${column.columnNameLower} ${table.remarks} 主键ID</#if></#list>
	 * @throws Exception 出错抛出异常
	 */
	public void delete(String <#list table.columns as column><#if !column.isNotIdOrVersionField>${column.columnNameLower}</#if></#list>) throws Exception{
		${classNameLower}Mapper.delete(<#list table.columns as column><#if !column.isNotIdOrVersionField>${column.columnNameLower}</#if></#list>);
	}
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param ${classNameLower} ${table.remarks}
	 * @throws Exception 出错抛出异常
	 */
	public void update(${className} ${classNameLower}) throws Exception{
		${classNameLower}Mapper.update(${classNameLower});
	}

	/**
	 * 更新非空字段
	 * <p>通过主键更新记录</p>
	 * 
	 * @param ${classNameLower} ${table.remarks}
	 * @throws Exception 出错抛出异常
	 */
	public void updateIfNotNull(${className} ${classNameLower}) throws Exception{
		${classNameLower}Mapper.updateIfNotNull(${classNameLower});
	}
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param <#list table.columns as column><#if !column.isNotIdOrVersionField>${column.columnNameLower} ${table.remarks} 主键ID</#if></#list>
	 * @return ${table.remarks} 单条记录
	 */
	public ${className} selectByPrimaryKey(String <#list table.columns as column><#if !column.isNotIdOrVersionField>${column.columnNameLower}</#if></#list>){
		return ${classNameLower}Mapper.selectByPrimaryKey(<#list table.columns as column><#if !column.isNotIdOrVersionField>${column.columnNameLower}</#if></#list>);
	}

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param ${classNameLower} ${table.remarks}
	 */
	public List<${className}> selectList(${className} ${classNameLower}){
		return ${classNameLower}Mapper.selectList(${classNameLower});
	}

}