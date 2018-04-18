<#assign className = table.className> 
<#assign classNameLower = table.className?uncap_first>   
package ${basepackage}.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ${basepackage}.service.${className}Service;


/**
 * ${className} ${table.remarks}
 *
 * @author  ${author}
 * @since   ${since}
 * @version ${now?string('yyyy年MM月dd日')} ${author}
 */
@RestController
@RequestMapping("/api/v1/${classNameLower}/*")
public class ${className}Controller{

	/** ${classNameLower}Service ${table.remarks} */
	@Autowired
	private ${className}Service ${classNameLower}Service;

	
}
	

